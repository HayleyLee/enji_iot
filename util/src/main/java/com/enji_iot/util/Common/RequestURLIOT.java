package com.enji_iot.util.Common;

/**
 * 
 */
public class RequestURLIOT {

	/**
	 * 场景信息
	 *
	 */
	public static class SceneInfo {

		public final static String SCENE_INFO = "/scene";

		public final static String ADMIN_SCENE_INFO_PAGE = "/admin/page/scene";
		
		public final static String SCENE_INFO_PAGE = "/page/scene";
		//todo fontEnd is use it, but in here is not
		public final static String SCENE_INFO_ALL = "/page/scene-all";

		public final static String SCENE_TRIGGER_SENSOR = "/scene/trigger-sensor";
		
		public final static String SELF_SCENE_INFO_PAGE = "/self/page/scene";
		
		public final static String SCENE_DETAILL = "/scene/detail" ;

		public final static String SCENE_COUNT = "/scene/count";
	}

	public static class NodeInfo {
		public final static String NODE_INFO = "/node";
		
		public final static String NODE_BIND = "/node/bind" ;

		public final static String NODE_UPDATE = "/node/update" ;

		public final static String ADMIN_NODE_INFO_PAGE = "/admin/page/node";
		
		public final static String ADMIN_UNUSED_NODES = "/admin/unused/nodes";
		
		public final static String NODE_INFO_PAGE = "/page/node";

		public final static String NODE_STATISTIC = "/node/statistic";
		//todo fontEnd is use it, but in here is not
		public final static String NODE_GUARANTEE = "/node/guarantee";
		//todo fontEnd is use it, but in here is not
		public final static String NODE_INFO_MAP = "/page/node-map";
		//todo not use
		public final static String NODE_STATUS_INFO = "/node/status";
		
		public final static String NODE_INFO_INFO_PAGE = "/page/node/list";

		public final static String NODE_INFO_SENSOR_INFO_DEV = "/page/node/sensor_dev/list";
		//todo not use
		public final static String NODE_INFO_SENSOR_INFO_PAGES = "/page/node/sensor/lists";

		public final static String ADD_NODE_UNIT = "/add/node/unit";

		public final static String PUT_NODE_UNIT = "/put/node/unit";
		//todo not use
		public final static String DEL_NODE_UNIT = "/del/node/unit";

		public final static String SELECT_NODE_UNIT_LISTS = "/select/node/unit/list";

		public final static String NODE_HISTORY_INFO_NODE = "/node/history";

		public final static String NODE_HISTORY_DATA = "/node/history/data";

		public final static String NODE_CONTROL_VALUE = "/node/control/realtime/update";

		public final static String NODE_COUNT = "/node/count";



	}

	//todo not use
	public static class SensorHistoryInfo {
		public final static String SENSOR_HISTORY_INFO = "/sensor/history";

		public final static String SENSOR_HISTORY_INFO_PAGE = "/page/sensor/history";
		//todo only this's use it
		public final static String SENSOR_HISTORY_INFO_NODE_COUNT = "/page/node/history";

		public final static String SENSOR_HISTORY_INFO_LIST = "/list/sensor/history";

		public final static String SENSOR_HISTORY_INFO_INFO = "/sensor/history/excel";
		
		public final static String SENSORS_HISTORY_DATA = "/sensor/history/data" ;
		public final static String HISTORY_COUNT = "/history/count" ;
	}

	public static class AlarmInfo {
		public final static String ALARM_INFO = "/alarm";

		public final static String ALARM_INFO_PAGE = "/page/alarm";
		
		public final static String ALARM_INFO_STATISTIC = "/alarm/info/statistic";

		public final static String ALARM_INFO_ALL = "/page/alarm-all";
		//todo not use
		public final static String ALARM_INFO_EXCEL = "/alarm/excel";

		public final static String ALARM_INFO_READ = "/alarm/read";

		public final static String ALARM_INFO_UNREAD = "/alarm/unread";
	}

	public static class TriggerInfo {
		public final static String TRIGGER_INFO = "/trigger";

		public final static String TRIGGER_INFO_PAGE = "/page/trigger";

		public final static String DEL_TRIGGER_INFO = "/del/trigger";

		public final static String TRIGGER_COUNT = "/trigger/count";
	}

	public static class TriggerHistoryInfo {
		//todo not use
		public final static String TRIGGER_HISTORY_INFO = "/trigger/history";

		public final static String TRIGGER_HISTORY_INFO_PAGE = "/page/trigger/history";
		//todo not use
		public final static String TRIGGER_HISTORY_INFO_EXCEL = "/trigger/history/excel";
	}

	public static class SceneUserRelation {
		//todo not use
		public final static String SCENE_USER_RELATION = "/relation/scene/user";
		
		public final static String SCENE_USER_RELATION_CHANGE = "/relation/scene/user/change";

		public final static String SCENE_USER_RELATION_PAGE = "/page/relation/scene/user";
	}

	public static class ContactUserInfo {
		public final static String CONTACT_USER_INFO = "/contact/user/info";

