package com.enji_iot.util.Common;

public class CodeIot extends Code {
	
	/**
	 * 设备打开数值
	 */
	public static class DEVICE_STATUS_VALUE{
		public static final Integer DEVICE_OPEN = 65535 ;
		
		public static final Integer DEVICE_CLOSE = 0 ;
	}
	
	/*
	 * 传感器类型
	 */
	public static class SENSOR_TYPE {
		public static final Integer POSITION = 90 ;
	}
	
	/**
	 * 传感器单位
	 */
	public static class SENSOR_MEASURE_UNIT_TYPE {
		
		public static final Integer LOCALON = 92 ;
		
	}
	
	
	
	/**
	 * 设备状态
	 * @author chenrj
	 *
	 */
	public static class DEVICE_STATUS{
		
		public static final Integer ONLINE = 16 ;
		
		public static final Integer OFFLINE = 17 ;
		
		public static final Integer UNCONTECT = 18;
		
		public static final Integer FAILURE = 19 ;
	}
	/**
	 * 触发器状态
	 * @author chenrj
	 *
	 */
	public static class IOT_TRIGGER_STATUS{
		
		public static final Integer NORMAL = 43 ;
		
		public static final Integer STOP =  44 ;
	}
	
	/**
	 * 触发器类型
	 * @author chenrj
	 *
	 */
	public static class TRIGGER_CONDITION_TYPE{
		
		public static final Integer OVERTOPX = 29;
		
		public static final Integer UNDERY = 30 ;
		
		public static final Integer XY_OVERMIDDLE = 31 ;
		
		public static final Integer EQUAL =32 ;
		
		public static final Integer OVERTOPX_OVERTIME = 33;
		
		public static final Integer UNDERY_OVERTIME =34 ;
		
		public static final Integer EXCEPTION_DATA_SOLVE = 189 ;
	}
	
	/**
	 * 报警开关
	 */
	public static class ALARM_FLAG{
		public static final Integer OPEN = 36 ;
		
		public static final Integer CLOSE = 37 ;
	}
	
	/**
	 * 触发类型
	 */
	public static class ACTION_TYPE {
		public static final Integer CONTROL_DEVICE = 50 ;
		public static final Integer MESSAGE_WECHAT = 51 ;
		public static final Integer MESSAGE_SMS = 52 ;
		public static final Integer MESSAGE_MAIL = 53 ;
		public static final Integer MESSAGE_VOICE = 360 ;
	}
	
	/**
	 * 处理标志
	 */
	public static class PROCESS_STATUS {
		public static final Integer NO = 46 ;
		public static final Integer YES = 47 ;
		public static final Integer TAGGING = 48 ;
	}
	
	public static class IOT_NODE_STATUS{
		public static final Integer HTTP = 82;
		public static final Integer MQTT = 84;
		public static final Integer TCP = 83;
		
		public static final Integer UDP = 105 ;
	}
	
}