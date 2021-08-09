package com.enji_iot.data.DAO;

import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotHistorySensorDataBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotScreenHistoryNodeInfoMapper {
    List<IotHistorySensorDataBO> selectGroupByPage(@Param(value = "iotHistoryNodeInfo") IotHistorySensorDataBO iotHistorySensorData);
    List<IotHistorySensorDataBO> selectPage(@Param(value = "iotHistoryNodeInfo") IotHistorySensorDataBO iotHistorySensorData);
    IotHistorySensorDataBO selectOne(@Param(value = "iotHistoryNodeInfo") IotHistorySensorDataBO iotHistorySensorData);
    Integer update(@Param(value = "iotHistoryNodeInfo") IotHistorySensorDataBO iotHistorySensorData);
    List<IotHistoryNodeDataBO> selectHistoryNodeData(@Param(value = "iotHistoryNodeInfo") IotHistoryNodeDataBO iotHistoryNodeData);
    Integer selectPageCount(@Param(value = "iotHistoryNodeInfo") IotHistorySensorDataBO iotHistorySensorData);
    Integer selectHistoryNodeCount(@Param(value = "iotHistoryNodeInfo") IotHistoryNodeDataBO iotHistoryNodeData);
    Integer insert(@Param(value = "iotHistoryNodeInfo") IotHistoryNodeDataBO iotHistoryNodeData);
}
