package com.enji_iot.cache.Cache;

/**
 * 
 * cache的键名
 * 
 *
 */
public class CacheName {

	/**
	 * 项目字典 code ->obj
	 */
	public final static String DICTIONARY = "Dictionary";
	/**
	 * 项目字典，一级子列表 code -> obj.subList
	 */
	public final static String DICTIONARY_RELATION = "DictionaryRelationSub";
	/**
	 * 用户缓存 userkey -> obj
	 */
	public final static String USERINFO = "UserInfo";
	/**
	 * 中间件LPM缓存 lpmKey -> obj
	 */
	public final static String LPMINFO = "LpmInfo" ;
	/**
	 * 传感器缓存 id->obj
	 */
	public final static String SENSORINFO = "SensorInfo";
	/**
	 * 传感器缓存 nodeid,sensor_device_id,portid -> obj
	 */
	public final static String SENSORINFO_NSP = "SensorInfoNsp";
	/**
	 * 节点缓存 id -> obj
	 */
	public final static String NODEINFO = "NodeInfo";
	/**
	 * 节点缓存 device_code -> obj
	 */
	public final static String NODEINFO_DEVICECODE = "NodeInfoDeviceCode";
	/**
	 * 场景缓存  id -> obj
	 */
	public final static String SCENEINFO = "SceneInfo";
	
	/**
	 * 传感器触发器列表缓存 obj.getNode_id()+"-"+ obj.getSensor_device_id()+"-"+obj.getPort_id() -> obj . TriggerList
	 */
	public final static String SENSORTRIGGERINFO = "SensorTriggerInfo" ;
	
	/**
	 * 设备离线触发器缓存
	 */
	public final static String NODETRIGGERINFO = "NodeTriggerInfo" ;
	
	
	/**
	 * 网关注册 LPM缓存  device_code -> lpmkey
	 */
	public final static String DEVICECODE_LPM ="DeviceCodeToLpmInfo";
	
	/**
	 * 视频设备信息缓存
	 */
	public final static String VIDEO_INFO = "VideoInfo";
	
	/**
	 * open_id , userinfo 缓存
	 */
	public final static String USERINFO_OPENID = "UserInfoOpenId";
	
	public final static String USERACCOUNT_ID = "UserAccountId" ;
	
	/**
	 * 用户短信缓存
	 */
	public final static String USER_SMS = "UserSms" ;
	
	/**
	 * 传感器设置缓存
	 */
	public final static String SENSOR_PARAM_SETTING = "SensorSetting" ;
	
	
	public final static String SCENE_IPDATE_FLAG = "SceneUpdateFlag" ;

	/**
	 * 设备保活
	 */
	public final static String NODE_KEEPALIVE = "NodeKeepAlive" ;

	/**
	 * 传感器缓存 （设备捆绑的）
	 */
	public final static String SENSOR_DEV_INFO = "SensorDevInfo";

	/**
	 * 设备传感器单位缓存 nodeUnit.getScene_id() + nodeUnit.getDevice_code() + nodeUnit.getSensor_name()
	 */
	public final static String IOT_NODE_UNIT = "IotNodeUnit";

}
