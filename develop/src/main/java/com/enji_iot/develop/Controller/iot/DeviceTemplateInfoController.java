package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.DeviceTemplateInfoService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.DeviceTemplateInfoBO;
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

public class DeviceTemplateInfoController {
	@Autowired
	@Qualifier(value = "DeviceTemplateInfoService")
	private DeviceTemplateInfoService deviceTemplateInfoService;
	/**
	 * 检索
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.DeviceTemplateInfo.DEVICE_TEMPLATE_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                   @RequestBody DeviceTemplateInfoBO obj, @RequestParam(required = false) Integer pageSize,
                                   @RequestParam Integer paged) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 插入
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.DeviceTemplateInfo.DEVICE_TEMPLATE_INFO)
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                             @RequestBody DeviceTemplateInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateInfoService.insert(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查询单个
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.DeviceTemplateInfo.DEVICE_TEMPLATE_INFO)
	public ModelAndView selectOne(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateInfoService.selectOne(new DeviceTemplateInfoBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 更新
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.DeviceTemplateInfo.DEVICE_TEMPLATE_INFO)
	public ModelAndView update(HttpServletResponse response, @RequestBody DeviceTemplateInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = deviceTemplateInfoService.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 删除
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.DeviceTemplateInfo.DEVICE_TEMPLATE_INFO)
	public ModelAndView delete(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			DeviceTemplateInfoBO obj = new DeviceTemplateInfoBO();
			if (ObjectUtil.isEmpty(id)) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			} else {
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = deviceTemplateInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
