package com.wjalong.util.web;

import java.util.UUID;

public class TokenUtil {
	public static String generate() {
		return  MD5Utils.MD5Encode(UUID.randomUUID().toString().replaceAll("-",""));
	}
	
	public static String tokenSessionKey(String token) {
		return "user_"+token;
	}
	public static String tokenSessionKey(String deviceType,String token) {
		return deviceType+"_user_"+token;
	}


	public static String deviceSessionKey(String deviceId) {
		return "device_"+deviceId;
	}


}
