package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotTriggerInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.IotTriggerInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class IotTriggerInfoController {
	@Autowired
	@Qualifier(value = "IotTriggerInfoService")
	private IotTriggerInfoService iotTriggerInfoService;

	/**
	 * 统计
	 */
	@RequestMapping(method = RequestMethod.POST,value = RequestURLIOT.TriggerInfo.TRIGGER_COUNT)
	public @ResponseBody JSONArray selectCount(String user_id){
		IotSceneInfoBO sceneInfo = new IotSceneInfoBO();
		sceneInfo.setUser_id(Integer.parseInt(user_id));
		return iotTriggerInfoService.selectCount(sceneInfo);
	}

	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.TriggerInfo.TRIGGER_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotTriggerInfoBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			if( ObjectUtil.isNotEmpty(obj.getIot_sensor_name()) || ObjectUtil.isNotEmpty(obj.getScene_id()) || UserUtil.verifyUserRole(userKey,Code.UserType.SUPER) ){
				resultMap = iotTriggerInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 插入
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.TriggerInfo.TRIGGER_INFO )
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody IotTriggerInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 增加触发器状态
//			obj.setIot_trigger_status(CodeIot.IOT_TRIGGER_STATUS.NORMAL);
			// 设置添加用户信息
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setAid(user.getId());

			resultMap = iotTriggerInfoService.insert(obj);
//			if(isOk(resultMap)){
//				// 更新触发器缓存
////				resultMap = service.selectList("IotSensorInfo.selectSensorTriggerList", new IotSensorInfoBO(obj.getFrom_sensor_id()));
//
//			}
			if(ResultMapUtils.isOk(resultMap)){
				List<IotTriggerInfoBO> list = new ArrayList<>();
				list.add(obj);
//				IotSensorInfoBO iotSensorObj = ((List<IotSensorInfoBO>) getData(resultMap)).get(0);
				ProCacheUtil.addCache(CacheName.NODETRIGGERINFO, obj.getNode_id()+"",list);

				// 判断
				if(obj.getIot_trigger_condition_type() == 280){
					List<IotTriggerInfoBO> triggerList = ProCacheUtil.getCache(CacheName.NODETRIGGERINFO, obj.getNode_id()+"");
					if(ObjectUtil.isNotEmpty(triggerList)){
						triggerList.add(obj);
					}else{
						List<IotTriggerInfoBO> tmps = new ArrayList<>();
						tmps.add(obj);
						ProCacheUtil.addCache(CacheName.NODETRIGGERINFO, obj.getNode_id()+"" , tmps );
					}
				}
			}
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
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.TriggerInfo.TRIGGER_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotTriggerInfoService.selectOne(new IotTriggerInfoBO(id));
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
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.TriggerInfo.TRIGGER_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody IotTriggerInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			obj.setMid( user.getId() );
			resultMap = iotTriggerInfoService.update(obj);
			if(ResultMapUtils.isOk(resultMap)){
				
				resultMap = iotTriggerInfoService.selectOne(new IotTriggerInfoBO(obj.getId()));
				obj = (IotTriggerInfoBO) ResultMapUtils.getData(resultMap) ;
				
				// 更新触发器缓存
//				resultMap = service.selectList("IotSensorInfo.selectSensorTriggerList", new IotSensorInfoBO(obj.getFrom_sensor_id()));
//				if(isOk(resultMap)){
//					IotSensorInfoBO iotSensorObj = ((List<IotSensorInfoBO>) getData(resultMap)).get(0);
//					ProCacheUtil.addCache(CacheName.SENSORTRIGGERINFO, iotSensorObj.getNode_id()+"-"+
//							iotSensorObj.getSensor_device_id()+"-"+iotSensorObj.getPort_id(), iotSensorObj);
//				}
				// 单独更新这个触发器
				IotTriggerInfoBO triggerInfo = new IotTriggerInfoBO();
//				triggerInfo.setIot_trigger_condition_type(280);
				triggerInfo.setNode_id(obj.getNode_id());
				resultMap = iotTriggerInfoService.selectList(triggerInfo);
				List<IotTriggerInfoBO> iotTriggerInfoBOList  = ResultMapUtils.getData(resultMap) ;
				
				ProCacheUtil.removeCache(CacheName.NODETRIGGERINFO, obj.getNode_id().toString());
				
				if( ObjectUtil.isNotEmpty(iotTriggerInfoBOList) ){
					for(IotTriggerInfoBO tmp: iotTriggerInfoBOList){
						List<IotTriggerInfoBO> objs = ProCacheUtil.getCache(CacheName.NODETRIGGERINFO, tmp.getNode_id().toString()) ;
						if( ObjectUtil.isNotEmpty( objs ) ){
							objs.add(tmp);
						}else{
							objs = new ArrayList<>();
							objs.add(tmp);
							ProCacheUtil.addCache(CacheName.NODETRIGGERINFO, tmp.getNode_id().toString() , objs);
						}
					}
				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.OK);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 删除（不删除数据）
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.TriggerInfo.TRIGGER_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotTriggerInfoBO obj = new IotTriggerInfoBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
				obj.setUser_id(user.getId());
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				obj.setMid( user.getId() );
				resultMap = iotTriggerInfoService.update(obj);
				// 更新传感器触发器缓存
				if(ResultMapUtils.isOk(resultMap)){
					resultMap = iotTriggerInfoService.selectOne(new IotTriggerInfoBO(id));
					obj = ResultMapUtils.getData(resultMap);
					// 更新这个设备node的触发器
					IotTriggerInfoBO triggerInfo = new IotTriggerInfoBO();
					triggerInfo.setIot_trigger_condition_type(280);
					triggerInfo.setNode_id(obj.getNode_id());
					resultMap = iotTriggerInfoService.selectList(triggerInfo);
					List<IotTriggerInfoBO> iotTriggerInfoBOList  = ResultMapUtils.getData(resultMap) ;
					ProCacheUtil.removeCache(CacheName.NODETRIGGERINFO, obj.getNode_id().toString());
					if( ObjectUtil.isNotEmpty(iotTriggerInfoBOList) ){
						for(IotTriggerInfoBO tmp: iotTriggerInfoBOList){
							List<IotTriggerInfoBO> objs = ProCacheUtil.getCache(CacheName.NODETRIGGERINFO, tmp.getNode_id().toString()) ;
							if( ObjectUtil.isNotEmpty( objs ) ){
								objs.add(tmp);
							}else{
								objs = new ArrayList<>();
								objs.add(tmp);
								ProCacheUtil.addCache(CacheName.NODETRIGGERINFO, tmp.getNode_id().toString() , objs);
							}
						}
					}else{
						ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.OK);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 删除（删除此触发器）
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.TriggerInfo.DEL_TRIGGER_INFO)
	public ModelAndView deleteData(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotTriggerInfoBO obj = new IotTriggerInfoBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
				obj.setUser_id(user.getId());
				obj.setNode_id(id);
				resultMap = iotTriggerInfoService.delete(obj);
				// 更新传感器触发器缓存
				if(ResultMapUtils.isOk(resultMap)){
					// 更新这个设备node的触发器
					ProCacheUtil.removeCache(CacheName.NODETRIGGERINFO, obj.getNode_id().toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
