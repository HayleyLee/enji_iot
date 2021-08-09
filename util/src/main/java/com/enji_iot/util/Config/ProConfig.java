package com.enji_iot.util.Config;

import com.enji_iot.util.Util.PropertiesUtil;

/**
 * config 属性文件
 *
 */
public class ProConfig {

	public final static long VERSION = System.currentTimeMillis();

	public static String LOCAL_DOMAIN= "" ;

	public static String SERVER_DOMAIN= "http://182.254.183.87" ;

	public static String IMAGE_DOMAIN = "" ;

	public static String VIDEO_DOMAIN = "" ;

	public static String LOCAL_FILE_PATH = "";

	public static String PROJECT_NAME = "" ;

	public static String SYS_WEB_LOGIN_NAME = "";

	public static String SYS_APP_LOGIN_NAME = "";

	public static String SYS_INFO_NAME = "";

	public static String SYS_TECH_HELP = "";

	public static String SYS_BEIAN_NAME = "";

	public static String APP_TIME_TASK_PERIOD = "";

	public final static String PAGE_SIZE = PropertiesUtil.getProperty("page.size");

	public final static String DEV_MODE = PropertiesUtil.getProperty("dev.mode");

	public final static String BD_API_GEOCODER = PropertiesUtil.getProperty("bd.api.geocoder");

//	public final static String SENSOR_ICON_SELF_FLAG = PropertiesUtil.getProperty("sensor.icon.self.flag") ;

	public static class Mail{
		public final static String HOST =  PropertiesUtil.getProperty("mail.config" ,"mail.host") ;
		public final static String PORT=  PropertiesUtil.getProperty("mail.config" ,"mail.port");
		public final static String USERNAME =  PropertiesUtil.getProperty("mail.config" ,"mail.username");
		public final static String PASSWORD =  PropertiesUtil.getProperty("mail.config" ,"mail.password");
		public final static String SMTP_AUTH =  PropertiesUtil.getProperty("mail.config" ,"mail.smtp.auth");
		public final static String SMTP_TIMEOUT =  PropertiesUtil.getProperty("mail.config" ,"mail.smtp.timeout");
		public final static String DEFAULT_FROM =  PropertiesUtil.getProperty("mail.config" ,"mail.default.from");
	}

	public static class Map{
		public final static String BAIDU_MAP_KEY =  PropertiesUtil.getProperty("map.config" ,"baidu.map.api.key") ;
	}

	public static class ShortMessageYunpian{
		/**
		 * api_key
		 */
		public final static String API_KEY = PropertiesUtil.getProperty("sms.config", "sms.yunpian.api_key");
		/**
		 * 验证码模板ID
		 */
		public final static String TPL_ID_VALIDATE_CODE = PropertiesUtil.getProperty("sms.config",
				"sms.yunpian.tpl.id.validate.code");
		/**
		 * /** 签名
		 */
		public final static String SIGNATURE = PropertiesUtil.getProperty("sms.config", "sms.yunpian.signature");
	}


	public static class AliyunShortMessage{
		/**
		 *
		 */
		public final static String ACCESSKEY = PropertiesUtil.getProperty("sms.config", "aliyun.accesskey");

		public final static String ACCESSKEYSECRET = PropertiesUtil.getProperty("sms.config", "aliyun.accesskeysecret");

		public final static String SIGNATURE = PropertiesUtil.getProperty("sms.config", "aliyun.signname");

		public final static String CALLEDSHOWNUMBER = PropertiesUtil.getProperty("sms.config", "aliyun.calledshownumber");

		public final static String SMS_TEMPCODE1 = PropertiesUtil.getProperty("sms.config", "aliyun.sms.templatecode1");
		public final static String SMS_TEMPCODE2 = PropertiesUtil.getProperty("sms.config", "aliyun.sms.templatecode2");
		public final static String SMS_TEMPCODE3 = PropertiesUtil.getProperty("sms.config", "aliyun.sms.templatecode3");
		public final static String SMS_TEMPCODE4 = PropertiesUtil.getProperty("sms.config", "aliyun.sms.templatecode4");

		public final static String VOICE_TEMPLATE1 = PropertiesUtil.getProperty("sms.config", "aliyun.voice.templatecode1");
		public final static String VOICE_TEMPLATE2 = PropertiesUtil.getProperty("sms.config", "aliyun.voice.templatecode2");
		public final static String VOICE_TEMPLATE3 = PropertiesUtil.getProperty("sms.config", "aliyun.voice.templatecode3");
		public final static String VOICE_TEMPLATE4 = PropertiesUtil.getProperty("sms.config", "aliyun.voice.templatecode4");

		public final static String SMS_TEMPLATE_CODE = PropertiesUtil.getProperty("sms.config", "aliyun.validate.code");

	}


	public static class MQTT {

		public static final String USERNAME = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.username") ;

		public static final String PASSWORD = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.password") ;

		public static final String MQTTSIMPLEURI = PropertiesUtil.getProperty("mqtt.config" ,"mqtt.simpleURI") ;

	}

	/**
	 * 微信公众号配置
	 * @author chenrj
	 *
	 */
	public static class WEIXIN{

		public final static String MP_OAUTH2_REDIRECT_URI = PropertiesUtil.getProperty("weixin.config","weixin.mp.oauth2.redirect_uri");

		public final static String MP_NOTIFY_URL = LOCAL_DOMAIN + PropertiesUtil.getProperty("weixin.config","weixin.pay.notify_url");

		public final static String APP_ID =  PropertiesUtil.getProperty("weixin.config" ,"weixin.mp.appid");

		public final static String MCH_ID =  PropertiesUtil.getProperty("weixin.config" ,"weixin.mp.mch.id");

		public final static String MCH_SERECT =  PropertiesUtil.getProperty("weixin.config" ,"weixin.mp.mch.key");

		/**
		 *  传感点报警消息
		 */
		public final static String NOTICE_1 =  PropertiesUtil.getProperty("weixin.config" ,"weixin.msg.tpl.NOTICE_1");
		/**
		 *
		 */
		public final static String NOTICE_2 =  PropertiesUtil.getProperty("weixin.config" ,"weixin.msg.tpl.NOTICE_2");
		/**
		 *
		 */
		public final static String NOTICE_3 =  PropertiesUtil.getProperty("weixin.config" ,"weixin.msg.tpl.NOTICE_3");
		/**
		 *
		 */
		public final static String NOTICE_4 =  PropertiesUtil.getProperty("weixin.config" ,"weixin.msg.tpl.NOTICE_4");

		/**
		 *
		 */
		public final static String NOTICE_5 =  PropertiesUtil.getProperty("weixin.config" ,"weixin.msg.tpl.NOTICE_5");

		/**
		 *
		 */
		public final static String NOTICE_6 =  PropertiesUtil.getProperty("weixin.config" ,"weixin.msg.tpl.NOTICE_6");

	}

}
