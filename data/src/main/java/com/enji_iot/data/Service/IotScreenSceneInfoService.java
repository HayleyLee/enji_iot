package com.enji_iot.data.Service;

import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotScreenSceneInfoService {

	/**
	 * 获取场景的详情信息
	 * @param iotSceneInfo
	 * @return
	 */
	Map<String, Object> selectOne(IotSceneInfoBO iotSceneInfo);
	Map<String, Object> selectPageList(IotSceneInfoBO iotSceneInfo, PageBean pageBean);
	Map<String, Object> insert(IotSceneInfoBO iotSceneInfo);
	Map<String, Object> update(IotSceneInfoBO iotSceneInfo);
}
