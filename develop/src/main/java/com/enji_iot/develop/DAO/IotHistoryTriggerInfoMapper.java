package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotHistoryTriggerInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotHistoryTriggerInfoMapper {
    List<IotHistoryTriggerInfoBO> selectPage(@Param(value = "iotHistoryTriggerInfo")IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    List<IotHistoryTriggerInfoBO> select(@Param(value = "iotHistoryTriggerInfo")IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    Integer selectPageCount(@Param(value = "iotHistoryTriggerInfo")IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    Integer insert(@Param(value = "iotHistoryTriggerInfo")IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    Integer update(@Param(value = "iotHistoryTriggerInfo")IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
    IotHistoryTriggerInfoBO selectOne(@Param(value = "iotHistoryTriggerInfo")IotHistoryTriggerInfoBO iotHistoryTriggerInfo);
}
