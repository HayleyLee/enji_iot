package com.enji_iot.data.Service.Impl;

import com.enji_iot.data.Service.IotScreenHistoryNodeInfoService;
import com.enji_iot.data.DAO.IotScreenHistoryNodeInfoMapper;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotHistorySensorDataBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotScreenHistoryNodeInfoService")
public class IotScreenHistoryNodeInfoServiceImpl implements IotScreenHistoryNodeInfoService {
    @Resource
    private IotScreenHistoryNodeInfoMapper iotScreenHistoryNodeInfoMapper;

    @Override
    public Map<String, Object> selectHistoryNodeData(IotHistoryNodeDataBO iotHistoryNodeData) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            List<IotHistoryNodeDataBO> data = iotScreenHistoryNodeInfoMapper.selectHistoryNodeData(iotHistoryNodeData);
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
    public Map<String,Object> insert(IotHistoryNodeDataBO iotHistoryNodeData) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotScreenHistoryNodeInfoMapper.insert(iotHistoryNodeData);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(IotHistorySensorDataBO iotHistorySensorData) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotScreenHistoryNodeInfoMapper.update(iotHistorySensorData);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotHistorySensorDataBO iotHistorySensorData) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            IotHistorySensorDataBO data = iotScreenHistoryNodeInfoMapper.selectOne(iotHistorySensorData);
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
    public Map<String, Object> selectList(IotHistoryNodeDataBO iotHistoryNodeData) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotScreenHistoryNodeInfoMapper.selectHistoryNodeCount(iotHistoryNodeData);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
