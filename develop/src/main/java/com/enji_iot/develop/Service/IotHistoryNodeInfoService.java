package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotHistorySensorDataBO;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.PageBean;
import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

public interface IotHistoryNodeInfoService {
    Map<String,Object> selectGroupByPage(IotHistorySensorDataBO iotHistorySensorData, PageBean pageBean);
    Map<String,Object> selectGroupByPage(IotHistorySensorDataBO iotHistorySensorData, PageBean... pageBean);
    Map<String,Object> selectPageList(IotHistorySensorDataBO iotHistorySensorData, PageBean pageBean);
    Map<String,Object> selectPageList(IotHistorySensorDataBO iotHistorySensorData, PageBean... pageBean);
    Map<String,Object> selectHistoryNodeData(IotHistoryNodeDataBO iotHistoryNodeData);
    List<IotHistoryNodeDataBO> singleSelectHistoryNodeData(IotHistoryNodeDataBO iotHistoryNodeData);
    Integer selectHistoryNodeCount(IotHistoryNodeDataBO iotHistoryNodeData);
    Map<String,Object> insert(IotHistoryNodeDataBO iotHistoryNodeData);
    Map<String,Object> update(IotHistorySensorDataBO iotHistorySensorData);
    Map<String,Object> selectOne(IotHistorySensorDataBO iotHistorySensorData);
    Map<String,Object> selectList(IotHistoryNodeDataBO iotHistoryNodeData);

    JSONArray selectCount(IotSceneInfoBO scene);
}
