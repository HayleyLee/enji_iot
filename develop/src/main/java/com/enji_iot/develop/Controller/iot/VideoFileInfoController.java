package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.VideoFileInfoService;
import com.enji_iot.util.Entity.bo.VideoFileInfoBO;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller

public class VideoFileInfoController {
	@Autowired
	@Qualifier(value = "VideoFileInfoService")
	private VideoFileInfoService videoFileInfoService;
	/**
	 * 检索
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.VideoFileInfo.VIDEO_FILE_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                   @RequestBody VideoFileInfoBO obj, @RequestParam(required = false) Integer pageSize,
                                   @RequestParam Integer paged) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = videoFileInfoService.selectPage(obj,new ResultMapUtils().getPageBean(paged, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 插入
	 */
	// @RequestMapping(method = RequestMethod.POST, value =
	// RequestURLIOT.VideoFileInfo.VIDEO_FILE_INFO )
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                             @RequestBody VideoFileInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = videoFileInfoService.insert(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查询单个
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.VideoFileInfo.VIDEO_FILE_INFO)
	public ModelAndView selectOne(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = videoFileInfoService.selectOne(new VideoFileInfoBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 更新
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.VideoFileInfo.VIDEO_FILE_INFO)
	public ModelAndView update(HttpServletResponse response, @RequestBody VideoFileInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = videoFileInfoService.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 删除
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.VideoFileInfo.VIDEO_FILE_INFO)
	public ModelAndView delete(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			VideoFileInfoBO obj = new VideoFileInfoBO();
			if (ObjectUtil.isEmpty(id)) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			} else {
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = videoFileInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
