package com.enji_iot.data.Service;

import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotHistorySensorDataBO;
import com.enji_iot.util.Util.PageBean;

import java.util.List;
import java.util.Map;

public interface IotScreenHistoryNodeInfoService {
    Map<String,Object> selectHistoryNodeData(IotHistoryNodeDataBO iotHistoryNodeData);
    Map<String,Object> insert(IotHistoryNodeDataBO iotHistoryNodeData);
    Map<String,Object> update(IotHistorySensorDataBO iotHistorySensorData);
    Map<String,Object> selectOne(IotHistorySensorDataBO iotHistorySensorData);
    Map<String,Object> selectList(IotHistoryNodeDataBO iotHistoryNodeData);
}
