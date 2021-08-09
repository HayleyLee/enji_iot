package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotVideoRecordBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotVideoRecordMapper {
    Integer insert(@Param(value = "iotVideoRecord")IotVideoRecordBO iotVideoRecord);
    Integer update(@Param(value = "iotVideoRecord")IotVideoRecordBO iotVideoRecord);
    Integer selectPageCount(@Param(value = "iotVideoRecord")IotVideoRecordBO iotVideoRecord);
    IotVideoRecordBO selectOne(@Param(value = "iotVideoRecord")IotVideoRecordBO iotVideoRecord);
    List<IotVideoRecordBO> selectPage(@Param(value = "iotVideoRecord")IotVideoRecordBO iotVideoRecord);
}
