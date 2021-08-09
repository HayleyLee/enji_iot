package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.DeviceTemplateSensorInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.DeviceTemplateSensorInfoBO;
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

public class DeviceTemplateSensorInfoController {
	@Autowired
	@Qualifier(value = "DeviceTemplateSensorInfoService")
	private DeviceTemplateSensorInfoService deviceTemplateSensorInfoService;
	/**
	 * 检索
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.DeviceTemplateSensorInfo.DEVICE_TEMPLATE_SENSOR_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                   @RequestBody DeviceTemplateSensorInfoBO obj, @RequestParam(required = false) Integer pageSize,
                                   @RequestParam Integer paged) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateSensorInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 插入
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.DeviceTemplateSensorInfo.DEVICE_TEMPLATE_SENSOR_INFO)
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                             @RequestBody DeviceTemplateSensorInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateSensorInfoService.insert(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查询单个
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.DeviceTemplateSensorInfo.DEVICE_TEMPLATE_SENSOR_INFO)
	public ModelAndView selectOne(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateSensorInfoService.selectOne(new DeviceTemplateSensorInfoBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 更新
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.DeviceTemplateSensorInfo.DEVICE_TEMPLATE_SENSOR_INFO)
	public ModelAndView update(HttpServletResponse response, @RequestBody DeviceTemplateSensorInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateSensorInfoService.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 删除
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.DeviceTemplateSensorInfo.DEVICE_TEMPLATE_SENSOR_INFO)
	public ModelAndView delete(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			DeviceTemplateSensorInfoBO obj = new DeviceTemplateSensorInfoBO();
			if (ObjectUtil.isEmpty(id)) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			} else {
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = deviceTemplateSensorInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
