package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.ProDictionaryInfoMapper;
import com.enji_iot.develop.Service.ProDictionaryInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.ProDictionaryInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "ProDictionaryInfoService")
public class ProDictionaryInfoServiceImpl implements ProDictionaryInfoService {
    @Resource
    private ProDictionaryInfoMapper proDictionaryInfoMapper;
    @Override
    public Map<String, Object> selectPageList(PageBean pageBean, ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = proDictionaryInfoMapper.selectPageListCount(proDictionaryInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(proDictionaryInfo);
                List<ProDictionaryInfoBO> list = proDictionaryInfoMapper.selectPageList(proDictionaryInfo);
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
    public Map<String, Object> selectOne(ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            ProDictionaryInfoBO data = proDictionaryInfoMapper.selectOne(proDictionaryInfo);
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
    public Map<String, Object> generateCode(ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            Integer data = proDictionaryInfoMapper.generateCode();
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
    public Map<String, Object> insert(ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = proDictionaryInfoMapper.insert(proDictionaryInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = proDictionaryInfoMapper.update(proDictionaryInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> delete(ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = proDictionaryInfoMapper.delete(proDictionaryInfo);
            if (rows == 0) {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> deleteByPcode(ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = proDictionaryInfoMapper.deleteByPcode(proDictionaryInfo);
            if (rows == 0) {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateByCondition(ProDictionaryInfoBO proDictionaryInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = proDictionaryInfoMapper.updateByCondition(proDictionaryInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
