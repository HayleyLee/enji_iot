package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotLpmInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotLpmInfoMapper {
    List<IotLpmInfoBO> selectPage(@Param(value = "iotLpmInfo")IotLpmInfoBO iotLpmInfo);
    IotLpmInfoBO selectOne(@Param(value = "iotLpmInfo")IotLpmInfoBO iotLpmInfo);
    Integer selectPageCount(@Param(value = "iotLpmInfo")IotLpmInfoBO iotLpmInfo);
    Integer insert(@Param(value = "iotLpmInfo")IotLpmInfoBO iotLpmInfo);
    Integer update(@Param(value = "iotLpmInfo")IotLpmInfoBO iotLpmInfo);
}
