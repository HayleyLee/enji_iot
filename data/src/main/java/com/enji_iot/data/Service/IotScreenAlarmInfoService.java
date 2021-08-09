package com.enji_iot.data.Service;

import com.enji_iot.util.Entity.bo.IotAlarmInfoBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotScreenAlarmInfoService {
    Map<String,Object> selectPageList(IotAlarmInfoBO iotAlarmInfo, PageBean pageBean);
    Map<String,Object> selectStatisticInfo(IotAlarmInfoBO iotAlarmInfo);
    Map<String,Object> insert(IotAlarmInfoBO iotAlarmInfo);
    Map<String,Object> selectOne(IotAlarmInfoBO iotAlarmInfo);
    Map<String,Object> update(IotAlarmInfoBO iotAlarmInfo);
    Map<String,Object> selectAll(IotAlarmInfoBO iotAlarmInfo);
}
