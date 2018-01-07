package com.ccx.models.util;

import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Function;

/**
 * @Description:队列（队列出队时默认执行函数，设置时间后到达一定时间执行函数）
 * @author:lilong
 * @Date: 2017/12/21
 */
public class    FunctionTimerQueue<K> extends ConcurrentLinkedQueue<K> {
    //超时多久时取出队列
    private Long time;
    //执行函数
    private Function<K,Boolean> fuc;

    private  Timer t;


    public FunctionTimerQueue(Long time, Function<K, Boolean> fuc) {
        this.time = time;
        this.fuc = fuc;
    }

    public FunctionTimerQueue(Collection<? extends K> c, Long time, Function<K, Boolean> fuc) {
        super(c);
        this.time = time;
        this.fuc = fuc;
    }

    @Override
    public boolean add(K k) {
        boolean b= super.add(k);
       if(this.size()==1){time();}
        return b;
    }

    @Override
    public boolean offer(K k) {
        boolean b= super.offer(k);
        if(this.size()==1){time();}
        return  b;
    }

    @Override
    public K poll() {
        K k= super.poll();
        System.out.println("开始请求。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。"+JsonUtils.toJson(k));
        doFuc(k);
        time();
        return k;
    }
    private void doFuc(K k){
        try {
            fuc.apply(k);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void time(){
        if(t!=null) t.cancel();
        if (time==null||this.size()==0) return;
        t =new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                poll();
            }
        }, time);
    }
/*
    public static void main(String[] args) {
      FunctionTimerQueue<Integer> queue=new FunctionTimerQueue<Integer>( 2000l,(val)->{
           System.out.println("取值：：：："+val);
           return true;
       });
        queue.add(1);
        System.out.println("add1"+queue);
        queue.add(2);
        System.out.println("add2"+queue);
        queue.add(3);
        System.out.println("add3"+queue);
        System.out.println("poll....");
        queue.poll();
        queue.add(4);
        System.out.println("add4"+queue);
        System.out.println("poll....");
        queue.poll();
        queue.add(5);
        System.out.println("add5"+queue);
        try {
            System.out.println(".......................wait.............");
            TimeUnit.SECONDS.sleep(3l);
            System.out.println(".......................wait..end.............");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.add(6);
        System.out.println("add6"+queue);
        queue.poll();
        queue.add(7);
        System.out.println("add7"+queue);

        try {
            TimeUnit.SECONDS.sleep(100l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/
}
