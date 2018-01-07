package com.ccx.models.listener;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/11/5
 */

import com.ccx.models.Constants;
import com.ccx.models.util.CommonThreadPool;
import com.ccx.models.util.JsonUtils;
import com.ccx.models.util.NioReadUtil;
import com.ccx.models.util.TimerConcurrentHashMap;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WatchDirsListener {
   //标志位
    public volatile static Map<String,Boolean> done=new TimerConcurrentHashMap<>(Constants.HOUR,1000);

    private String pathdir;
    private  String filename;
    private  String reqId;

    public WatchDirsListener(String path,String reqId){
        this.reqId=reqId;
        done.put(reqId,true);
        int i=path.lastIndexOf("/");
        this.pathdir=path.substring(0,i+1);
        this.filename=path.substring(i+1);
    }

    private  volatile Map<String,Long>  map_start=new TimerConcurrentHashMap<>(null,1000);
    private volatile Map<String,Boolean> map_flag=new TimerConcurrentHashMap<>(null,1000);

    public void listener(WatchEvent.Kind<Path> wkind,Function<String,Object> fuc) {
        Path path = Paths.get(pathdir);
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService(); //创建watchService
            path.register(watchService
                    ,wkind
            ); //注册需要监控的事件,ENTRY_CREATE 文件创建,ENTRY_MODIFY 文件修改,ENTRY_MODIFY 文件删除
            try{
                CommonThreadPool.INSTANCE.getInstanse().submit(()-> {
                    start(watchService,wkind,fuc);
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start(WatchService watchService,WatchEvent.Kind<Path> wkind,Function<String,Object> fuc){
        WatchKey watchKey=null;
        while (done.get(reqId)){
            System.out.println(JsonUtils.toJson(done));
            try {
                 watchKey = watchService.poll(1, TimeUnit.SECONDS); //此处将处于等待状态,等待检测到文件夹下得文件发生改变,返回WatchKey对象
                if(watchKey != null){
                    List<WatchEvent<?>> events = watchKey.pollEvents(); //获取所有得事件
                    for (WatchEvent event : events){
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind == StandardWatchEventKinds.OVERFLOW){
                            //当前磁盘不可用
                            continue;
                        }
                        WatchEvent<Path> ev = event;
                        final Path path = ev.context();
                         if(kind == wkind&&filename.equals(path.toString())){
                            doti(path.toString(),fuc);
                        }
                    }
                    if(!watchKey.reset()){
                        //已经关闭了进程
                        System.out.println("exit watch server");
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            done.remove(reqId);
            System.out.println("停止watchservice.....");
            watchKey.cancel();
            watchService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void doti(final String path,Function<String,Object> fuc){
            if(map_flag.get(path)!=null&&map_flag.get(path)==true){
                return;
            }
            map_flag.put(path,true);
            CommonThreadPool.INSTANCE.getInstanse().submit(()-> {
                try {
                    map_start.put(path, NioReadUtil.read(pathdir+path,map_start.get(path),"UTF-8",fuc));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                map_flag.put(path,false);
            });
    }
}
