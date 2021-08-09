package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.IotHistoryTriggerInfoMapper;
import com.enji_iot.develop.Service.IotHistoryTriggerInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotHistoryTriggerInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotHistoryTriggerInfoService")
public class IotHistoryTriggerInfoServiceImpl implements IotHistoryTriggerInfoService {
    @Resource
    private IotHistoryTriggerInfoMapper iotHistoryTriggerInfoMapper;
    @Override
    public Map<String, Object> selectPageList(IotHistoryTriggerInfoBO iotHistoryTriggerInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotHistoryTriggerInfoMapper.selectPageCount(iotHistoryTriggerInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotHistoryTriggerInfo);
                List<?> list = iotHistoryTriggerInfoMapper.selectPage(iotHistoryTriggerInfo);
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
    public Map<String, Object> select(IotHistoryTriggerInfoBO iotHistoryTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();

        try {
            List<IotHistoryTriggerInfoBO> data = iotHistoryTriggerInfoMapper.select(iotHistoryTriggerInfo);
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
    public Map<String, Object> insert(IotHistoryTriggerInfoBO iotHistoryTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotHistoryTriggerInfoMapper.insert(iotHistoryTriggerInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(IotHistoryTriggerInfoBO iotHistoryTriggerInfo) {

        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotHistoryTriggerInfoMapper.update(iotHistoryTriggerInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotHistoryTriggerInfoBO iotHistoryTriggerInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            Object data = iotHistoryTriggerInfoMapper.selectOne(iotHistoryTriggerInfo);
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
}
