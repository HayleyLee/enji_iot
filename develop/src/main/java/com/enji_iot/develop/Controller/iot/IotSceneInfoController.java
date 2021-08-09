package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotSceneUserRelationService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.IotSceneUserRelationBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.develop.Service.IotSceneInfoService;
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
import java.util.Map;


@Controller
public class IotSceneInfoController {

	@Autowired
	@Qualifier(value = "IotSceneInfoService")
	private IotSceneInfoService iotSceneInfoService;
	@Autowired
	@Qualifier(value = "IotSceneUserRelationService")
	private IotSceneUserRelationService iotSceneUserRelationService;

	/**
	 * 统计场景
	 * @param user_id 用户id
	 */
	@RequestMapping(method = RequestMethod.POST,value = RequestURLIOT.SceneInfo.SCENE_COUNT)
	public @ResponseBody JSONArray sceneCount(String user_id){
		IotSceneInfoBO sceneInfo = new IotSceneInfoBO();
		try {
			sceneInfo.setUser_id(Integer.parseInt(user_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iotSceneInfoService.sceneCount(sceneInfo);
	}
	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SceneInfo.SCENE_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotSceneInfoBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotSceneInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 超管使用检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SceneInfo.ADMIN_SCENE_INFO_PAGE)
	public ModelAndView selectALLPage(HttpServletResponse response,
                                      @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                      @RequestBody IotSceneInfoBO obj,
                                      @RequestParam(required=false) Integer pageSize ,
                                      @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			if(user.getType().equals(Code.UserType.SUPER)){
				resultMap = iotSceneInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	
	
	
	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SceneInfo.SELF_SCENE_INFO_PAGE)
	public ModelAndView selectSelfPage(HttpServletResponse response,
                                       @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                       @RequestBody IotSceneInfoBO obj,
                                       @RequestParam(required=false) Integer pageSize ,
                                       @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotSceneInfoService.selectSceneInfo(obj,new ResultMapUtils().getPageBean(paged,pageSize));
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
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SceneInfo.SCENE_INFO)
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody IotSceneInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			if(ObjectUtil.isNotEmpty(user)){
				obj.setUser_id(user.getId());
				resultMap = iotSceneInfoService.insert(obj);
				if(ResultMapUtils.isOk(resultMap)){
					// 场景插入成功，需要添加场景和用户关系
					IotSceneUserRelationBO tmp = new IotSceneUserRelationBO();
					tmp.setScene_id(obj.getId());
					tmp.setUser_id(user.getId());
					tmp.setAid(user.getId());
					resultMap = iotSceneUserRelationService.insert(tmp);
					if(ResultMapUtils.isOk(resultMap)){
						// 添加场景缓存
						ProCacheUtil.addCache(CacheName.SCENEINFO, obj.getId().toString(), obj);
					}
				}				
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
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
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.SceneInfo.SCENE_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotSceneInfoBO obj = new IotSceneInfoBO();
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			if(ObjectUtil.isNotEmpty(user)  ){
				if(!UserUtil.verifyUserRole(userKey, Code.UserType.SUPER)){
					obj.setUser_id(user.getId());					
				}
				obj.setId(id);
				resultMap = iotSceneInfoService.selectOne(obj);
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 查询场景下详情
	 * @param response
	 * @param userKey
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.SceneInfo.SCENE_DETAILL)
	public ModelAndView selectOneDetail(HttpServletResponse response,
                                        @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                        @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotSceneInfoService.getSceneDetailInfo(new IotSceneInfoBO(id)) ;
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
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.SceneInfo.SCENE_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody IotSceneInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotSceneInfoService.update(obj);
			if(ResultMapUtils.isOk(resultMap)){
				// 添加场景缓存
				ProCacheUtil.addCache(CacheName.SCENEINFO, obj.getId().toString(), obj);
			}
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
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.SceneInfo.SCENE_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotSceneInfoBO obj = new IotSceneInfoBO();
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				obj.setId(id);
				obj.setUser_id(user.getId());
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = iotSceneInfoService.update(obj);
				if(ResultMapUtils.isOk(resultMap)){
					ProCacheUtil.removeCache(CacheName.SCENEINFO, obj.getId().toString());
					// 这边需要删除，所有的关联
					IotSceneUserRelationBO iotSceneUser = new IotSceneUserRelationBO() ;
					iotSceneUser.setScene_id(id);
					iotSceneUserRelationService.deleteUserSceneRelation(iotSceneUser);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