		public final static String CONTACT_USER_INFO_PAGE = "/page/contact/user/info";
	}

	public static class IotLpmInfo {

		public static final String IOT_LPM_INFO_PAGE = "/page/lpm";
		//todo not use
		public static final String IOT_LPM_INFO = "/lpm";

	}

	/**
	 * 海康账户信息
	 * 
	 * @author chenrj
	 *
	 */
	//todo not use
	public static class HkAccountInfo {

		public static final String HK_ACCOUNT_INFO_PAGE = "/page/hkaccount";

		public static final String HK_ACCOUNT_INFO = "/hkaccount";
	}

	/**
	 * 视频设备信息
	 * 
	 * @author chenrj
	 *
	 */
	public static class IotVideoInfo {
		public static final String IOT_VIDEO_INFO = "/video";

		public static final String IOT_VIDEO_INFO_PAGE = "/page/video";
	}

	/**
	 * 视频图片表
	 * /video
	 * @author chenrj
	 *
	 */
	//todo not use
	public static class VideoFileInfo {
		public static final String VIDEO_FILE_INFO = "/video/file";

		public static final String VIDEO_FILE_INFO_PAGE = "/page/video/file";
	}

	/**
	 * 视频记录表
	 * 
	 * @author chenrj
	 *
	 */
	public static class IotVideoRecord {
		//todo not use
		public static final String IOT_VIDEO_RECORD = "/video/record";

		public static final String IOT_VIDEO_RECORD_PAGE = "/page/video/record";
	}

	/**
	 * 视频服务器回调信息
	 * 
	 * @author chenrj
	 *
	 */
	public static class IotVideoCall {

		public static final String IOT_VIDEO_CLIENTS = "/live/clients";

		public static final String IOT_VIDEO_STREAMS = "/live/streams";

		public static final String IOT_VIDEO_SESSIONS = "/live/sessions";

		public static final String IOT_VIDEO_DVRS = "/live/dvrs";
	}

	/**
	 * Mqtt server recall
	 * 
	 * @author chenrj
	 *
	 */
	public static class IotMqttCall {
		//todo not use
		public static final String IOT_MQTT_AUTH_CLIENT = "/mqtt/auth";

		public static final String IOT_MQTT_CLIENT_NOTICE = "/mqtt/notice";

	}

	public static final class DeviceTemplateInfo {

		public static final String DEVICE_TEMPLATE_INFO_PAGE = "/page/deviceTemplateInfo";

		public static final String DEVICE_TEMPLATE_INFO = "/deviceTemplateInfo";

	}

	public static final class DeviceTemplateSensorInfo {

		public static final String DEVICE_TEMPLATE_SENSOR_INFO_PAGE = "/page/deviceTemplateSensorInfo";
		//todo not use
		public static final String DEVICE_TEMPLATE_SENSOR_INFO = "/deviceTemplateSensorInfo";

	}

	public static final class DeviceTemplateTiggerInfo {
		//todo not use
		public static final String DEVICE_TEMPLATE_TIGGER_INFO_PAGE = "/page/deviceTemplateTiggerInfo";

		public static final String DEVICE_TEMPLATE_TIGGER_INFO = "/deviceTemplateTiggerInfo";

	}
	//todo not use
	public static final class AlarmTriggerStatistic{
		
		public static final String ALARM_TRIGGER_STATISTIC_PAGE = "/page/alarm/statistic" ;
		
		public static final String ALARM_TRIGGER_STATISTIC = "/alarm/statistic" ;
		
	}
	
	public static final class AlarmTriggerRecord{
		
		public static final String ALARM_TRIGGER_RECORD_PAGE = "/page/alarm/record" ;
		
		public static final String ALARM_TRIGGER_RECORD_STATISTIC_PAGE = "/page/alarm/record/statistic" ;
		
		public static final String ALARM_TRIGGER_RECORD = "/alarm/record" ;
		
		public static final String ALARM_TRIGGER_RECORD_UPDATE = "/alarm/record/update" ;
		
	}
	
	public static final class UserAccountInfo {
		
		public static final String USER_ACCOUNT_INFO = "/user/account";
		
	}

	public static final class IotScreen {
		//todo not use
		public static final String IOT_SCREEN = "/screen";

		public final static String SCENE_INFO_PAGE = "/page/scene";

		public final static String ALARM_INFO_STATISTIC = "/alarm/info/statistic";

		public final static String NODE_STATISTIC = "/node/statistic";

		public final static String ALARM_INFO_PAGE = "/page/alarm";

		public final static String NODE_INFO_PAGE = "/page/node";

		public final static String SELECT_NODE_UNIT_LISTS = "/select/node/unit/list";

		public final static String NODE_HISTORY_INFO_NODE = "/node/history";

		public final static String NODE_INFO_ALL = "/page/node-all";

		public final static String SENSOR_HISTORY_INFO_NODE_COUNT = "/page/node/history";

		public final static String ALARM_INFO_ALL = "/page/alarm-all";

	}
}