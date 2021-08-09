package com.enji_iot.mqtt.DAO;

import com.enji_iot.util.Entity.bean.IotNodeUnitData;
import com.enji_iot.util.Entity.bo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Repository(value = "MQTTMapper")
public interface MQTTMapper {
    ContactUserInfoBO selectOne(@Param(value = "contactUserInfo")ContactUserInfoBO contactUserInfo);
    Integer updateNodeStatus(@Param(value = "nodeInfo")IotNodeInfoBO nodeInfoBO);
    Integer updatevoicenum(@Param(value = "userAccountInfo") UserAccountInfoBO userAccountInfo);
    Integer updatesmsnum(@Param(value = "userAccountInfo") UserAccountInfoBO userAccountInfo);
    List<IotNodeInfoBO> selectList(@Param(value = "nodeInfo")IotNodeInfoBO nodeInfoBO);
    Integer insert(@Param(value = "nodeInfo")IotNodeInfoBO nodeInfoBO);
    Integer insertIotAlarmInfo(@Param(value = "alarmInfo") IotAlarmInfoBO alarmInfoBO);
    Integer insertAlarmTriggerRecord(@Param(value = "alarmTriggerRecord") AlarmTriggerRecordBO alarmTriggerRecord);
    Integer insertIotHistoryNodeData(@Param(value = "historyNodeData") IotHistoryNodeDataBO historyNodeDataBO);
    Integer insertIotHistoryTriggerInfo(@Param(value = "historyTriggerInfo") IotHistoryTriggerInfoBO historyTriggerInfoBO);
}