package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.DeviceTemplateSensorInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTemplateSensorInfoMapper {
    List<DeviceTemplateSensorInfoBO> selectPage(@Param(value = "deviceTemplateSensorInfo")DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
    Integer selectPageCount(@Param(value = "deviceTemplateSensorInfo")DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
    Integer insert(@Param(value = "deviceTemplateSensorInfo")DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
    Integer update(@Param(value = "deviceTemplateSensorInfo")DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
    DeviceTemplateSensorInfoBO selectOne(@Param(value = "deviceTemplateSensorInfo")DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
}
