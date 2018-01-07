package com.ccx.credit.risk.manager;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:计时限容Map类（线程安全）
 * @author:lilong
 * @Date: 2017/10/31
 */
public class  TimerConcurrentHashMap<K,V> extends ConcurrentHashMap<K,V> {
   //超时时间
    private Long timeout;
    //Map缓存容量
    private Integer count;

    private  Lock lock=new ReentrantLock();

    private Map<K,Long> time=new ConcurrentHashMap<>();
    public TimerConcurrentHashMap(Long timeout,Integer count){
        super();
        this.timeout=timeout;
        this.count=count;
    }

    @Override
    public V put(K key, V value){
        if(count==null||size()<count){
            lock.lock();
            time.put(key,System.currentTimeMillis());
            V v= super.put(key,value);
            lock.unlock();
            return  v;
        }
      return  null;
    }

    @Override
    public V remove(Object key) {
        lock.lock();
        V v= super.remove(key);
        lock.unlock();
        return v;
    }

    @Override
    public V get(Object key) {
        V v=super.get(key);
        if(v==null) return null;
        if(timeout!=null&&System.currentTimeMillis()-time.get(key)>timeout){
            remove(key);
            return null;
        }
        return super.get(key);
    }

    @Override
    public int size() {
        if(timeout==null) return super.size();
        lock.lock();
        for(Entry<K,Long> str:this.time.entrySet()){
            if(System.currentTimeMillis()-str.getValue()>=timeout){
                remove(str.getKey());
            }
        }
        lock.unlock();
        return super.size();
    }
}
