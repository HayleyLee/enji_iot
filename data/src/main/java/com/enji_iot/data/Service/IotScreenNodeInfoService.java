package com.enji_iot.data.Service;

import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeUnitDataBO;
import com.enji_iot.util.Util.PageBean;

import java.util.Map;

public interface IotScreenNodeInfoService {


	Map<String, Object> selectPageList(IotNodeInfoBO iotNodeInfo, PageBean pageBean);


	Map<String, Object> selectStatisticNodeInfo(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> update(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> selectOne(IotNodeInfoBO iotNodeInfo);

	Map<String, Object> selectNodeUnitList(IotNodeUnitDataBO iotNodeUnitData, PageBean... pageBean);
}
