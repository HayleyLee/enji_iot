package com.enji_iot.cache.DAO;

import com.enji_iot.util.Entity.bean.IotLpmInfo;
import com.enji_iot.util.Entity.bo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CacheMapper {
    List<IotNodeInfoBO> iot_node_info_selectList();
    Integer iot_node_info_updateNodeStatus(@Param(value = "nodeInfo")IotNodeInfoBO nodeInfo);
    List<SysConfigInfoBO> sys_config_info_selectList();
    List<ProDictionaryInfoBO> pro_dictionary_info_selectList();
    List<UserAccountInfoBO> user_account_info_selectList();
    List<UserInfoBO> user_info_selectList();
    List<IotLpmInfo> iot_lpm_info_selectList();
    List<IotSceneInfoBO> iot_scene_info_selectList();
    List<IotTriggerInfoBO> iot_trigger_info_selectList();
    List<IotVideoInfoBO> iot_video_info_selectList();
    List<IotNodeUnitDataBO> iot_node_unit_data_selectList();
}
