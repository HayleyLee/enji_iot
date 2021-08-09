package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.DAO.IotSceneInfoMapper;
import com.enji_iot.develop.Service.IotSceneInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.IotNodeInfoBO;
import com.enji_iot.util.Entity.bo.IotNodeUnitDataBO;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Code.ResponseCode;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.develop.Service.IotNodeInfoService;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import com.enji_iot.mqtt.iot.ProtocolUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
public class IotNodeInfoController {

	@Autowired
	@Qualifier(value = "IotNodeInfoService")
	private IotNodeInfoService iotNodeInfoService ;

	/**
	 * 统计设备
	 */
	@RequestMapping(method = RequestMethod.POST,value = RequestURLIOT.NodeInfo.NODE_COUNT)
	public @ResponseBody JSONArray selectCount(String user_id){
		IotSceneInfoBO sceneInfo = new IotSceneInfoBO();
		sceneInfo.setUser_id(Integer.parseInt(user_id));
		return iotNodeInfoService.selectCount(sceneInfo);
	}

	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotNodeInfoBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotNodeInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 设备统计
	 * @param response
	 * @param userKey
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_STATISTIC)
	public ModelAndView selectStatisticNode(HttpServletResponse response,
                                            @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                            @RequestBody IotNodeInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId()); 
			resultMap = iotNodeInfoService.selectStatisticNodeInfo(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 检索 - 管理员使用
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.ADMIN_NODE_INFO_PAGE)
	public ModelAndView selectALLNodePage(HttpServletResponse response,
                                          @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                          @RequestBody IotNodeInfoBO obj,
                                          @RequestParam(required=false) Integer pageSize ,
                                          @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			if(user.getType().equals(Code.UserType.SUPER)){
				resultMap = iotNodeInfoService.selectPage(obj,new ResultMapUtils().getPageBean(paged,pageSize));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 设备仓库 
	 * 未使用设备
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.ADMIN_UNUSED_NODES)
	public ModelAndView selectALLUnusedDevice(HttpServletResponse response,
                                              @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                              @RequestBody IotNodeInfoBO obj,
                                              @RequestParam(required=false) Integer pageSize ,
                                              @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			if(user.getType() == Code.UserType.SUPER ){
				resultMap = iotNodeInfoService.selectUnUsedNode(obj,new ResultMapUtils().getPageBean(paged,pageSize));
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
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_INFO )
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody IotNodeInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if( ObjectUtil.isNotEmpty(obj.getScene_id()) ){
				IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code()) ;
				// 判断device_code是否重复
				if(ObjectUtil.isNotEmpty(nodeInfo)){
					ResultMapUtils.putStatusCode(resultMap, ResponseCode.IotInfo.DEVICE_CODE_EXIST);
				}else{
					obj.setIot_node_status(CodeIot.DEVICE_STATUS.UNCONTECT);
					obj.setMtime(new Date());
					obj.setDisable_flag(0);
					resultMap = iotNodeInfoService.saveNodeInfo(obj);					
				}
			}else{
				ResultMapUtils.putStatusCode(resultMap, ResponseCode.SystemCode.PARAM_ERROR);
			}
			if(ResultMapUtils.isOk(resultMap) ){
				ProCacheUtil.addCache(CacheName.NODEINFO, obj.getId().toString(), obj);
				ProCacheUtil.addCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code(), obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 根据设备仓库中的设备号添加
	 * @param response
	 * @param userKey
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_BIND)
	public ModelAndView deviceAdd(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestBody IotNodeInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if( ObjectUtil.isNotEmpty(obj.getDevice_code()) && ObjectUtil.isNotEmpty(obj.getScene_id()) ){
				// 
				IotNodeInfoBO nodeInfo = ProCacheUtil.getCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code()) ;
				if( ObjectUtil.isNotEmpty(nodeInfo)   ){
					//
					if( nodeInfo.getScene_id() != null){
						// 设备码已使用
						ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.IotInfo.DEVICE_CODE_USED) ;
					}else{
						obj.setId(nodeInfo.getId());
						// id , scene_id  , device_code
						resultMap = iotNodeInfoService.update(obj);
						if(ResultMapUtils.isOk(resultMap)){
							nodeInfo.setScene_id( obj.getScene_id() );
						}
					}
				}else{
					// 设备码不存在
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.IotInfo.DEVICE_CODE_NOT_EXIST); ;
				}
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR) ;
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
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.NodeInfo.NODE_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotNodeInfoService.selectOne(new IotNodeInfoBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * LPM 更新网关状态
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.NodeInfo.NODE_STATUS_INFO)
	public ModelAndView updateNodeStatus(HttpServletResponse response,
                                         @RequestBody IotNodeInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotNodeInfoService.updateNodeStatus(obj);
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
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.NodeInfo.NODE_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody IotNodeInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
				obj.setUser_id(user.getId());
				resultMap = iotNodeInfoService.update(obj);
			// 更新网关下传感器的经纬度数值
//			IotSensorInfoBO iotSensorInfoBo = new IotSensorInfoBO();
//			iotSensorInfoBo.setNode_id(obj.getId());
//			iotSensorInfoBo.setIot_sensor_type(CodeIot.SENSOR_TYPE.POSITION);
//			iotSensorInfoBo.setStr_sdata(obj.getLonLat());
//			service.update("IotSensorInfo.updateSensorBySelf", iotSensorInfoBo);
			if(ResultMapUtils.isOk(resultMap)){
				// 更新device
				IotNodeInfoBO pre = ProCacheUtil.getCache(CacheName.NODEINFO, obj.getId().toString());
				ProCacheUtil.addCache(CacheName.NODEINFO, obj.getId().toString(), obj);
				if(ObjectUtil.isEmpty(obj.getDevice_code())){
					// 这边是迁移设备到其他项目中
					pre.setScene_id( obj.getScene_id() );
				}else{
					List<IotNodeInfoBO> iotNodeInfoList = iotNodeInfoService.select(obj);
					if(pre.getDevice_code().equals(obj.getDevice_code())){
						if( ObjectUtil.isNotEmpty(iotNodeInfoList) ){
							for(IotNodeInfoBO objs: iotNodeInfoList){
								ProCacheUtil.addCache(CacheName.NODEINFO, obj.getId().toString(), objs);
								ProCacheUtil.addCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code(), objs);
							}
						}
					}else{
						if( ObjectUtil.isNotEmpty(iotNodeInfoList) ){
							for(IotNodeInfoBO objs: iotNodeInfoList){
								ProCacheUtil.addCache(CacheName.NODEINFO, obj.getId().toString(), objs);
								ProCacheUtil.addCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code(), objs);
							}
						}
//						ProCacheUtil.addCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code(), obj);
						ProCacheUtil.removeCache(CacheName.NODEINFO_DEVICECODE, pre.getDevice_code());
					}
					if(obj.getIot_node_type() == 83 ){
						// 修改网关信息后，则设置设备重新连接  TCP协议
						ProtocolUtil.sendGatewayRestart(obj) ;											
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	/**
	 * 超级管理员专属 更新
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.NodeInfo.NODE_UPDATE)
	public ModelAndView updateDisable(HttpServletResponse response,
                                      @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                      @RequestBody IotNodeInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotNodeInfoService.update(obj);
			if(ResultMapUtils.isOk(resultMap)){
				//更新缓存
				List<IotNodeInfoBO> iotNodeInfoLists = iotNodeInfoService.select(obj);
				for (IotNodeInfoBO nodei: iotNodeInfoLists) {
					ProCacheUtil.addCache(CacheName.NODEINFO_DEVICECODE,nodei.getDevice_code(),nodei);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 设备excel导入
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/node/upload")
	public ModelAndView upload(HttpServletResponse response,
                               @RequestParam(required = false,value="type") String type,
                               @RequestParam(required = false,value="property") String[] property,
                               @RequestParam(required = false, value = "file") MultipartFile file) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotNodeInfoService.importTemplateNodeInfo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 检索所有设备
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_INFO_INFO_PAGE)
	public ModelAndView selectNodeListPage(HttpServletResponse response,
                                           @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                           @RequestBody IotNodeInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());

			resultMap = iotNodeInfoService.selectNodeDevCode(obj);
//			JSONObject cache = ProCacheUtil.getCache(CacheName.SENSOR_DEV_INFO, obj.getDevice_code());
//			System.err.println("map对象："+cache.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查找设备传上来的数据
	 * @param response
	 * @param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_INFO_SENSOR_INFO_DEV)
	public ModelAndView selectNodeSensorListDev(HttpServletResponse response,
                                                @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                                @RequestBody IotNodeInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = ProCacheUtil.getCache(CacheName.SENSOR_DEV_INFO, obj.getDevice_code());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 增加设备列表，支持分页
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_INFO_SENSOR_INFO_PAGES)
	public ModelAndView selectNodeSensorListPages(HttpServletResponse response,
                                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                                  @RequestParam(required=false) Integer pageSize ,
                                                  @RequestParam Integer paged,
                                                  @RequestBody IotNodeInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotNodeInfoService.selectNodeSensorList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
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
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.NodeInfo.NODE_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotNodeInfoBO obj = new IotNodeInfoBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
				if( ! UserUtil.verifyUserRole(userKey,Code.UserType.SUPER) ){
					obj.setUser_id(user.getId());					
				}
				obj.setId(id);
//				obj.setDelete_flag(Constants.DELETE.YES);
//				resultMap = service.update("IotNodeInfo.update", obj) ;	
				resultMap = iotNodeInfoService.deleteNode(obj);
				if(ResultMapUtils.isOk(resultMap)){
					// 这边再删除sensorlist 信息
//					IotSensorInfoBO sensorBo = new IotSensorInfoBO() ;
//					sensorBo.setNode_id(id);
//					sensorBo.setDelete_flag(Constants.DELETE.YES);
//					service.update("IotSensorInfo.updateSensorDelete", sensorBo);
					
					// obj 获取缓存
					obj = ProCacheUtil.getCache(CacheName.NODEINFO, obj.getId().toString());
					ProCacheUtil.removeCache(CacheName.NODEINFO, obj.getId().toString());
					ProCacheUtil.removeCache(CacheName.NODEINFO_DEVICECODE, obj.getDevice_code());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 插入用户自定义单位
	 * @param response
	 * @param userKey
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.ADD_NODE_UNIT)
	public ModelAndView deviceUnitAdd(HttpServletResponse response,
                                      @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                      @RequestBody IotNodeUnitDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotNodeInfoService.saveNodeUnit(obj);
			if (ResultMapUtils.isOk(resultMap)){
				List<IotNodeUnitDataBO> iotUnitList = iotNodeInfoService.selectNodeUnitList(new IotNodeUnitDataBO());
				//更新缓存
				ProCacheUtil.addCache(CacheName.IOT_NODE_UNIT,"unitList" ,iotUnitList);
				for(IotNodeUnitDataBO nodeUnit : iotUnitList){
					//用于触发器
					String key = nodeUnit.getScene_id() + nodeUnit.getDevice_code() + nodeUnit.getSensor_name();
					ProCacheUtil.addCache(CacheName.IOT_NODE_UNIT,key ,nodeUnit);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 更新用户自定义的单位
	 * @param response
	 * @param userKey
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.PUT_NODE_UNIT)
	public ModelAndView deviceUnitPut(HttpServletResponse response,
                                      @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                      @RequestBody IotNodeUnitDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotNodeInfoService.deviceUnitUpdate(obj);
			if (ResultMapUtils.isOk(resultMap)){
				List<IotNodeUnitDataBO> iotUnitList = iotNodeInfoService.selectNodeUnitList(new IotNodeUnitDataBO());
				//更新缓存
				ProCacheUtil.addCache(CacheName.IOT_NODE_UNIT,"unitList" ,iotUnitList);
				for(IotNodeUnitDataBO nodeUnit : iotUnitList){
					//用于触发器
					String key = nodeUnit.getScene_id() + nodeUnit.getDevice_code() + nodeUnit.getSensor_name();
					ProCacheUtil.addCache(CacheName.IOT_NODE_UNIT,key ,nodeUnit);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	/**
	 * 删除用户自定义单位
	 * @param response
	 * @param userKey
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.DEL_NODE_UNIT)
	public ModelAndView deviceUnitDel(HttpServletResponse response,
                                      @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                      @RequestBody IotNodeUnitDataBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotNodeInfoService.deviceUnitDelete(obj);
			if (ResultMapUtils.isOk(resultMap)){
				ProCacheUtil.removeCache(CacheName.IOT_NODE_UNIT,"unitList");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 检索所有设备单位
	 * @param response
	 * @param
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.SELECT_NODE_UNIT_LISTS)
	public ModelAndView selectNodeUnitList(HttpServletResponse response,
                                           @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                           @RequestBody IotNodeUnitDataBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			List<IotNodeUnitDataBO> iotUnitList = ProCacheUtil.getCache(CacheName.IOT_NODE_UNIT,"unitList");
			ArrayList<IotNodeUnitDataBO> list = new ArrayList<>();
			//如果缓存有数据就取缓存
			if (ObjectUtil.isNotEmpty(iotUnitList)){
				for (IotNodeUnitDataBO o:iotUnitList) {
					if (o.getScene_id().equals(obj.getScene_id()) && o.getDevice_code().equals(obj.getDevice_code())){
						list.add(o);
					}
				}
				ResultMapUtils.putData(resultMap, list);
			}
			else
				resultMap = iotNodeInfoService.selectNodeUnitList(obj, new PageBean());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 实时更新传感器数值( 执行器请求状态改变 )
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.NodeInfo.NODE_CONTROL_VALUE)
	public ModelAndView updateSensorControlValue(HttpServletResponse response,
												 @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
												 @RequestBody IotNodeInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(ObjectUtil.isNotEmpty(obj.getId())){
				if( ProtocolUtil.sendControlSensorCommand(obj) >-1){
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.OK);
				}
				else{
					ResultMapUtils.putStatusCode(resultMap, CodeIot.DEVICE_STATUS.OFFLINE);
				}
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
