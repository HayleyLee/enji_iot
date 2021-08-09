package com.enji_iot.data.DAO;

import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeUnitDataBO;
import com.enji_iot.util.Entity.bo.IotStatisticBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotScreenNodeInfoMapper {
    IotNodeInfoBO selectOne(@Param(value = "iotNodeInfo") IotNodeInfoBO iotNodeInfo);
    List<IotNodeInfoBO> selectPage(@Param(value = "iotNodeInfo") IotNodeInfoBO iotNodeInfo);
    Integer selectPageCount(@Param(value = "iotNodeInfo") IotNodeInfoBO iotNodeInfo);
    Integer insert(@Param(value = "iotNodeInfo") IotNodeInfoBO iotNodeInfo);
    Integer update(@Param(value = "iotNodeInfo") IotNodeInfoBO iotNodeInfo);
    List<IotStatisticBO> selectStatisticNodeInfo(@Param(value = "iotNodeInfo") IotNodeInfoBO iotNodeInfo);
    List<IotNodeUnitDataBO> selectNodeUnitList(@Param(value = "iotNodeUnitData") IotNodeUnitDataBO iotNodeUnitData);
}
