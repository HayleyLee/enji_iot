package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.IotSceneUserRelationBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotSceneUserRelationService {
    Map<String,Object> insert(IotSceneUserRelationBO iotSceneUserRelation);
    Map<String,Object> update(IotSceneUserRelationBO iotSceneUserRelation);
    Map<String,Object> deleteUserSceneRelation(IotSceneUserRelationBO iotSceneUserRelation);
    Map<String,Object> deleteUserRelation(IotSceneUserRelationBO iotSceneUserRelation);
    Map<String,Object> selectOne(IotSceneUserRelationBO iotSceneUserRelation);
    Map<String,Object> selectPageList(IotSceneUserRelationBO iotSceneUserRelation, PageBean pageBean);
}
