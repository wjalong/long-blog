package com.wjalong.util.web;

import com.wjalong.util.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Component
public class SessionUtil {
	@Autowired
	private RedisDao redisDao;
	private static final Integer TOKEN_EXPIRE_SECOND_TIME=30*24*60*60;
	//aes密钥失效时间
	private static final Integer AESKEY_EXPIRE_SECOND_TIME=30*24*60*60;
	private static final Integer DELAY_EXPIRE_SECOND_TIME = 60*60;
	
	public String getTokenByPage(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	
	public void createUserSession(String token,SessionUser sessionUser) {
		redisDao.set(TokenUtil.tokenSessionKey(token), sessionUser,TOKEN_EXPIRE_SECOND_TIME);
	}
	public void createUserSession(String deviceType,String token,SessionUser sessionUser) {
		redisDao.set(TokenUtil.tokenSessionKey(deviceType,token), sessionUser,TOKEN_EXPIRE_SECOND_TIME);
	}
	
	public SessionUser getUserSession(String token) {
		SessionUser user = redisDao.get(TokenUtil.tokenSessionKey(token),SessionUser.class);
		return user;
	}
	public SessionUser getUserSession(String deviceType,String token) {
		SessionUser user = redisDao.get(TokenUtil.tokenSessionKey(deviceType,token),SessionUser.class);
		return user;
	}

	public String getUserToken(String token) {
		SessionUser user = redisDao.get(TokenUtil.tokenSessionKey(token), SessionUser.class);
		if (user != null) {
			return user.getToken();
		}
		return null;
	}

	public void createDeviceSession(String deviceId,SessionDevice sessionDevice) {
		redisDao.set(TokenUtil.deviceSessionKey(deviceId), sessionDevice,AESKEY_EXPIRE_SECOND_TIME);
	}

	public SessionDevice getDeviceSession(String deviceId) {
		SessionDevice device = redisDao.get(TokenUtil.deviceSessionKey(deviceId),SessionDevice.class);
		return device;

	}

	
	public void expireUserSession(String token) {
		redisDao.expire(token);
	}
	
	public void expire(String key) {
		redisDao.expire(key);
	}
	
	public void set(String key,Object obj) {
		redisDao.set(key, obj);
	}
	

	public void set(String key,Object obj,int expireSeconds) {
		redisDao.set(key, obj,expireSeconds);
	}
	
	public <T> T get(String key,Class<T>classzz) {
		return redisDao.get(key,classzz);
	}
	
	public String  get(String key) {
		return  redisDao.get(key);
	}
	
	public void set(String key,String obj,int expireSeconds) {
		redisDao.set(key, obj,expireSeconds);
	}

	/**
	 * 重新登录,历史token 设置延迟退出
	 * @param token
	 */
	public void delayExpireSession(String token) {
		redisDao.refresh(TokenUtil.tokenSessionKey(token), DELAY_EXPIRE_SECOND_TIME);
	}

	/**
	 * 续期
	 * @param token
	 */
	public void refreshSession(String token) {
		redisDao.refresh(TokenUtil.tokenSessionKey(token), TOKEN_EXPIRE_SECOND_TIME);
	}
}
