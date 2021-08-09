package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.AlarmTriggerRecordBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmTriggerRecordMapper {
    List<AlarmTriggerRecordBO> selectPage(@Param(value = "alarmTriggerRecord")AlarmTriggerRecordBO alarmTriggerRecord);
    List<AlarmTriggerRecordBO> selectStatisticPage(@Param(value = "alarmTriggerRecord")AlarmTriggerRecordBO alarmTriggerRecord);
    AlarmTriggerRecordBO selectOne(@Param(value = "alarmTriggerRecord")AlarmTriggerRecordBO alarmTriggerRecord);
    Integer selectPageCount(@Param(value = "alarmTriggerRecord")AlarmTriggerRecordBO alarmTriggerRecord);
    Integer insert(@Param(value = "alarmTriggerRecord")AlarmTriggerRecordBO alarmTriggerRecord);
    Integer update(@Param(value = "alarmTriggerRecord")AlarmTriggerRecordBO alarmTriggerRecord);
    Integer updateSmsVocieNum(@Param(value = "alarmTriggerRecord")AlarmTriggerRecordBO alarmTriggerRecord);
}
