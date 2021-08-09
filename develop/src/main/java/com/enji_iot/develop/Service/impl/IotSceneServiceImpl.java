package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.IotSceneInfoMapper;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.OtherBO;
import com.enji_iot.develop.Service.IotSceneInfoService;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.*;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value = "IotSceneInfoService")
public class IotSceneServiceImpl implements IotSceneInfoService {
	@Resource
	private IotSceneInfoMapper iotSceneInfoMapper;

	@Override
	public Map<String, Object> getSceneDetailInfo(IotSceneInfoBO iotSceneInfo) {
		Map<String,Object> resultMap = ResultMapUtils.getResultMap();
		try{
			OtherBO bo = iotSceneInfoMapper.selectSceneDetail(iotSceneInfo);
			ResultMapUtils.putData(resultMap, bo);
		}catch (Exception e) {
			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR );
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> selectOne(IotSceneInfoBO iotSceneInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotSceneInfoBO data = iotSceneInfoMapper.selectOne(iotSceneInfo);
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
	public Map<String, Object> selects(IotSceneInfoBO iotSceneInfo) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<?> data = iotSceneInfoMapper.selects(iotSceneInfo);
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
	public Map<String, Object> selectPageList(IotSceneInfoBO iotSceneInfo, PageBean pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int totalCount = iotSceneInfoMapper.selectCount(iotSceneInfo);
			if (totalCount > 0) {
				pageBean.setPageParam4Mysql(iotSceneInfo);
				List<IotSceneInfoBO> list = iotSceneInfoMapper.select(iotSceneInfo);
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
	public Map<String, Object> selectSceneInfo(IotSceneInfoBO iotSceneInfo, PageBean pageBean) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			int totalCount = iotSceneInfoMapper.selectCount(iotSceneInfo);
			if (totalCount > 0) {
				pageBean.setPageParam4Mysql(iotSceneInfo);
				List<IotSceneInfoBO> list = iotSceneInfoMapper.selectSceneInfo(iotSceneInfo);
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
			int rows = iotSceneInfoMapper.insert(iotSceneInfo);
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
			int rows = iotSceneInfoMapper.update(iotSceneInfo);
			if (rows <= 0)  {
				return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	@Override
	public JSONArray sceneCount(IotSceneInfoBO iotSceneInfo) {
		if(ObjectUtil.isNotEmpty(iotSceneInfo)){
			Integer count = iotSceneInfoMapper.sceneCount(iotSceneInfo);
			return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,count);
		}
		else return ResponseUtil.getResponse(ResponseType.NULL_POINT_ERROR_ID,ResponseType.NULL_POINT_ERROR_MESSAGES);
	}

}
