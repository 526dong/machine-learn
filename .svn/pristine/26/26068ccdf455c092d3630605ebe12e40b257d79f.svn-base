package com.ccx.models.listener;

/**
 * @Description:
 * @author:lilong
 * @Date: 2017/11/5
 */
import com.ccx.models.util.NioReadUtil;
import com.ccx.models.util.TimerConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WatchDirListener {
   org.apache.logging.log4j.Logger logs = LogManager.getLogger("myLog");
   org.apache.logging.log4j.Logger log = LogManager.getLogger(this.getClass());


    private WatchService watchService;
    private boolean notDone = true;

    private  volatile Map<String,Long>  map_start=new TimerConcurrentHashMap<>(null,1000);
    private volatile Map<String,Boolean> map_flag=new TimerConcurrentHashMap<>(null,1000);
    private final String PATHDIR="/opt/apache-tomcat-7.0.59/logs/";
    private final String FILE_NAME="catalina.out";
    //分析数据处理线程池
   private static ExecutorService taskExecutor=
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                    Runtime.getRuntime().availableProcessors(),
                    60L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>(1000));

    public void init() {
        Path path = Paths.get(PATHDIR);
        try {
            watchService = FileSystems.getDefault().newWatchService(); //创建watchService

            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE
                    ,StandardWatchEventKinds.ENTRY_MODIFY
                    ,StandardWatchEventKinds.ENTRY_DELETE
            ); //注册需要监控的事件,ENTRY_CREATE 文件创建,ENTRY_MODIFY 文件修改,ENTRY_MODIFY 文件删除
            try{
                new Thread(()->start()).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        log.info("watch...");
        while (notDone){
            try {
                WatchKey watchKey = watchService.poll(1, TimeUnit.SECONDS); //此处将处于等待状态,等待检测到文件夹下得文件发生改变,返回WatchKey对象
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
                        if(kind == StandardWatchEventKinds.ENTRY_CREATE){
                        }else if(kind == StandardWatchEventKinds.ENTRY_MODIFY){
                            doti(path.toString());
                        }else if(kind == StandardWatchEventKinds.ENTRY_DELETE){
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
    }
    private void doti(final String path){
           // log.info("into....");
            if(!path.equals(FILE_NAME))
                return;
            if(map_flag.get(path)!=null&&map_flag.get(path)==true){
                log.info("wait.....");
                return;
            }
            log.info("start read logs....");
            map_flag.put(path,true);
            taskExecutor.submit(()-> {
                try {
                    map_start.put(path, NioReadUtil.read(PATHDIR+path,map_start.get(path),"UTF-8",(line)->{
                        logs.info(line);
                        return null;
                    }));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                map_flag.put(path,false);
            });

    }
}
