package com.enji_iot.develop.Service;

import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeUnitDataBO;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.PageBean;
import net.sf.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IotNodeInfoService{

	Map<String, Object> updateNodeStatus(IotNodeInfoBO obj);

	Map<String, Object> saveNodeInfo(IotNodeInfoBO obj);

	Map<String, Object> importTemplateNodeInfo(MultipartFile file);

	Map<String, Object> saveNodeUnit(IotNodeUnitDataBO obj);

	Map<String, Object> selectPageList(IotNodeInfoBO iotNodeInfo, PageBean pageBean);

	Map<String, Object> selectPage(IotNodeInfoBO iotNodeInfo, PageBean pageBean);

	Map<String, Object> selectStatisticNodeInfo(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> selectNodeDevCode(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> update(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> deleteNode(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> selectOne(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> selectUnUsedNode(IotNodeInfoBO iotNodeInfo,PageBean pageBean);

	List<IotNodeInfoBO> select(IotNodeInfoBO iotNodeInfo);

	List<IotNodeUnitDataBO> selectNodeUnitList(IotNodeUnitDataBO iotNodeUnitData);

	Map<String, Object> selectNodeUnitList(IotNodeUnitDataBO iotNodeUnitData, PageBean... pageBean);

	Map<String, Object> selectNodeSensorList(IotNodeInfoBO iotNodeInfo, PageBean pageBean);

	Map<String, Object> deviceUnitUpdate(IotNodeUnitDataBO iotNodeUnitData);

	Map<String, Object> deviceUnitDelete(IotNodeUnitDataBO iotNodeUnitData);

    JSONArray selectCount(IotSceneInfoBO sceneInfo);
}
