package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.DeviceTemplateTiggerInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface DeviceTemplateTiggerInfoService {
    Map<String,Object> selectPageList(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo, PageBean pageBean);
    Map<String,Object> insert(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
    Map<String,Object> update(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
    Map<String,Object> selectOne(DeviceTemplateTiggerInfoBO deviceTemplateTiggerInfo);
}
 
 
