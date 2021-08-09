package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.DeviceTemplateInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceTemplateInfoMapper {
    List<DeviceTemplateInfoBO> selectPage(@Param(value = "deviceTemplateInfo")DeviceTemplateInfoBO deviceTemplateInfo);
    Integer selectPageCount(@Param(value = "deviceTemplateInfo")DeviceTemplateInfoBO deviceTemplateInfo);
    Integer insert(@Param(value = "deviceTemplateInfo")DeviceTemplateInfoBO deviceTemplateInfo);
    Integer update(@Param(value = "deviceTemplateInfo")DeviceTemplateInfoBO deviceTemplateInfo);
    DeviceTemplateInfoBO selectOne(@Param(value = "deviceTemplateInfo")DeviceTemplateInfoBO deviceTemplateInfo);
}
