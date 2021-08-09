package com.enji_iot.data.Service.Impl;

import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.data.Service.IotScreenNodeInfoService;
import com.enji_iot.data.DAO.IotScreenNodeInfoMapper;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeUnitDataBO;
import com.enji_iot.util.Entity.bo.IotStatisticBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.QRCodeUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service(value = "IotScreenNodeInfoService")
public class IotScreenNodeInfoServerImpl implements IotScreenNodeInfoService {
	@Resource
	private IotScreenNodeInfoMapper iotScreenNodeInfoMapper;


	@Override
	public Map<String, Object> selectPageList(IotNodeInfoBO iotNodeInfo, PageBean pageBean) {

		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			pageBean.setPageParam4Mysql(iotNodeInfo);
			List<IotNodeInfoBO> data = iotScreenNodeInfoMapper.selectPage(iotNodeInfo);
			if (data==null || data.size() ==0) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			} else {
				ResultMapUtils.putData(resultMap, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}


	@Override
	public Map<String, Object> selectStatisticNodeInfo(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<IotStatisticBO> data = iotScreenNodeInfoMapper.selectStatisticNodeInfo(iotNodeInfo);
			if (data==null || data.size() ==0) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			} else {
				ResultMapUtils.putData(resultMap, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> update(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = iotScreenNodeInfoMapper.update(iotNodeInfo);
			if (rows <= 0)  {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectOne(IotNodeInfoBO iotNodeInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotNodeInfoBO data = iotScreenNodeInfoMapper.selectOne(iotNodeInfo);
			if (data == null) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			} else {
				ResultMapUtils.putData(resultMap, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectNodeUnitList(IotNodeUnitDataBO iotNodeUnitData, PageBean... pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<IotNodeUnitDataBO> data = iotScreenNodeInfoMapper.selectNodeUnitList(iotNodeUnitData);
			if (data==null || data.size() ==0) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			} else {
				ResultMapUtils.putData(resultMap, data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}
