package com.enji_iot.util.Common;

public class Code {
	
	/**
	 * 返回状态
	 * @author chenrj
	 *
	 */
	public static class ResponseCode{
		
		public static class SystemCode{
			
			public final static Integer OK = 2 ;
			
			public final static Integer ERROR = 3 ;
			
			public final static Integer NO_DATA = 4 ;
			
			public final static Integer PARAM_ERROR = 5;
			
			public final static Integer EXEC_FAIL = 9;
			
			public final static Integer NO_AUTHORIZATION = 11;
			
			public final static Integer ACTIVE_CODE_OVERDUE = 62;
			
			public final static Integer ACTIVED= 63 ;
			
			public final static Integer NO_ACTIVE_CODE = 65 ;
			
			public final static Integer VALIDATER_ALLER = 93 ;
			
			public final static Integer CODE_TIME_ERROR = 94 ;
			
			public final static Integer CODE_ERROR = 95 ;
			
			public final static Integer PASSWORD_ERROR = 14 ;
			
		}
		
		public static class UserInfo{
			
			public final static Integer USER_EXIST = 12 ;
			
			public static final Integer USER_NOT_EXISTS = 13;
			
			public final static Integer USERNAME_OR_PASSWORD_ERROR = 14 ;
			
			public final static Integer NAME_EXIST = 66 ;
			
			public final static Integer EMAIL_EXIST = 67 ;
			
			public final static Integer PHONE_EXIST = 68 ;
			
		}
		
		public static class IotInfo{
			public final static Integer DEVICE_CODE_EXIST = 70 ;
			
			public final static Integer DEVICE_CODE_NOT_EXIST = 380 ;
			
			public final static Integer DEVICE_CODE_USED = 381 ;
			
			public final static Integer VIDEO_INFO_REPEAT = 86 ;
		}
		
	}
	
	/**
	 * 用户类型
	 * @author chenrj
	 *
	 */
	public static class UserType{
		
		public final static Integer Normal = 7 ;
		
		public final static Integer MANAGER = 8 ;
		
		public final static Integer SUPER = 10 ;
		
	}
	
	public static class UserStatus{
		public final static Integer UN_ACTIVED = 59 ;
		public final static Integer NORMAL = 60 ;
		public final static Integer FORBIDDEN = 61;
	}
	
}