package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.IotVideoInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotVideoInfoService {
    Map<String,Object> selectPageList(IotVideoInfoBO iotVideoInfo, PageBean pageBean);
    Map<String,Object> insert(IotVideoInfoBO iotVideoInfo);
    Map<String,Object> selectOne(IotVideoInfoBO iotVideoInfo);
    Map<String,Object> update(IotVideoInfoBO iotVideoInfo);
}
 
 
