package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.IotTriggerInfoBO;
import com.enji_iot.util.Util.PageBean;
import net.sf.json.JSONArray;

import java.util.Map;

public interface IotTriggerInfoService {
    Map<String,Object> selectPageList(IotTriggerInfoBO iotTriggerInfo, PageBean pageBean);
    Map<String,Object> insert(IotTriggerInfoBO iotTriggerInfo);
    Map<String,Object> update(IotTriggerInfoBO iotTriggerInfo);
    Map<String,Object> delete(IotTriggerInfoBO iotTriggerInfo);
    Map<String,Object> selectOne(IotTriggerInfoBO iotTriggerInfo);
    Map<String,Object> selectList(IotTriggerInfoBO iotTriggerInfo);

    JSONArray selectCount(IotSceneInfoBO sceneInfo);
}
