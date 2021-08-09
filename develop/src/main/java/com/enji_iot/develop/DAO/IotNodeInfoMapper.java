package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bean.IotNodeUnitData;
import com.enji_iot.util.Entity.bo.CommonInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeUnitDataBO;
import com.enji_iot.util.Entity.bo.IotStatisticBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotNodeInfoMapper {
    List<IotNodeInfoBO> selectNodeSensorList(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer selectNodeSensorListCount(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    IotNodeInfoBO selectOne(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer updateNodeStatus(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer updateAllOffLine(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    List<IotNodeInfoBO> selectList(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    List<IotNodeInfoBO> select(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    List<IotNodeInfoBO> selectNodeDevCode(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    List<IotNodeInfoBO> selectPage(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer selectPageCount(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    List<IotNodeInfoBO> selectUnUsedNode(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer selectUnUsedNodeCount(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer insert(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer update(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer deleteNode(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    Integer addUnit(@Param(value = "iotNodeUnitData") IotNodeUnitDataBO iotNodeUnitData);
    List<CommonInfoBO> selectNodeNumGroup(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    List<IotStatisticBO> selectStatisticNodeInfo(@Param(value = "iotNodeInfo")IotNodeInfoBO iotNodeInfo);
    List<IotNodeUnitDataBO> selectNodeUnitList(@Param(value = "iotNodeUnitData") IotNodeUnitDataBO iotNodeUnitData);
    Integer deviceUnitUpdate(@Param(value = "iotNodeUnitData")IotNodeUnitDataBO iotNodeUnitData);
    Integer deviceUnitDelete(@Param(value = "iotNodeUnitData")IotNodeUnitDataBO iotNodeUnitData);

    Integer selectCount(@Param(value = "iotNodeInfo")IotNodeInfoBO nodeInfo);
}
