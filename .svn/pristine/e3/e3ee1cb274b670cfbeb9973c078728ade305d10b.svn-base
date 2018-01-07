package com.ccx.models.util;


import redis.clients.jedis.JedisCluster;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 * @author LHM
 *
 */
public class RedisCacheUtil {
	private static JedisCluster jedisCluster;

	public  JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public  void setJedisCluster(JedisCluster jedisCluster) {
		RedisCacheUtil.jedisCluster = jedisCluster;
	};
	
	/**
	   * 指定String  key  删除
	   * @param key
	   */
	  public static void delete(String key) {
		  jedisCluster.del(key);
	  }
	  /**
	   *  取出 缓存 数据
	   * @param key
	   * @return
	   */
	  public static String  get(String key) {
		  String value = jedisCluster.get(key);
	      return value;
	  }
	  
	  /**
	   * 删除 key 存贮
	   * @param key
	   * @return
	   */
	  public static Long  del(String key) {
		  Long value = jedisCluster.del(key);
	      return value;
	  }
	  /**
	   * 设置 过期时间 单位秒
	   * @param key
	   * @param value
	   * @param seconds
	   * @return
	   */
	  public static  void setTimeSecond(String key,String value,int seconds ) {
		   jedisCluster.setex(key, seconds, value);
	  }
	  /**
	   * 设置 过期时间 单位毫秒
	   * @param key
	   * @param value
	   * @param milliseconds
	   * @return
	   */
	  public static  void setTimeMilliseconds(String key,String value,long milliseconds ) {
		   jedisCluster.psetex(key, milliseconds, value);
	  }
	  /**
	   * 设置 过期时间 以天为单位
	   * @param key
	   * @param value
	   * @param day
	   * @return
	   */
	  public static  void setTimeDay(String key,String value,int day ) {
		   jedisCluster.psetex(key, day*24*60*60, value);
	  }
	  /**
	   * 设置 过期时间 以小时为单位
	   * @param key
	   * @param value
	   * @param Hour
	   * @return
	   */
	  public static  void setTimeHour(String key,String value,int Hour ) {
		   jedisCluster.psetex(key, Hour*60*60, value);
	  }
	  /**
	   * 设置 过期时间 以分钟为单位
	   * @param key
	   * @param value
	   * @param minute
	   * @return
	   */
	  public static  void setTimeMinute(String key,String value,int minute ) {
		   jedisCluster.psetex(key, minute*60, value);
		   String string = jedisCluster.get(key);
		   System.out.println(string);
 
	  }
	  
	  /**
	   * 自增：计数
	   * @param key 已保存的key值
	   */
	  public static void incr(String key){
		   jedisCluster.incr(key);
		  
	  }
	  /**
		 * 获取数据
		 * 
		 * @param key
		 * @return
		 */
		public static byte[] get(byte[] key) {

			byte[] value = null;

			// JedisCluster jedis =null;
			try {
				// jedis = new JedisCluster(jedisClusterNode, config);
				value = jedisCluster.get(key);
			} catch (Exception e) {
				// 释放redis对象
				// close(jedis);
				e.printStackTrace();
			} finally {
				// 返还到连接池
				// close(jedis);
			}

			return value;
		}
	  /**
	    * 存入缓存数据
	    * @param key
	    * @param
	    */
	  public static  void set(String key,String value) {
		  jedisCluster.set(key, value);
	  }
	  /**
	   * 存入缓存数
	   * @param key
	   * @param value
	   */
	  public static void set(byte[] key, byte[] value) {

			// JedisCluster jedis =null;
			try {
				// jedis = new JedisCluster(jedisClusterNode, config);
				jedisCluster.set(key, value);
			} catch (Exception e) {
				// 释放redis对象
				// close(jedis);
				e.printStackTrace();
			} finally {
				// 返还到连接池
				// close(jedis);
			}
		}
	  /**
	   * 存入缓存数据
	   * @param key
	   * @param value
	   * @param time
	   */
	  public static void set(byte[] key, byte[] value, int time) {
			// JedisCluster jedis =null;
			try {
				// jedis = new JedisCluster(jedisClusterNode, config);
				jedisCluster.set(key, value);
				jedisCluster.expire(key, time);
			} catch (Exception e) {
				// 释放redis对象
				// close(jedis);
				e.printStackTrace();
			} finally {
				// 返还到连接池
				// close(jedis);
			}
		}
	  /**
		 * redis使用接口
		 * @param key
		 * @param value
		 * @param time
		 * @throws Exception 
		 */
		public static void setRedis(String key, Object value, Integer time) throws Exception {
			if (time != null) {
				set(key.getBytes(), ObjectUtil.objectToBytes(value), time);
			} else {
				set(key.getBytes(), ObjectUtil.objectToBytes(value));
			}
		}
		public static void setRedis(String key, Object value) throws Exception {
			set(key.getBytes(), ObjectUtil.objectToBytes(value));
		}
		public static Object getRedis(String key) throws Exception {
			if (null != RedisCacheUtil.get(key.getBytes())) {
				return ObjectUtil.bytesToObject(RedisCacheUtil.get(key.getBytes()));
			}else{
				return null;
			}
		}
		public boolean getLock(String key, int seconds){
			return getLock(key,seconds,0);
		}

	/**
	 * 获取锁
	 **/
	public static boolean getLock(String key, int seconds,int n){
		try {
			if(n>10){
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(seconds));
				return getLock(key, seconds, 0);
			}
			long nowtime=System.currentTimeMillis();
			long stopTime=nowtime+seconds*1000l;
			Long flag= jedisCluster.setnx(key,String.valueOf(stopTime));
			if(flag==1){
				jedisCluster.expire(key, seconds);
				return true;
			}
//			String cTime=jedis.get(key);
//			if(cTime==null){
//				return getLock(key,seconds,++n);
//			}
			//long currentTime=Long.parseLong(cTime);
			/*if(nowtime>currentTime){//锁超时
				String c2=	jedis.getSet(key,String.valueOf(stopTime));
				if(c2.equals(cTime)){
					jedis.expire(key, seconds);
					return true;
				}
				//System.out.println(Thread.currentThread().getName()+"锁超时。。。");
			}*/
			//System.out.println(Thread.currentThread().getName()+"等待获取锁中。。。。");
			TimeUnit.MILLISECONDS.sleep(200*seconds);
			return getLock(key,seconds,++n);
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 释放锁
	 * @param key
	 */
	public static void releaseLock(String key){
		Long a= null;
		try {
			a=jedisCluster.del(key);
			if (a==0||a==null){
				TimeUnit.MILLISECONDS.sleep(2000);
				releaseLock(key);
			}
		}catch (Exception e){
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			releaseLock(key);
		}
	}
}
