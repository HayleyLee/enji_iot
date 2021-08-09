package com.enji_iot.util.Common;

/**
 * 
 */
public class RequestURL {
	
	public class Base {
		
	}
	/**
	 * 数据字典
	 *
	 */
	public class ProDictionaryInfo {
		
		public final static String PRO_DICTIONARY_INFO = "/dictionary";

		public final static String GET_PRO_DICTIONARY_INFO = "/get-dictionary";
		
		public final static String PRO_DICTIONARY_INFO_PAGE = "/page/dictionary";
		//todo not use
		public final static String PRO_DICTIONARY_INFO_SEL = "/dictionary/{p_code}" ;
		//todo not use
		public final static String GEN_DICTIONARY_INFO_SEL = "/gen/dictionary" ;
		
	}
	
	/**
	 * 用户
	 *
	 */
	public class User{
		
		public final static String USER_REGISTER = "/user/register";
		//todo not use
		public final static String WP_USER_LOGIN = "/wp/user/login" ;
		
		/**
		 * WP 微信小程序
		 */
		//todo not use
		public final static String WP_USER_BIND = "/wp/user/bind";
		
		/**
		 * wx公众号绑定
		 */
		//todo not use
		public final static String WX_USER_BIND = "/wx/user/bind";
		
		public final static String USER_LOGIN = "/user/login";
		//todo not use
		public final static String USER_LOGOUT = "/user/logout";
		
		public final static String USER_ID = "/user/{id}";
		
		public final static String USERS = "/users";
		
		public final static String USER = "/user";
		
		public final static String USER_MODIFY_PASSWORD = "/user/modify/password" ;
		
		public final static String USER_INFO = "/user/info";

		public final static String USER_SCREEN = "/user/user-screen";
		
		public final static String USER_INFO_MODIFY = "/user/self";
		
		public final static String USER_PAGE = "/page/user";
		
		public final static String RESET_PASSWORD = "/user/reset/password";
		
		/**
		 * 账号验证码
		 */
		public final static String ACCOUNT_SECURITY_CODE = "/security_code/{phone}";
		/**
		 * 获取验证码
		 */
		public final static String VALIDATE_BY_NAME = "/validate/{name}";
		/**
		 * 图片验证码
		 */
		public final static String IMG_SECURITY_CODE = "/security_code/img";
		//todo not use
		public final static String MAIL_MESSAGE = "/mail/{validatecode}" ;
		//todo not use
		public final static String MAIL_RESET_PASSWORD_MESSAGE = "/mail/reset/password/{validatecode}" ;
	}
	
	public static final class FileInfo {
		//todo not use
		public static final String FILE_INFO_PAGE = "/page/fileInfo";
		//todo not use
		public static final String FILE_INFO = "/fileInfo";
		//todo fontEnd has /node/upload but in here is /upload
		public static final String UPLOAD = "/upload";
		//todo not use
		public static final String FILE_SENSOR_ICO = "/sensor/ico/upload" ;
		// base64 保存图片
		//todo not use
		public static final String BASE64_FILE_INFO = "/base64/fileInfo" ;

	}
	
	public static final class SysConfigInfo {
		public static final String SYS_CONFIG_INFO_PAGE ="/sys/confog/page/info" ;
		
		public static final String SYS_CONFIG_INFO ="/sys/confog/info" ;
	}

	public static final class VideoStream {
		public static final String VIDEO_LIVE_STREAM = "/get_hls_url_by_videoId";
	}
}