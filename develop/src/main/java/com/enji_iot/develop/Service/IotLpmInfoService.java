package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.IotLpmInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotLpmInfoService {
    Map<String,Object> selectPageList(IotLpmInfoBO iotLpmInfo, PageBean pageBean);
    Map<String,Object> insert(IotLpmInfoBO iotLpmInfo);
    Map<String,Object> update(IotLpmInfoBO iotLpmInfo);
    Map<String,Object> selectOne(IotLpmInfoBO iotLpmInfo);
}
 
 
