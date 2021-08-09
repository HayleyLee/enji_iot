package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.IotHistoryTriggerInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotHistoryTriggerInfoService {
    Map<String,Object> selectPageList(IotHistoryTriggerInfoBO iotHistoryTriggerInfo, PageBean pageBean);
    Map<String,Object> select(IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    Map<String,Object> insert(IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    Map<String,Object> update(IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    Map<String,Object> selectOne(IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
}
