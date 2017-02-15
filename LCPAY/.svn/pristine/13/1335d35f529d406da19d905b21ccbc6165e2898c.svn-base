package com.cifpay.lc.core.cache.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.alibaba.fastjson.JSON;

public abstract class BaseCacheServant<T> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public StringRedisTemplate redisTemplate;
	
	@Autowired(required=false)
	public CuratorFramework client;
	
	public boolean setCache(final String key, final T value, final long expires) {
		final String cacheKey = getKey(key);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
			@Override  
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException { 
				try{
					byte[] kByte = redisTemplate.getStringSerializer().serialize(cacheKey);
					byte[] vByte = redisTemplate.getStringSerializer().serialize(JSON.toJSONString(value));
					connection.setEx(kByte, expires, vByte);
					return true;
				}catch(Exception e){
					logger.error("设置缓存异常",e);
					return false;
				}
			}  
		});
		return result;
	}
	
	public boolean setCache(final String key, final T value) {
		final String cacheKey = getKey(key);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
			@Override  
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException { 
				try{
					byte[] kByte = redisTemplate.getStringSerializer().serialize(cacheKey);
					byte[] vByte = redisTemplate.getStringSerializer().serialize(JSON.toJSONString(value));
					connection.set(kByte, vByte);
					return true;
				}catch(Exception e){
					logger.error("设置缓存异常",e);
					return false;
				}
			}  
		});
		return result;
	}
	
	public boolean lpushCache(final String key, final T value) {
		final String cacheKey = getKey(key);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
			@Override  
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException { 
				try{
					byte[] kByte = redisTemplate.getStringSerializer().serialize(cacheKey);
					byte[] vByte = redisTemplate.getStringSerializer().serialize(JSON.toJSONString(value));
					connection.lPush(kByte, vByte);
					return true;
				}catch(Exception e){
					logger.error("设置缓存异常",e);
					return false;
				}
			}  
		});
		return result;
	}

	public boolean removeCache(String key) {
		final String cacheKey = getKey(key);
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
	        @Override  
	        public Boolean doInRedis(RedisConnection connection)  throws DataAccessException { 
	        	try{
		        	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
	                byte[] k = serializer.serialize(cacheKey); 
	                connection.del(k);
	                return true;
	        	}catch(Exception e){
	        		logger.error("删除缓存异常",e);
	        		return false;
	        	}
	        }  
	    });
		return result;
	}

	public T getCache(final String key, final Class<T> c) {
		final String cacheKey = getKey(key);
		T result = redisTemplate.execute(new RedisCallback<T>() {  
			public T doInRedis(RedisConnection connection) throws DataAccessException {  
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] k = serializer.serialize(cacheKey);  
				byte[] val = connection.get(k);  
				if (val == null) {  
					return null;  
				}  
				String jsonStr = serializer.deserialize(val);
				return  JSON.parseObject(jsonStr, c);
			}  
		});  
		return result; 
	}
	
	
	public long pubMessage(final String topic,final String msg){
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] channel = redisTemplate.getStringSerializer().serialize(topic);
				byte[] message = redisTemplate.getStringSerializer().serialize(msg);
				long result = connection.publish(channel, message);
				return result;
			}
		});
		return result;
	}

	
	public abstract String getKey(String key);
	
//	public abstract String getLockPath(String key);
//	
//	
//	public InterProcessMutex getlock(String key) {
//		String lockPath = getLockPath(key);
//		try {
//			Stat stat = client.checkExists().forPath(lockPath);
//			if(stat == null){
//				client.create().creatingParentsIfNeeded().forPath(lockPath);
//			}
//		} catch (Exception e) {
//			logger.error("创建分布式锁异常",e);
//		}
//		
//		InterProcessMutex lock = new InterProcessMutex(client,lockPath);
//		return lock;
//	}
//	
//	public void unlock(InterProcessMutex lock,String key) {
//		try {
//			if(lock != null)
//				lock.release();
//			String lockPath = getLockPath(key);
//			client.delete().forPath(lockPath);
//		} catch (Exception e) {
//			logger.error("释放分布式锁异常",e);
//		}
//	}
}
