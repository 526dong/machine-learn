package com.ccx.models.util;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 多线程锁获取辅助类
 * @author:lilong
 * @Date: 2017/12/5
 */
public class LockUtils {
    private  static final  Integer LOCK_DATA_SIZE=200;
    private volatile static ConcurrentSkipListMap<String,Object> synchLock=new ConcurrentSkipListMap<>();
    private volatile static ConcurrentSkipListMap<String,Lock> reentLock=new ConcurrentSkipListMap<>();

    /**
     * 多线程中获取synchronized安全锁
     * @param key
     * @return
     */
    public static Object getSynch(String key){
        while (true) {
            if (synchLock.get(key) == null) {
                if(synchLock.putIfAbsent(key,new Object())==null){
                    break;
                }
            }else {
                break;
            }
        }
        if(synchLock.size()>LOCK_DATA_SIZE){
            synchLock.remove(synchLock.firstKey());
        }
        return  synchLock.get(key);
    }
    /**
     * 多线程中获取ReentrantLock安全锁
     * @param key
     * @return
     */
    public static Lock getLock(String key){
        while (true){
            if(reentLock.get(key)==null){
                if(reentLock.putIfAbsent(key,new ReentrantLock())==null){
                    break;
                }
            }else {
                break;
            }
        }
        if(reentLock.size()>LOCK_DATA_SIZE){
            reentLock.remove(synchLock.firstKey());
        }
        return   reentLock.get(key);
    }

    /**
     * 删除重入锁
     * @param key
     */
    public static void removeLock(String key){
        reentLock.remove(key);
    }
    /**
     * 删除sync锁
     * @param key
     */
    public static void removesyckLock(String key){
        synchLock.remove(key);
    }
/*
    public static void main(String[] args) {
        Integer k=3;
        for (int i = 0; i <100 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Lock lock=getLock("aak_"+k);
                    lock.lock();
                        System.out.println("1111111");
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    lock.unlock();
                }
            }).start();
        }

    }*/
}
