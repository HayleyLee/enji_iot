package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.IotHistoryNodeInfoMapper;
import com.enji_iot.develop.DAO.IotSceneInfoMapper;
import com.enji_iot.develop.Service.IotHistoryNodeInfoService;
import com.enji_iot.develop.Service.IotSceneInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotHistoryNodeDataBO;
import com.enji_iot.util.Entity.bo.IotHistorySensorDataBO;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Util.*;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotHistoryNodeInfoService")
public class IotHistoryNodeInfoServiceImpl implements IotHistoryNodeInfoService {
    @Resource
    private IotHistoryNodeInfoMapper iotHistoryNodeInfoMapper;
    @Resource
    private IotSceneInfoMapper iotSceneInfoMapper;
    @Override
    public Map<String, Object> selectGroupByPage(IotHistorySensorDataBO iotHistorySensorData, PageBean pageBean) {

        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotHistoryNodeInfoMapper.selectPageCount(iotHistorySensorData);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotHistorySensorData);
                List<IotHistorySensorDataBO> list = iotHistoryNodeInfoMapper.selectGroupByPage(iotHistorySensorData);
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
    public Map<String, Object> selectGroupByPage(IotHistorySensorDataBO iotHistorySensorData, PageBean... pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            List<IotHistorySensorDataBO> data = iotHistoryNodeInfoMapper.selectGroupByPage(iotHistorySensorData);
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
    public Map<String, Object> selectPageList(IotHistorySensorDataBO iotHistorySensorData, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotHistoryNodeInfoMapper.selectPageCount(iotHistorySensorData);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotHistorySensorData);
                List<?> list = iotHistoryNodeInfoMapper.selectPage(iotHistorySensorData);
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
    public Map<String, Object> selectPageList(IotHistorySensorDataBO iotHistorySensorData, PageBean... pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            List<IotHistorySensorDataBO> data = iotHistoryNodeInfoMapper.selectGroupByPage(iotHistorySensorData);
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
    public Map<String, Object> selectHistoryNodeData(IotHistoryNodeDataBO iotHistoryNodeData) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            List<IotHistoryNodeDataBO> data = iotHistoryNodeInfoMapper.selectHistoryNodeData(iotHistoryNodeData);
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
    public List<IotHistoryNodeDataBO> singleSelectHistoryNodeData(IotHistoryNodeDataBO iotHistoryNodeData) {
        return iotHistoryNodeInfoMapper.selectHistoryNodeData(iotHistoryNodeData);
    }

    @Override
    public Integer selectHistoryNodeCount(IotHistoryNodeDataBO iotHistoryNodeData) {
        int result = -1;
        try {
            result = iotHistoryNodeInfoMapper.selectHistoryNodeCount(iotHistoryNodeData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String,Object> insert(IotHistoryNodeDataBO iotHistoryNodeData) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotHistoryNodeInfoMapper.insert(iotHistoryNodeData);
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
            int rows = iotHistoryNodeInfoMapper.update(iotHistorySensorData);
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
            IotHistorySensorDataBO data = iotHistoryNodeInfoMapper.selectOne(iotHistorySensorData);
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
            int rows = iotHistoryNodeInfoMapper.selectHistoryNodeCount(iotHistoryNodeData);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public JSONArray selectCount(IotSceneInfoBO scene) {
        List<IotSceneInfoBO> sceneList = iotSceneInfoMapper.selects(scene);
        if(sceneList.size()>0){
            Integer r = 0;
            for(IotSceneInfoBO sceneInfo : sceneList){
                IotHistoryNodeDataBO history = new IotHistoryNodeDataBO();
                history.setScene_id(sceneInfo.getId());
                r += iotHistoryNodeInfoMapper.selectHistoryNodeCount(history);
            }
            return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,r);
        }
        else return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES);
    }
}
