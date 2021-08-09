package com.enji_iot.develop.Service.impl;


import com.enji_iot.develop.DAO.AlarmTriggerStatisticsMapper;
import com.enji_iot.develop.Service.AlarmTriggerStatisticService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.AlarmTriggerStatisticBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "AlarmTriggerStatisticService")
public class AlarmTriggerStatisticServiceImpl implements AlarmTriggerStatisticService {

    @Resource
    private AlarmTriggerStatisticsMapper alarmTriggerStatisticsMapper;

    @Override
    public Map<String, Object> selectPageList(AlarmTriggerStatisticBO alarmTriggerStatistic, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = alarmTriggerStatisticsMapper.selectPageCount(alarmTriggerStatistic);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(alarmTriggerStatistic);
                List<AlarmTriggerStatisticBO> list = alarmTriggerStatisticsMapper.selectPage(alarmTriggerStatistic);
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
    public Map<String, Object> insert(AlarmTriggerStatisticBO alarmTriggerStatistic) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = alarmTriggerStatisticsMapper.insert(alarmTriggerStatistic);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(AlarmTriggerStatisticBO alarmTriggerStatistic) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = alarmTriggerStatisticsMapper.update(alarmTriggerStatistic);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(AlarmTriggerStatisticBO alarmTriggerStatistic) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            AlarmTriggerStatisticBO data = alarmTriggerStatisticsMapper.selectOne(alarmTriggerStatistic);
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
 
 
