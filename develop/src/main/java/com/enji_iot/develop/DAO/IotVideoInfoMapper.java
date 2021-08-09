package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotVideoInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotVideoInfoMapper {
    Integer selectPageCount(@Param(value = "iotVideoInfo")IotVideoInfoBO iotVideoInfo);
    Integer insert(@Param(value = "iotVideoInfo")IotVideoInfoBO iotVideoInfo);
    Integer update(@Param(value = "iotVideoInfo")IotVideoInfoBO iotVideoInfo);
    IotVideoInfoBO selectOne(@Param(value = "iotVideoInfo")IotVideoInfoBO iotVideoInfo);
    List<IotVideoInfoBO> selectPage(@Param(value = "iotVideoInfo")IotVideoInfoBO iotVideoInfo);
}
