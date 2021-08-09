package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.PageBean;
import net.sf.json.JSONArray;

import java.util.Map;

public interface IotSceneInfoService {

	/**
	 * 获取场景的详情信息
	 * @param iotSceneInfo
	 * @return
	 */
	Map<String, Object> getSceneDetailInfo(IotSceneInfoBO iotSceneInfo);
	Map<String, Object> selectOne(IotSceneInfoBO iotSceneInfo);
	Map<String, Object> selects(IotSceneInfoBO iotSceneInfo);
	Map<String, Object> selectPageList(IotSceneInfoBO iotSceneInfo, PageBean pageBean);
	Map<String, Object> selectSceneInfo(IotSceneInfoBO iotSceneInfo, PageBean pageBean);
	Map<String, Object> insert(IotSceneInfoBO iotSceneInfo);
	Map<String, Object> update(IotSceneInfoBO iotSceneInfo);

    JSONArray sceneCount(IotSceneInfoBO sceneInfo);
}
