package com.enji_iot.develop.Service;


import com.enji_iot.util.Entity.bo.AlarmTriggerStatisticBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface AlarmTriggerStatisticService {
    Map<String,Object> selectPageList(AlarmTriggerStatisticBO alarmTriggerStatistic, PageBean pageBean);
    Map<String,Object> insert(AlarmTriggerStatisticBO alarmTriggerStatistic);
    Map<String,Object> update(AlarmTriggerStatisticBO alarmTriggerStatistic);
    Map<String,Object> selectOne(AlarmTriggerStatisticBO alarmTriggerStatistic);
}
 
 
