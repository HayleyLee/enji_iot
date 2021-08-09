package com.enji_iot.data.Service.Impl;

import com.enji_iot.data.Service.IotScreenSceneInfoService;
import com.enji_iot.data.DAO.IotScreenSceneInfoMapper;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotScreenSceneInfoService")
public class IotScreenSceneInfoServiceImpl implements IotScreenSceneInfoService {
	@Resource
	private IotScreenSceneInfoMapper iotScreenSceneInfoMapper;

	@Override
	public Map<String, Object> selectOne(IotSceneInfoBO iotSceneInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotSceneInfoBO data = iotScreenSceneInfoMapper.selectOne(iotSceneInfo);
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
	public Map<String, Object> selectPageList(IotSceneInfoBO iotSceneInfo, PageBean pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int totalCount = iotScreenSceneInfoMapper.selectCount(iotSceneInfo);
			if (totalCount > 0) {
				pageBean.setPageParam4Mysql(iotSceneInfo);
				List<IotSceneInfoBO> list = iotScreenSceneInfoMapper.select(iotSceneInfo);
				if(ObjectUtil.isNotEmpty(list)){
					pageBean.setTotalCount(totalCount);
					pageBean.setData(list);
					ResultMapUtils.putData(resultMap, pageBean);
				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
				}
			} else {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> insert(IotSceneInfoBO iotSceneInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = iotScreenSceneInfoMapper.insert(iotSceneInfo);
			if (rows <= 0)  {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> update(IotSceneInfoBO iotSceneInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int rows = iotScreenSceneInfoMapper.update(iotSceneInfo);
			if (rows <= 0)  {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

}
