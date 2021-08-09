package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotHistoryTriggerInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.IotHistoryTriggerInfoBO;
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
import java.util.List;
import java.util.Map;


@Controller
public class IotHistoryTriggerInfoController {
	@Autowired
	@Qualifier(value = "IotHistoryTriggerInfoService")
	private IotHistoryTriggerInfoService iotHistoryTriggerInfoService;
	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.TriggerHistoryInfo.TRIGGER_HISTORY_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotHistoryTriggerInfoBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			System.err.println("node_id："+obj.getNode_id());
			System.err.println("scene_id："+obj.getScene_id());
			// 这边查询，需要有场景id
			if( ObjectUtil.isNotEmpty(obj.getScene_id()) ){
				resultMap = iotHistoryTriggerInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
				System.err.println(resultMap.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * Excel 下载
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.TriggerHistoryInfo.TRIGGER_HISTORY_INFO_EXCEL )
	public void excel(HttpServletResponse response, 
			IotHistoryTriggerInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			List<?> data = ResultMapUtils.getData(iotHistoryTriggerInfoService.select(obj));
			UserUtil.downExcel("触发历史下载", "tpl/xls/history_action_template", data, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.TriggerHistoryInfo.TRIGGER_HISTORY_INFO)
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody IotHistoryTriggerInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			
			resultMap = iotHistoryTriggerInfoService.insert(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 查询单个
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.TriggerHistoryInfo.TRIGGER_HISTORY_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotHistoryTriggerInfoService.selectOne(new IotHistoryTriggerInfoBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 更新
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.TriggerHistoryInfo.TRIGGER_HISTORY_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody IotHistoryTriggerInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotHistoryTriggerInfoService.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 删除
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.TriggerHistoryInfo.TRIGGER_HISTORY_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotHistoryTriggerInfoBO obj = new IotHistoryTriggerInfoBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = iotHistoryTriggerInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
