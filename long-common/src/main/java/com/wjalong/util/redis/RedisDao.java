package com.wjalong.util.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisDao {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	

	private Logger logger = LoggerFactory.getLogger(RedisDao.class);
	// 60秒
	private static final int DEFAULT_WAITOUT_TIME = 60;

	public void set(String key, Object obj) {
		String value = JSONObject.toJSONString(obj);
		this.redisTemplate.opsForValue().set(key, value, DEFAULT_WAITOUT_TIME, TimeUnit.SECONDS);
	}

	//设置为永久不失效
	public void setForever(String key, Object obj) {
		String value = JSONObject.toJSONString(obj);
		this.redisTemplate.opsForValue().set(key, value);
	}

	public void set(String key, Object obj, int expireSeconds) {
		String value = JSONObject.toJSONString(obj);
		this.redisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
	}
	
	public void setMillis(String key, Object obj, int MillisSeconds) {
		String value = JSONObject.toJSONString(obj);
		this.redisTemplate.opsForValue().set(key, value, MillisSeconds, TimeUnit.MILLISECONDS);
	}

	public <T> T get(String key, Class<T> classzz) {
		String jsonStr = this.redisTemplate.opsForValue().get(key);
		if (StringUtils.isEmpty(jsonStr)) {
			return null;
		}
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		return JSONObject.toJavaObject(jsonObject, classzz);
	}

	public String get(String key) {
		return this.redisTemplate.opsForValue().get(key);

	}

	public void set(String key, String value, int expireSeconds) {
		this.redisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
	}

	public void setIncrement(String key) {
		this.redisTemplate.boundValueOps(key).increment();
	}

	public void expire(String key) {
		this.redisTemplate.delete(key);
	}
	
	
	public void refresh(String key,long timeout) {
		this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}


	

}
