package com.wjalong.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class DeviceUtils {


	private static final String ANDROID_PHONE = "android";
	private static final String IOS_PHONE = "ios";
	private static final String WEB = "web";
	public static boolean isLegalDevice(String deviceType) {
		if (StringUtils.isEmpty(deviceType)) {
			return false;
		}
		switch (deviceType) {
			case ANDROID_PHONE:
				return true;
			case IOS_PHONE:
				return true;
			case WEB:
				return true;
		}
		;
		return false;
	}



	public static boolean isClient(String deviceType) {
		if (StringUtils.isEmpty(deviceType)) {
			return false;
		}
		if (IOS_PHONE.equals(deviceType)||ANDROID_PHONE.equals(deviceType)) {
			return true;
		}
		return false;
	}

	public static boolean isNewVersion(HttpServletRequest request, String version) {

		String currentVersion = request.getHeader("version");
		if (currentVersion == null) {
			return false;
		}
		String head = currentVersion.trim().replaceAll("\\.", "").substring(0, 2);
		int v = Integer.parseInt(head);
		String compare = version.trim().replaceAll("\\.", "").substring(0, 2);
		int compared = Integer.parseInt(compare);
		if (v >= compared) {
			return true;
		}
		return false;
	}

	public static boolean isNewVersion(String currentVersion, String version) {

		if (currentVersion == null) {
			return false;
		}
		String head = currentVersion.trim().replaceAll("\\.", "").substring(0, 2);
		int v = Integer.parseInt(head);
		String compare = version.trim().replaceAll("\\.", "").substring(0, 2);
		int compared = Integer.parseInt(compare);
		if (v >= compared) {
			return true;
		}
		return false;
	}
}
