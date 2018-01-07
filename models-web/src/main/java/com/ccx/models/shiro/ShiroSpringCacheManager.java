package com.ccx.models.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

/**
 * 使用spring-cache作为shiro缓存
 * 缓存管理器
 *
 */
public class ShiroSpringCacheManager implements CacheManager, Destroyable {
	private static Logger logger = LogManager.getLogger(ShiroSpringCacheManager.class);
	
	private org.springframework.cache.CacheManager cacheManager;
	
	public org.springframework.cache.CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		if (logger.isTraceEnabled()) {
			logger.trace("Acquiring ShiroSpringCache instance named [" + name + "]");
		}
		org.springframework.cache.Cache cache = cacheManager.getCache(name);
		return new ShiroSpringCache<K, V>(cache);
	}

	@Override
	public void destroy() throws Exception {
		cacheManager = null;
	}

}
