package com.enji_iot.data.DAO;

import com.enji_iot.util.Entity.bo.CommonInfoBO;
import com.enji_iot.util.Entity.bo.IotAlarmInfoBO;
import com.enji_iot.util.Entity.bo.IotStatisticBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotScreenAlarmInfoMapper {
    List<IotStatisticBO> selectStatisticInfo(@Param(value = "iotAlarmInfo") IotAlarmInfoBO iotAlarmInfo);
    Integer selectPageCount(@Param(value = "iotAlarmInfo") IotAlarmInfoBO iotAlarmInfo);
    Integer insert(@Param(value = "iotAlarmInfo") IotAlarmInfoBO iotAlarmInfo);
    Integer update(@Param(value = "iotAlarmInfo") IotAlarmInfoBO iotAlarmInfo);
    IotAlarmInfoBO selectOne(@Param(value = "iotAlarmInfo") IotAlarmInfoBO iotAlarmInfo);
    List<IotAlarmInfoBO> selectPage(@Param(value = "iotAlarmInfo") IotAlarmInfoBO iotAlarmInfo);
    List<IotAlarmInfoBO> selectAll(@Param(value = "iotAlarmInfo") IotAlarmInfoBO iotAlarmInfo);
}
