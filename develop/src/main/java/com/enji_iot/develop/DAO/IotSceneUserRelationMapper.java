package com.enji_iot.develop.DAO;

import com.enji_iot.util.Entity.bo.IotSceneUserRelationBO;
import com.enji_iot.util.Util.PageBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IotSceneUserRelationMapper {
    Integer insert(@Param(value = "iotSceneUserRelation") IotSceneUserRelationBO iotSceneUserRelation);
    Integer update(@Param(value = "iotSceneUserRelation") IotSceneUserRelationBO iotSceneUserRelation);
    Integer deleteUserSceneRelation(@Param(value = "iotSceneUserRelation") IotSceneUserRelationBO iotSceneUserRelation);
    Integer deleteUserRelation(@Param(value = "iotSceneUserRelation") IotSceneUserRelationBO iotSceneUserRelation);
    Integer selectPageCount(@Param(value = "iotSceneUserRelation") IotSceneUserRelationBO iotSceneUserRelation);
    IotSceneUserRelationBO selectOne(@Param(value = "iotSceneUserRelation") IotSceneUserRelationBO iotSceneUserRelation);
    List<IotSceneUserRelationBO> selectPage(@Param(value = "iotSceneUserRelation") IotSceneUserRelationBO iotSceneUserRelation);
}
