package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.ContactUserInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.ContactUserInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.QRCodeUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class ContactUserInfoController {
	@Autowired
	@Qualifier(value = "ContactUserInfoService")
	private ContactUserInfoService contactUserInfoService;

	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.ContactUserInfo.CONTACT_USER_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody ContactUserInfoBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = contactUserInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
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
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.ContactUserInfo.CONTACT_USER_INFO )
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody ContactUserInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = contactUserInfoService.insert(obj);
			if(ResultMapUtils.isOk(resultMap)){
				// 增加二维码，提供微信公众号绑定 [ 增加新的文件储存隔离系统 ]
				QRCodeUtil.encode(ProConfig.LOCAL_DOMAIN +"/service/wiot/cbind?id="+obj.getId() ,null, 
						ProConfig.LOCAL_FILE_PATH+"/"+Constants.FileRealPath.QRCODE+"/"+ (int) (obj.getId()/100+1)*100 
						, false,obj.getId()+"");
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
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.ContactUserInfo.CONTACT_USER_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			ContactUserInfoBO obj = new ContactUserInfoBO(id);
			obj.setUser_id(user.getId());
			resultMap = contactUserInfoService.selectOne(obj);
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
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.ContactUserInfo.CONTACT_USER_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody ContactUserInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			//UserInfoBO user = getUserInfoByUserKey(userKey);
			//obj.setUser_id(user.getId());
			resultMap = contactUserInfoService.update(obj);
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
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.ContactUserInfo.CONTACT_USER_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			ContactUserInfoBO obj = new ContactUserInfoBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
				obj.setUser_id(user.getId());
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = contactUserInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
