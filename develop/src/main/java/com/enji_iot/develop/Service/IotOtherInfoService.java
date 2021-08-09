package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.UserInfoBO;

import java.util.Map;

public interface IotOtherInfoService {

	/**
	 * 获取用户系统内的数据
	 * @return
	 */
	Map<String, Object> getAppStatusInfo(UserInfoBO user);

}
