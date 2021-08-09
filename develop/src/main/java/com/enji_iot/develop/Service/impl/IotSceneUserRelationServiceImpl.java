package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.IotSceneUserRelationMapper;
import com.enji_iot.develop.Service.IotSceneUserRelationService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotSceneUserRelationBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotSceneUserRelationService")
public class IotSceneUserRelationServiceImpl implements IotSceneUserRelationService {
    @Resource
    private IotSceneUserRelationMapper iotSceneUserRelationMapper;
    @Override
    public Map<String, Object> insert(IotSceneUserRelationBO iotSceneUserRelation) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotSceneUserRelationMapper.insert(iotSceneUserRelation);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(IotSceneUserRelationBO iotSceneUserRelation) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotSceneUserRelationMapper.update(iotSceneUserRelation);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteUserSceneRelation(IotSceneUserRelationBO iotSceneUserRelation) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotSceneUserRelationMapper.deleteUserSceneRelation(iotSceneUserRelation);
            if (rows == 0) {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteUserRelation(IotSceneUserRelationBO iotSceneUserRelation) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotSceneUserRelationMapper.deleteUserRelation(iotSceneUserRelation);
            if (rows == 0) {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotSceneUserRelationBO iotSceneUserRelation) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            IotSceneUserRelationBO data = iotSceneUserRelationMapper.selectOne(iotSceneUserRelation);
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
    public Map<String, Object> selectPageList(IotSceneUserRelationBO iotSceneUserRelation, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotSceneUserRelationMapper.selectPageCount(iotSceneUserRelation);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotSceneUserRelation);
                List<?> list = iotSceneUserRelationMapper.selectPage(iotSceneUserRelation);
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
}
