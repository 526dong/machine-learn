package com.ccx.models.util;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:计时限容Map类（线程安全）
 * @author:lilong
 * @Date: 2017/10/31
 */
public class TimerConcurrentHashMap<K,V> extends ConcurrentHashMap<K,V> {
   //超时时间
    private Long timeout;
    //Map缓存容量
    private Integer count;

    private  Lock lock=new ReentrantLock();

    private Map<K,Long> time=new ConcurrentHashMap<>();

    private volatile AtomicInteger size=new AtomicInteger(0);

    private  volatile long lastOperationTime=new Date().getTime();
    public TimerConcurrentHashMap(Long timeout, Integer count){
        super();
        this.timeout=timeout;
        this.count=count;
    }

    @Override
    public V put(K key, V value){
        if(count==null||size()<count){
            time.put(key,System.currentTimeMillis());
            V v= super.put(key,value);
            size.incrementAndGet();
            return  v;
        }
        if(size.get()%count==count/10
                ||(timeout!=null&&System.currentTimeMillis()
                        -lastOperationTime>timeout)) {
             getSize();
        }
      return  null;
    }


    @Override
    public V remove(Object key) {
        V v= super.remove(key);
        return v;
    }

    @Override
    public V get(Object key) {
        V v=super.get(key);
        if(v==null) return null;
        if(timeout!=null&&System.currentTimeMillis()-time.get(key)>timeout){
            remove(key);
            size.decrementAndGet();
            return null;
        }
        return super.get(key);
    }

    @Override
    public int size() {
        return this.size.get();
    }
    private void getSize(){
        try {
            lock.lock();
            for(Entry<K,Long> str:this.time.entrySet()){
                if(System.currentTimeMillis()-str.getValue()>=timeout){
                    remove(str.getKey());
                }
            }
            size.set(super.size());
        }finally {
            lock.unlock();
        }
    }
    @Override
    public boolean containsKey(Object key) {
        getSize();
        return super.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        getSize();
        return super.containsValue(value);
    }

    @Override
    public KeySetView<K, V> keySet() {
        getSize();
        return super.keySet();
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        getSize();
        return super.entrySet();
    }



    @Override
    public boolean contains(Object value) {
        getSize();
        return super.contains(value);
    }

    @Override
    public Enumeration<K> keys() {
        getSize();
        return super.keys();
    }

    @Override
    public KeySetView<K, V> keySet(V mappedValue) {
        getSize();
        return super.keySet(mappedValue);
    }

    public static void main(String[] args) {
        //Map<String,Integer> map1=new HashMap<>();
      // Map<String,Integer> map1=new ConcurrentHashMap<>();
        Map<String,Integer> map1=new TimerConcurrentHashMap<>(10000l,10000000);
       final CountDownLatch latch=new CountDownLatch(10*10000);
        long l=System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <10000 ; j++) {
                int finalJ = j;
                int finalI = i;
                new Thread(()->{
                    map1.put(finalI +""+ finalJ,1);
                    latch.countDown();
                }
                ).start();
            }
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时：：：：："+(System.currentTimeMillis()-l));
    }
}
