package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.IotVideoInfoMapper;
import com.enji_iot.develop.Service.IotVideoInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotVideoInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotVideoInfoService")
public class IotVideoInfoServiceImpl implements IotVideoInfoService {
    @Resource
    private IotVideoInfoMapper iotVideoInfoMapper;
    @Override
    public Map<String, Object> selectPageList(IotVideoInfoBO iotVideoInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotVideoInfoMapper.selectPageCount(iotVideoInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotVideoInfo);
                List<?> list = iotVideoInfoMapper.selectPage(iotVideoInfo);
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
    public Map<String, Object> insert(IotVideoInfoBO iotVideoInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotVideoInfoMapper.insert(iotVideoInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotVideoInfoBO iotVideoInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            Object data = iotVideoInfoMapper.selectOne(iotVideoInfo);
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
    public Map<String, Object> update(IotVideoInfoBO iotVideoInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotVideoInfoMapper.update(iotVideoInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
 
 
