package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotVideoInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.IotVideoInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.mqtt.protocol.ProtocalFactory;
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

public class IotVideoInfoController {
	@Autowired
	@Qualifier(value = "IotVideoInfoService")
	private IotVideoInfoService iotVideoInfoService;
	/**
	 * 检索
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotVideoInfo.IOT_VIDEO_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                   @RequestBody IotVideoInfoBO obj, @RequestParam(required = false) Integer pageSize,
                                   @RequestParam Integer paged) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotVideoInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 插入
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotVideoInfo.IOT_VIDEO_INFO)
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                             @RequestBody IotVideoInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			obj.setStatus(CodeIot.DEVICE_STATUS.UNCONTECT);
			if(ObjectUtil.isEmpty(ProCacheUtil.getCache(CacheName.VIDEO_INFO, obj.getApp_name()))){
				resultMap = iotVideoInfoService.insert(obj);
				if(ResultMapUtils.isOk(resultMap)){
					ProCacheUtil.addCache(CacheName.VIDEO_INFO, obj.getApp_name(), obj);
					System.err.println(obj.getId());
					//todo 新增摄像头后发出指令
					ProtocalFactory.getInstance("ProtocalMing").add_Video_Live(obj.getId());
				}
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.IotInfo.VIDEO_INFO_REPEAT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查询单个
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.IotVideoInfo.IOT_VIDEO_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotVideoInfoService.selectOne(new IotVideoInfoBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 更新
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.IotVideoInfo.IOT_VIDEO_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey, @RequestBody IotVideoInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotVideoInfoBO tmp =ProCacheUtil.getCache(CacheName.VIDEO_INFO, obj.getApp_name());
			if(ObjectUtil.isEmpty(tmp) || tmp.getId()== obj.getId()+0 ){
				resultMap = iotVideoInfoService.update(obj);
				if(ResultMapUtils.isOk(resultMap)){
					ProCacheUtil.addCache(CacheName.VIDEO_INFO, obj.getApp_name(), obj);
				}				
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.IotInfo.VIDEO_INFO_REPEAT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 删除
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.IotVideoInfo.IOT_VIDEO_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotVideoInfoBO obj = new IotVideoInfoBO();
			if (ObjectUtil.isEmpty(id)) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			} else {
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = iotVideoInfoService.update(obj);
				if(ResultMapUtils.isOk(resultMap)){
					// 需要先查询，然后删除
					obj = (IotVideoInfoBO) ResultMapUtils.getData(iotVideoInfoService.selectOne(obj));
					ProCacheUtil.removeCache(CacheName.VIDEO_INFO, obj.getApp_name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
