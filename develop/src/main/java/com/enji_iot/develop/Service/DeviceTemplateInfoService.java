package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.DeviceTemplateInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface DeviceTemplateInfoService {
    Map<String,Object> selectPageList(DeviceTemplateInfoBO deviceTemplateInfo, PageBean pageBean);
    Map<String,Object> insert(DeviceTemplateInfoBO deviceTemplateInfo);
    Map<String,Object> update(DeviceTemplateInfoBO deviceTemplateInfo);
    Map<String,Object> selectOne(DeviceTemplateInfoBO deviceTemplateInfo);
}
 
 
