package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.OtherBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotSceneInfoMapper {
    Integer selectCount(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);

    Integer insert(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);

    Integer update(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);

    OtherBO selectSceneDetail(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfoBO);

    IotSceneInfoBO selectOne(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfoBO);

    List<IotSceneInfoBO> select(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);

    List<IotSceneInfoBO> selects(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);

    List<IotSceneInfoBO> selectSceneInfo(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);

    Integer sceneCount(@Param(value = "iotSceneInfo") IotSceneInfoBO iotSceneInfo);
}