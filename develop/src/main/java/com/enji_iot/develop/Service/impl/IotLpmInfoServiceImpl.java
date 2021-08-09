package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.IotLpmInfoMapper;
import com.enji_iot.develop.Service.IotLpmInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotLpmInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotLpmInfoService")
public class IotLpmInfoServiceImpl implements IotLpmInfoService {
    @Resource
    private IotLpmInfoMapper iotLpmInfoMapper;

    @Override
    public Map<String, Object> selectPageList(IotLpmInfoBO iotLpmInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotLpmInfoMapper.selectPageCount(iotLpmInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotLpmInfo);
                List<?> list = iotLpmInfoMapper.selectPage(iotLpmInfo);
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
    public Map<String, Object> insert(IotLpmInfoBO iotLpmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotLpmInfoMapper.insert(iotLpmInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(IotLpmInfoBO iotLpmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotLpmInfoMapper.update(iotLpmInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotLpmInfoBO iotLpmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            Object data = iotLpmInfoMapper.selectOne(iotLpmInfo);
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
 
 
