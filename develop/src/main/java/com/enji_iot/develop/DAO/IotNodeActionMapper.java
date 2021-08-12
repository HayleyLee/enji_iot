package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotNodeActionBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IotNodeActionMapper {
    ArrayList<IotNodeActionBO> select(@Param(value = "iotNodeAction")IotNodeActionBO iotNodeAction);
    Integer selectCount(@Param(value = "iotNodeAction")IotNodeActionBO iotNodeAction);
}
