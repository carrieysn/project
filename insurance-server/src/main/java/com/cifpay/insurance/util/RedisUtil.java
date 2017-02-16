/**
 * File: RedisUtil.java
 *
 * Copyright：Copyright (c) 2015
 * Company：深圳市银信网银科技有限公司
 * Created on：2015年11月30日 下午4:02:39
 */
package com.cifpay.insurance.util;

import java.util.Collections;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.cifpay.starframework.redis.util.DataRedisUtil;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * redis缓存工具类。
 * 
 * @author 张均锋
 *
 */
public class RedisUtil {
	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	/**
	 * 存储数据。
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            小于等于０时永久存储对象，大于0时指定存储失效时间为time秒。
	 * @return
	 */
	public static boolean set(String key, String value, int time) {
		Jedis jedis = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			jedis.getSet(key, value);
			if (time != 0) {
				jedis.expire(key, time);
			}
		} catch (Exception e) {
			logger.error("存储失败！", e);
			return false;
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
		return true;
	}

	/**
	 * 存储对象，存储进进行json序列化。
	 * 
	 * @param key
	 * @param value
	 * @param time
	 *            小于等于０时永久存储对象，大于0时指定存储失效时间为time秒。
	 * @return
	 */
	public static boolean setObject(String key, Object value, int time) {
		String sValue = JacksonUtil.toJson(value);
		return set(key, sValue, time);
	}

	/**
	 * 永久存储对象，存储时进行json序列化。
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean setObject(String key, Object value) {
		String sValue = JacksonUtil.toJson(value);
		return set(key, sValue, 0);
	}

	/**
	 * 据Key获取对象。
	 * 
	 * @param key
	 * @param _class
	 * @return
	 */
	public static <T> T getObject(String key, Class<T> _class) {
		String sValue = getString(key);
		if (sValue == null) {
			return null;
		}
		return (T) JacksonUtil.fromJson(sValue, _class);
	}

	/**
	 * 据Key获取对象，对象为集合类。
	 * 
	 * @param key
	 * @param typeReference
	 * @return
	 */
	public static <T> T getObject(String key, TypeReference<T> typeReference) {
		String sValue = getString(key);
		if (sValue == null) {
			return null;
		}
		return (T) JacksonUtil.fromJson(sValue, typeReference);
	}

	/**
	 * 删除对象。
	 * 
	 * @param key
	 */
	public static void delObject(String key) {
		try {
			DataRedisUtil.del(key);
		} catch (Exception e) {
			logger.error("删除缓存数据失败！", e);
		}
		
	}

	public static String getString(String key) {
		String s = null;
		try {
			s = DataRedisUtil.getStringFromRedis(key);
		} catch (Exception e) {
			logger.error("获取缓存数据失败！", e);
		}
		return s;
	}
	
	/**
	 * 为指定key属性值累加1。
	 * 
	 * @param key 键
	 * @param field 属性
	 * @return
	 */
	public static long hincr(String key, String field) {
		return hincrby(key, field, 1);
	}
	
	/**
	 * 是否存在指定key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			return jedis.exists(key);
		} catch (Exception e) {
			logger.error("存储失败！", e);
			return false;
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
		
	}
	
	/**
	 * 批量增加键值
	 * 
	 * @param key
	 * @param fields
	 * @param values
	 * @param times 过期时间
	 * @param force 不存在key时，是否强制创建key
	 * @return 返回null则不存在key
	 */
	public static Long[] hincrBatch(String key, String[] fields, long[] values, long times, boolean force) {
		Jedis jedis = null;
		Long[] rets = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			if (!jedis.exists(key) && !force) {//不存在键
				return null;
			}
			rets = new Long[fields.length];
			for (int i = 0; i < fields.length; i++) {
				if (values[i] == 0) { //如果增加的值为0，则直接从缓存获取最新值。
					String s = jedis.hget(key, fields[i]);
					try {
						if (s == null) {//不存在值时，须确保把键插进去
							rets[i] = jedis.hincrBy(key, fields[i], 0);
						} else {
							rets[i] = Long.valueOf(s);
						}
					} catch (Exception e) {
					}
				} else {
					rets[i] = jedis.hincrBy(key, fields[i], values[i]);
				}
				
			}
			if (times > 0) {
				jedis.expire(key, (int) times);
			}
			return rets;
		} catch (Exception e) {
			logger.error("存储失败！", e);
			return rets;
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
	}
	
	/**
	 * 为指定key属性值减1。
	 * 
	 * @param key 键
	 * @param field 属性
	 * @return
	 */
	public static long hdecr(String key, String field) {
		return hincrby(key, field, -1);
	}
	
	/**
	 * 设置指定键过期时间。
	 * 
	 * @param key
	 * @param seconds
	 * @return
	 */
	public static boolean setExpire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			long i = jedis.expire(key, seconds);
			return i > 0;
		} catch (Exception e) {
			logger.error("设置键过期时间失败！", e);
			return false;
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
		
	}
	
	/**
	 * 增加指定key属性中指定的值。
	 * 
	 * @param key 键
	 * @param field 属性
	 * @param value 知道值，正数为加，负数为减
	 * @return
	 */
	public static long hincrby(String key, String field, long value) {
		Jedis jedis = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			return jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			logger.error("存储失败！", e);
			return -1;
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
	}
	
	/**
	 * 获取所有键值。
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, String> hgetall(String key) {
		Jedis jedis = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			logger.error("存储失败！", e);
			return Collections.emptyMap();
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
	}
	
	/**
	 * 获取批指定键值。
	 * 
	 * @param key
	 * @return
	 */
	public static String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			String ret = jedis.hget(key, field);
			if (ret.equals("nil")) {
				return null;
			}
			return ret;
		} catch (Exception e) {
			logger.error("存储失败！", e);
			return null;
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
	}
	
	/**
	 * 
	 * @param key
	 * @param callBack
	 * @return
	 */
	public static boolean getSetObject(String key, RedisCallBack callBack) {
		Jedis jedis = null;
		try {
			jedis = DataRedisUtil.getDataJedis();
			jedis.watch(key);
			String sValue = jedis.get(key);
			String newValue = callBack.doValue(sValue);
			Transaction t = jedis.multi();
			jedis.set(key, newValue);
			t.exec();
		} catch (Exception e) {
			logger.error("存储失败！", e);
			return false;
		} finally {
			DataRedisUtil.returnResource(jedis);
		}
		return true;
	}

	public interface RedisCallBack {

		public String doValue(String value);
	}
}
