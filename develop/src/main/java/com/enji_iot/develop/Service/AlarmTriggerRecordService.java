package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.AlarmTriggerRecordBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface AlarmTriggerRecordService {
    Map<String,Object> selectPageList(AlarmTriggerRecordBO alarmTriggerRecord, PageBean pageBean);
    Map<String,Object> selectStatisticPage(AlarmTriggerRecordBO alarmTriggerRecord, PageBean pageBean);
    Map<String,Object> insert(AlarmTriggerRecordBO alarmTriggerRecord);
    Map<String,Object> update(AlarmTriggerRecordBO alarmTriggerRecord);
    Map<String,Object> selectOne(AlarmTriggerRecordBO alarmTriggerRecord);
    Map<String,Object> updateSmsVocieNum(AlarmTriggerRecordBO alarmTriggerRecord);
}
 
 
