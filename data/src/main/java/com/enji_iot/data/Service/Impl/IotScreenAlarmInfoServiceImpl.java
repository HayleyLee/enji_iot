package com.enji_iot.data.Service.Impl;

import com.enji_iot.data.DAO.IotScreenAlarmInfoMapper;
import com.enji_iot.data.Service.IotScreenAlarmInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.IotAlarmInfoBO;
import com.enji_iot.util.Entity.bo.IotStatisticBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "IotScreenAlarmInfoService")
public class IotScreenAlarmInfoServiceImpl implements IotScreenAlarmInfoService {
    @Resource
    private IotScreenAlarmInfoMapper iotScreenAlarmInfoMapper;
    @Override
    public Map<String, Object> selectPageList(IotAlarmInfoBO iotAlarmInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = iotScreenAlarmInfoMapper.selectPageCount(iotAlarmInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(iotAlarmInfo);
                List<?> list = iotScreenAlarmInfoMapper.selectPage(iotAlarmInfo);
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
    public Map<String, Object> selectStatisticInfo(IotAlarmInfoBO iotAlarmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();

        try {
            List<IotStatisticBO> data = iotScreenAlarmInfoMapper.selectStatisticInfo(iotAlarmInfo);
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
    public Map<String, Object> insert(IotAlarmInfoBO iotAlarmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotScreenAlarmInfoMapper.insert(iotAlarmInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(IotAlarmInfoBO iotAlarmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            IotAlarmInfoBO data = iotScreenAlarmInfoMapper.selectOne(iotAlarmInfo);
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
    public Map<String, Object> update(IotAlarmInfoBO iotAlarmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = iotScreenAlarmInfoMapper.update(iotAlarmInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectAll(IotAlarmInfoBO iotAlarmInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            List<IotAlarmInfoBO> data = iotScreenAlarmInfoMapper.selectAll(iotAlarmInfo);
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
