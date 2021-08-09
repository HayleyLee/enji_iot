package com.enji_iot.util.Util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;


public class ObjectUtil extends CommonUtil {

	public static Boolean isEmpty(Object o){
		if(o == null){
			return true ;
		}
		if(o instanceof List ){
			if(((List) o).size()==0){
				return true ;
			}
		}else if(o instanceof String){
			if(StringUtils.isBlank((String)o)){
				return true ;
			}
		}
		return false ;
	}
	
	public static boolean hasNull(Object... objs) {
		if (objs == null || objs.length == 0) {
			return true;
		} else {
			for (int i = 0; i < objs.length; i++) {
				if (objs[i] == null || isEmpty(getString(objs[i]))) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static Boolean isNotEmpty(Object o){
		return !isEmpty(o) ;
	}
	
	public static Boolean isIntegerOverZero(Integer value){
		if(value != null && value >0){
			return true ;
		}else{
			return false ;
		}
	}
	
	/**
	 * 把字符串首字母转换为大写
	 * 
	 * @param str
	 * @return
	 */
	public static String upFirstChar(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

	/**
	 * 随机生成6位验证码
	 */
	public static String getSixRandomCode(){
		String code = "";
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int r = random.nextInt(10); //每次随机出一个数字（0-9）
			code = code + r;  //把每次随机出的数字拼在一起
		}
		return code ;
	}
	
	/**
	 * 转化成整数值, 错误时返回-9999
	 * 
	 * @param o
	 * @return
	 */
	public static Integer parseIntData(Object o) {
		try {
			if (o != null && isNotEmpty(o.toString().trim())) {
				return Integer.parseInt(o.toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -9999;
	}
	
	public static String dictionaryValue(Double sdata , String value){
		if(isNotEmpty(value)){
			String[] values = value.split(",");
			for(int i=0;i<values.length;i++){
				if( sdata == Double.parseDouble(values[i].split(":")[0] )){
					return values[i].split(":")[1] ;
				}
			}
		}
		return null ;
	}
	
	
	/**
	 * socket工具类
	 * 
	 *
	 */
	public static class socket  {

		/**
		 * socket连接缓存
		 */
		private static Map<String, Socket> SOCKETS = new HashMap<String, Socket>();

		/**
		 * 
		 * 
		 * @param ip
		 * @param port
		 * @param message
		 */
		public static Integer send(String ip, int port, String message) {
			try {
				String socketKey = ip + "_" + port;
				Socket socket = null;
				if (SOCKETS.containsKey(socketKey)) {
					socket = SOCKETS.get(socketKey);
					if (socket.isClosed() || !socket.isConnected()) {
						socket = new Socket(ip, port);
						SOCKETS.put(socketKey, socket);
					}
				} else {
					socket = new Socket(ip, port);
					SOCKETS.put(socketKey, socket);
				}
				boolean socket_ok_flag = false;
				try {
					socket.sendUrgentData(0xFF);
					socket_ok_flag = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (!socket_ok_flag) {
					socket = new Socket(ip, port);
					SOCKETS.put(socketKey, socket);
				}
				// 2、获取输出流，向服务器端发送信息
				OutputStream os = socket.getOutputStream();// 字节输出流
				PrintWriter pw = new PrintWriter(os);// 将输出流包装成打印流
				pw.write(message);
				pw.flush();
			} catch (Exception e) {
				LogUtil.errorLog(e.getMessage());
				return -1 ;
			}
			return 0;
		}
	}
	
//	public static String getMacValue()  {
//		InetAddress ia;
//		try {
//			ia = InetAddress.getLocalHost();
//			String mac =getLocalMac(ia);
//			String UID = ObjectUtil.UUIDString.getUUIDString();
//			String PID = ia.toString();
//			long nowTime = new Date().getTime();
////			LogUtil.otherLogger.error("MPIDAC:"+mac);
//			return UID+MD5Util.MD5Encode(mac, "") + MD5Util.MD5Encode(PID, "") +nowTime;
//		} catch (Exception e) {
//			LogUtil.errorLog(e.getMessage());
//			return "";
//		}
//	}
	
	public static void main(String[] args) {
		System.out.println( getSixRandomCode() ) ;
	}
	
	private static String getLocalMac(InetAddress ia) throws SocketException {
		//获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		return sb.toString().toUpperCase();
	}
	
	/**
	 * keystore生成方法，交互时删除
	 * @return
	 */
	private static String generateKeyStore(String macValue){
		InetAddress ia;
		try {
			ia = InetAddress.getLocalHost();
			String mac = getLocalMac(ia);
			if(isNotEmpty(macValue)){
				mac = macValue;
			}
			String UID = ObjectUtil.UUIDString.getUUIDString();
			String PID = ia.toString();
			long nowTime = new Date().getTime();
			return UID+MD5Util.MD5Encode(mac, "") + UID.substring(0,2) + MD5Util.MD5Encode("2018", "") ;
		} catch (Exception e) {
			LogUtil.errorLog(e.getMessage());
			return "";
		}
	}
	
	/**
	 * 
	 */
	public static class Spring {

		/**
		 * 将数据库表名转化成对象名，即首字母大写
		 * 
		 * @param tableName
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static <T> T getBean(String beanName) {
			return ((T) SpringApplicationContext.getBean(beanName));
		}

	}
}
