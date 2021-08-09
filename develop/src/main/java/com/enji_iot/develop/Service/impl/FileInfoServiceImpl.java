package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.FileInfoMapper;
import com.enji_iot.develop.Service.FileInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.FileInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "FileInfoService")
public class FileInfoServiceImpl implements FileInfoService {
    @Resource
    private FileInfoMapper fileInfoMapper;
    @Override
    public Map<String, Object> selectPageList(PageBean pageBean, FileInfoBO fileInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = fileInfoMapper.selectPageCount(fileInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(fileInfo);
                List<FileInfoBO> list = fileInfoMapper.selectPage(fileInfo);
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
    public Map<String, Object> selectOne(FileInfoBO fileInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            FileInfoBO data = fileInfoMapper.selectOne(fileInfo);
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
    public Map<String, Object> update(FileInfoBO fileInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = fileInfoMapper.update(fileInfo);
            if (rows <= 0)  {
                return ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> insert(FileInfoBO fileInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = fileInfoMapper.insert(fileInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
