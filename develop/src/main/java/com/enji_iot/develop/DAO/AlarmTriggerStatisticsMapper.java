package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.AlarmTriggerStatisticBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmTriggerStatisticsMapper {
    List<AlarmTriggerStatisticBO> selectPage(@Param(value = "alarmTriggerStatistic")AlarmTriggerStatisticBO alarmTriggerStatistic);
    Integer selectPageCount(@Param(value = "alarmTriggerStatistic")AlarmTriggerStatisticBO alarmTriggerStatistic);
    Integer insert(@Param(value = "alarmTriggerStatistic")AlarmTriggerStatisticBO alarmTriggerStatistic);
    Integer update(@Param(value = "alarmTriggerStatistic")AlarmTriggerStatisticBO alarmTriggerStatistic);
    AlarmTriggerStatisticBO selectOne(@Param(value = "alarmTriggerStatistic")AlarmTriggerStatisticBO alarmTriggerStatistic);
}
