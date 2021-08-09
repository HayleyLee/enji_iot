package com.enji_iot.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

/**
 * 工具类
 * 
 */
public class CommonUtil extends StringUtils {
	
	/**
	 * UUID
	 */
	public static class UUIDString {
		/**
		 * 
		 * 获取加密的uuid字符串
		 * 
		 * @return
		 */
		public static String getEncodeUUIDString() {
			return Base64.encodeBase64String(getUUIDString().getBytes());
		}

		/**
		 * 
		 * 获取uuid字符串
		 * 
		 * @return
		 */
		public static String getUUIDString() {
			return UUID.randomUUID().toString().replace("-", "");
		}
	}
	

	/**
	 * 
	 * 获取字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(Object o) {
		if (o == null) {
			return StringUtils.EMPTY;
		}
		return getString(o.toString());
	}

	/**
	 * 
	 * 获取字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getString(String str) {
		if (isNotEmpty(str)) {
			return str.trim().replaceAll("\t", "").replace("\n", "").replace("\r", "");
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 获取字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String getTrimString(Object obj) {
		if (obj == null) {
			return StringUtils.EMPTY;
		}
		return obj.toString().trim();
	}

	/**
	 * 转化成长整型数值, 错误时返回-9999
	 * 
	 * @param o
	 * @return
	 */
	public static Long parseLong(Object o) {
		try {
			if (o != null && isNotEmpty(o.toString().trim())) {
				return Long.parseLong(o.toString().trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -9999L;
	}

	/**
	 * 转化成整型数值, 错误时返回-9999
	 * 
	 * @param o
	 * @return
	 */
	public static int parseInt(Object o) {
		try {
			if (o != null && isNotEmpty(o.toString().trim())) {
				return Integer.parseInt(o.toString().trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -9999;
	}

	/**
	 * 从字符输入流转字符串
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static String inputStream2String(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO: handle exception
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		return sb.toString();
	}

	/**
	 * 填充目标对象的字符串到固定长度
	 * 
	 * @param src
	 * @param quote
	 * @param size
	 * @return
	 */
	public static String padLeft(Object src, String quote, int size) {
		String str = getString(src);
		int strSize = str.length();
		// 只有在源长度小于需要填补的长度时才进行填充
		String result = str;
		if (strSize < size) {
			for (int i = size - strSize; i > 0; i--) {
				result = quote + result;
			}
		}
		return result;
	}
	
	public static class checkReqUtil{
		
		private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" };

		public static boolean checkAgentIsMobile(HttpServletRequest req){
			String ua= req.getHeader("User-Agent");
			return 	checkAgentIsMobile(ua);
		}
		
		public static boolean checkAgentIsMobile(String ua) {
			boolean flag = false;
			if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
				// 排除 苹果桌面系统
				if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
					for (String item : agent) {
						if (ua.contains(item)) {
							flag = true;
							break;
						}
					}
				}
			}
			return flag;
		}
		
		
	}
	
}
