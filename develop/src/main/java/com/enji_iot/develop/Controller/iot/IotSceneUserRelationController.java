package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotSceneUserRelationService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.IotSceneUserRelationBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
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
public class IotSceneUserRelationController {
	@Autowired
	@Qualifier(value = "IotSceneUserRelationService")
	private IotSceneUserRelationService iotSceneUserRelationService;

	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SceneUserRelation.SCENE_USER_RELATION_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotSceneUserRelationBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotSceneUserRelationService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	//SCENE_USER_RELATION_CHANGE
	/**
	 * 切换场景信息
	 * @param response
	 * @param userKey
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.SceneUserRelation.SCENE_USER_RELATION_CHANGE )
	public ModelAndView changeScene(HttpServletResponse response,
                                    @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                    @RequestBody IotSceneUserRelationBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user2 = UserUtil.getUserInfoByUserKey(obj.getUserKey());
			if(ObjectUtil.isNotEmpty(user2)){
				UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
				obj.setUser_id(user.getId());
				// 删除当前用户授权信息
				iotSceneUserRelationService.deleteUserSceneRelation(obj);
				// 添加新用户的授权
				obj.setUser_id(user2.getId());
				iotSceneUserRelationService.insert(obj);
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_DATA);
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
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.SceneUserRelation.SCENE_USER_RELATION )
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody IotSceneUserRelationBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setAid(user.getId());
			if(ObjectUtil.isNotEmpty(obj.getIds())){
				String[] ids = obj.getIds().split(",");
				// 删除当前用户的全部授权
				iotSceneUserRelationService.deleteUserRelation(obj);
				for(int i=0;i<ids.length;i++){
					obj.setScene_id(Integer.parseInt(ids[i]));
					resultMap = iotSceneUserRelationService.insert(obj);
				}
			}else {
				if(ObjectUtil.isNotEmpty(obj.getScene_id())){
					resultMap = iotSceneUserRelationService.insert(obj);
				}else{
					// 不授权场景给某个用户
					// 删除当前用户的全部授权
					iotSceneUserRelationService.deleteUserRelation(obj);
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
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.SceneUserRelation.SCENE_USER_RELATION )
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			
			resultMap = iotSceneUserRelationService.selectOne(new IotSceneUserRelationBO(id));
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
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.SceneUserRelation.SCENE_USER_RELATION )
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody IotSceneUserRelationBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotSceneUserRelationService.update(obj);
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
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.SceneUserRelation.SCENE_USER_RELATION )
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotSceneUserRelationBO obj = new IotSceneUserRelationBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = iotSceneUserRelationService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
