package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.IotVideoRecordBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotVideoRecordService {
    Map<String,Object> insert(IotVideoRecordBO iotVideoRecord);
    Map<String,Object> update(IotVideoRecordBO iotVideoRecord);
    Map<String,Object> selectOne(IotVideoRecordBO iotVideoRecord);
    Map<String,Object> selectPageList(IotVideoRecordBO iotVideoRecord, PageBean pageBean);
}
 
 
