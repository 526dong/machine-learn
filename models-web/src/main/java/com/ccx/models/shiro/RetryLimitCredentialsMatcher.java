package com.ccx.models.shiro;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 输错5次密码锁定半小时，ehcache.xml配置
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {
	private static Logger logger = LogManager.getLogger(RetryLimitCredentialsMatcher.class);

	public static final String DEFAULT_CHACHE_NAME = "retryLimitCache";
	
	private String retryLimitCacheName;
	private Cache<String, AtomicInteger> passwordRetryCache;
	private PasswordHash passwordHash;
	@Autowired
	private ShiroSpringCacheManager cacheManager;
	
	public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
		this.retryLimitCacheName = DEFAULT_CHACHE_NAME;
	}
	
	public String getRetryLimitCacheName() {
		return retryLimitCacheName;
	}
	public void setRetryLimitCacheName(String retryLimitCacheName) {
		this.retryLimitCacheName = retryLimitCacheName;
	}
	
	public void setPasswordHash(PasswordHash passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		String username = (String) authcToken.getPrincipal();
		System.out.println("===="+cacheManager.getCache(retryLimitCacheName));
		//retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if(retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if(retryCount.incrementAndGet() > 5) {
			//if retry count > 5 throw
			logger.warn("username: " + username + " tried to login more than 5 times in period");  
			throw new ExcessiveAttemptsException("用户名: " + username + " 密码连续输入错误超过5次，锁定半小时！"); 
		}

		boolean matches = super.doCredentialsMatch(authcToken, info);
		if(matches) {
			//clear retry data
			passwordRetryCache.remove(username);
		}
		return matches;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(passwordHash, "you must set passwordHash!");
		super.setHashAlgorithmName(passwordHash.getAlgorithmName());
		super.setHashIterations(passwordHash.getHashIterations());
		this.passwordRetryCache = cacheManager.getCache(retryLimitCacheName);
	}
}
