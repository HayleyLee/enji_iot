package com.enji_iot.data.DAO;

import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotScreenSceneInfoMapper {
    Integer selectCount(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);
    Integer insert(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);
    Integer update(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);
    IotSceneInfoBO selectOne(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfoBO);
    List<IotSceneInfoBO> select(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);
}
