package com.cifpay.lc.core.cache.integrate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheManagerConfig {

	@Bean
	@ConditionalOnMissingBean
	public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		
		Map<String,Long> expires = new HashMap<String,Long>();
		expires.put("LC_", 3600L);
		expires.put("LC_BANK_", 3600L);
		expires.put("LC_OPEN_", 3600L);
		expires.put("LC_PRODUCT_", 3600L);
		expires.put("LC_SEND_", 3600L);
		expires.put("LC_TYPE_", 3600L);
		expires.put("LC_MERCHANT_", 3600L);
		expires.put("PRE_LC_", 3600L);
		cacheManager.setExpires(expires);
		return cacheManager;
	}
}
