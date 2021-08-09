package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.DeviceTemplateTiggerInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTemplateTriggerInfoMapper {
    List<DeviceTemplateTiggerInfoBO> selectPage(@Param(value = "deviceTemplateTriggerInfo")DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
    Integer selectPageCount(@Param(value = "deviceTemplateTriggerInfo")DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
    Integer insert(@Param(value = "deviceTemplateTriggerInfo")DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
    Integer update(@Param(value = "deviceTemplateTriggerInfo")DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
    DeviceTemplateTiggerInfoBO selectOne(@Param(value = "deviceTemplateTriggerInfo")DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
}
