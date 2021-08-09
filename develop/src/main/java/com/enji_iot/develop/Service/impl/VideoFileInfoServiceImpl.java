package com.enji_iot.develop.Service.impl;

import com.enji_iot.develop.DAO.VideoFileInfoMapper;
import com.enji_iot.develop.Service.VideoFileInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.VideoFileInfoBO;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "VideoFileInfoService")
public class VideoFileInfoServiceImpl implements VideoFileInfoService {
    @Resource
    private VideoFileInfoMapper videoFileInfoMapper;
    @Override
    public Map<String, Object> selectPage(VideoFileInfoBO videoFileInfo, PageBean pageBean) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int totalCount = videoFileInfoMapper.selectPageCount(videoFileInfo);
            if (totalCount > 0) {
                pageBean.setPageParam4Mysql(videoFileInfo);
                List<VideoFileInfoBO> list = videoFileInfoMapper.selectPage(videoFileInfo);
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
    public Map<String, Object> select(VideoFileInfoBO videoFileInfo) {
        return null;
    }

    @Override
    public Map<String, Object> insert(VideoFileInfoBO videoFileInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = videoFileInfoMapper.insert(videoFileInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> update(VideoFileInfoBO videoFileInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            int rows = videoFileInfoMapper.update(videoFileInfo);
            if (rows <= 0)  {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> selectOne(VideoFileInfoBO videoFileInfo) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            VideoFileInfoBO data = videoFileInfoMapper.selectOne(videoFileInfo);
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
 
 
