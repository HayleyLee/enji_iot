package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.DeviceTemplateSensorInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface DeviceTemplateSensorInfoService {
    Map<String,Object> selectPageList(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo, PageBean pageBean);
    Map<String,Object> insert(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
    Map<String,Object> update(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
    Map<String,Object> selectOne(DeviceTemplateSensorInfoBO deviceTemplateSensorInfo);
}
 
 
