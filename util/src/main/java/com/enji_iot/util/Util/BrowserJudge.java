package com.enji_iot.util.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.enji_iot.util.Config.ProConfig;

public class BrowserJudge {


	/**
	 * 是否是微信浏览器
	 * @param request
	 * @return  boolean
	 */
	public static boolean isWeixin(HttpServletRequest request){
		if(((HttpServletRequest) request).getHeader("user-agent") == null){
			return false ;
		}		
		String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {
			return true;
		}
		return false;
	}
	
	public static class Weixin {


		public static Map<String, String> sign(String jsapi_ticket, String url) {
			Map<String, String> ret = new HashMap<String, String>();
			String nonce_str = create_nonce_str();
			String timestamp = create_timestamp();
			String string1;
			String signature = "";

			// 注意这里参数名必须全部小写，且必须有序
			string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url="
					+ url;
			System.out.println(string1);

			try {
				MessageDigest crypt = MessageDigest.getInstance("SHA-1");
				crypt.reset();
				crypt.update(string1.getBytes("UTF-8"));
				signature = byteToHex(crypt.digest());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			ret.put("url", url);
			ret.put("jsapi_ticket", jsapi_ticket);
			ret.put("nonceStr", nonce_str);
			ret.put("timestamp", timestamp);
			ret.put("signature", signature);

			return ret;
		}

		private static String byteToHex(final byte[] hash) {
			Formatter formatter = new Formatter();
			for (byte b : hash) {
				formatter.format("%02x", b);
			}
			String result = formatter.toString();
			formatter.close();
			return result;
		}

		private static String create_nonce_str() {
			return UUID.randomUUID().toString();
		}

		private static String create_timestamp() {
			return Long.toString(System.currentTimeMillis() / 1000);
		}

		/**
		 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
		 */
		public static String createSign(SortedMap<String, String> packageParams, String trade_type) {
			StringBuffer sb = new StringBuffer();
			Set es = packageParams.entrySet();
			Iterator it = es.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String k = (String) entry.getKey();
				String v = (String) entry.getValue();
				if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
					sb.append(k + "=" + v + "&");
				}
			}
			sb.append("key=" + ProConfig.WEIXIN.MCH_SERECT);
			String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
			return sign;
		}

		public static void main(String[] args) {
			
		}

	}
	

}
