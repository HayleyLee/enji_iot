package com.enji_iot.data.DAO;

import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotScreenMapper {
    List<IotNodeInfoBO> selectAll(@Param(value = "iotNodeInfo") IotNodeInfoBO iotNodeInfo);
}
