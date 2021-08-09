package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotTriggerInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotTriggerInfoMapper {
    Integer selectPageCount(@Param(value = "iotTriggerInfo") IotTriggerInfoBO iotTriggerInfo);
    Integer insert(@Param(value = "iotTriggerInfo") IotTriggerInfoBO iotTriggerInfo);
    Integer update(@Param(value = "iotTriggerInfo") IotTriggerInfoBO iotTriggerInfo);
    Integer deleteData(@Param(value = "iotTriggerInfo") IotTriggerInfoBO iotTriggerInfo);
    IotTriggerInfoBO selectOne(@Param(value = "iotTriggerInfo") IotTriggerInfoBO iotTriggerInfo);
    List<IotTriggerInfoBO> selectPage(@Param(value = "iotTriggerInfo") IotTriggerInfoBO iotTriggerInfo);
    List<IotTriggerInfoBO> select(@Param(value = "iotTriggerInfo") IotTriggerInfoBO iotTriggerInfo);

    Integer selectCount(@Param(value = "iotTriggerInfo")IotTriggerInfoBO triggerInfo);
}
