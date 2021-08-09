package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.IotNodeInfoMapper;
import com.enji_iot.develop.DAO.IotSceneInfoMapper;
import com.enji_iot.develop.DAO.IotTriggerInfoMapper;
import com.enji_iot.develop.Service.IotTriggerInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.IotTriggerInfoBO;
import com.enji_iot.util.Util.*;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotTriggerInfoService")
public class IotTriggerInfoServiceImpl implements IotTriggerInfoService {
    @Resource
    private IotTriggerInfoMapper iotTriggerInfoMapper;
    @Resource
    private IotNodeInfoMapper iotNodeInfoMapper;
    @Resource
    private IotSceneInfoMapper iotSceneInfoMapper;
    @Override
    public Map<String, Object> selectPageList(IotTriggerInfoBO iotTriggerInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotTriggerInfoMapper.selectPageCount(iotTriggerInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotTriggerInfo);
                List<IotTriggerInfoBO> list = iotTriggerInfoMapper.selectPage(iotTriggerInfo);
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
    public Map<String, Object> insert(IotTriggerInfoBO iotTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotTriggerInfoMapper.insert(iotTriggerInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(IotTriggerInfoBO iotTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotTriggerInfoMapper.update(iotTriggerInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> delete(IotTriggerInfoBO iotTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotTriggerInfoMapper.deleteData(iotTriggerInfo);
            if (rows == 0) {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotTriggerInfoBO iotTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            IotTriggerInfoBO data = iotTriggerInfoMapper.selectOne(iotTriggerInfo);
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
    public Map<String, Object> selectList(IotTriggerInfoBO iotTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            List<IotTriggerInfoBO> data = iotTriggerInfoMapper.select(iotTriggerInfo);
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
    public JSONArray selectCount(IotSceneInfoBO sceneInfo) {
        List<IotSceneInfoBO> sceneList = iotSceneInfoMapper.selects(sceneInfo);
        if(sceneList.size()>0){
            Integer r = 0;
            for(IotSceneInfoBO scene : sceneList){
                IotNodeInfoBO nodeInfo = new IotNodeInfoBO();
                nodeInfo.setScene_id(scene.getId());
                List<IotNodeInfoBO> nodeList = iotNodeInfoMapper.select(nodeInfo);
                for(IotNodeInfoBO node : nodeList){
                    IotTriggerInfoBO triggerInfo = new IotTriggerInfoBO();
                    triggerInfo.setNode_id(node.getId());
                    r += iotTriggerInfoMapper.selectCount(triggerInfo);
                }
            }
            return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,r);
        }
        else return ResponseUtil.getResponse(ResponseType.SUCCESS_ID,ResponseType.SUCCESS_MESSAGES,0);
    }
}
