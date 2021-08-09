package com.enji_iot.develop.Controller;

import com.alibaba.fastjson.JSON;
import com.enji_iot.develop.Service.UserAccountInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURL;
import com.enji_iot.util.Entity.bo.AliyunParamBO;
import com.enji_iot.util.Entity.bo.UserAccountInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.develop.Service.UserService;
import com.enji_iot.util.Util.AliyunSmsAndVoiceUtil;
import com.enji_iot.util.Util.CommonUtil;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;


@Controller
public class UserController {

	@Autowired
	@Qualifier(value = "UserService")
	private UserService userSerivce;

	@Autowired
	@Qualifier(value = "UserAccountInfoService")
	private UserAccountInfoService userAccountInfoService;

	/**
	 * 用户注册
	 * 
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.User.USER_REGISTER )
	public ModelAndView userRegister(HttpServletRequest req, HttpServletResponse resp,
                                     @RequestBody UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if( ObjectUtil.isNotEmpty(user.getRegister_type()) && user.getRegister_type() == 1 ){
				// 短信携带验证码过来注册
				String register_code =  user.getValidate_code() ;
				String code = ProCacheUtil.getCache(CacheName.USER_SMS, user.getPhone()+"_code");
				if(code.equals(register_code)){
					// 成功
					resultMap = userSerivce.userRegisterByPhone(user);		
				}else{
					// 激活码过去
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.CODE_ERROR);
				}
			}else{
				resultMap = userSerivce.userRegister(user);				
			}
			if(ResultMapUtils.isOk(resultMap)){
				user = (UserInfoBO) ResultMapUtils.getData(resultMap);
				// 注册成功，添加缓存
				ProCacheUtil.addCache(CacheName.USERINFO, user.getUser_key() ,user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	/**
	 * 账户验证激活-页面
	 * @param req
	 * @param resp
	 * @param validatecode
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.User.MAIL_MESSAGE )
	public ModelAndView mailMessage(HttpServletRequest req, HttpServletResponse resp,
                                    @PathVariable String validatecode) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = userSerivce.validateCode(validatecode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap,"/oss/iot/message","Info");
	}
	
	/**
	 * 重置密码-页面
	 * @param req
	 * @param resp
	 * @param validatecode
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.User.MAIL_RESET_PASSWORD_MESSAGE )
	public ModelAndView mailResetPasswordMessage(HttpServletRequest req, HttpServletResponse resp,
                                                 @PathVariable String validatecode) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = userSerivce.mailValidatePassword(validatecode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap,"/oss/iot/resetpassword","Info");
	}
	
	/**
	 * 重置密码，发送邮件
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.User.RESET_PASSWORD )
	public ModelAndView resetPassword(HttpServletRequest req, HttpServletResponse resp,
                                      @RequestBody UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = userSerivce.resetPassword(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	/**
	 * 获取账号验证码
	 * 
	 * @param contentType
	 * @param body
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.User.VALIDATE_BY_NAME)
	public ModelAndView getSecurityCode(HttpServletResponse response, @PathVariable String name) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = userSerivce.sendSecurityCode(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 修改密码
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURL.User.USER )
	public ModelAndView resetPasswordOver(HttpServletRequest req, HttpServletResponse resp,
                                          @RequestBody UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = userSerivce.updatePassword(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	
	/**
	 * 修改密码
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURL.User.USER_MODIFY_PASSWORD )
	public ModelAndView modifyPasswordOver(HttpServletRequest req, HttpServletResponse resp,
                                           @RequestBody UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 根据id 获取用户信息
			UserInfoBO userCache = ProCacheUtil.getCache(CacheName.USERINFO, user.getUser_key());
			if( userCache.getPassword().equalsIgnoreCase(user.getPassword()) ){
				user.setPassword(user.getNewpassword());
				resultMap = userSerivce.updatePasswordByKey(user);
				userCache.setPassword(user.getNewpassword());
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PASSWORD_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	/**
	 * 微信公众号信息 绑定
	 * 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURL.User.WX_USER_BIND )
	public ModelAndView wxBind(HttpServletRequest req, HttpServletResponse resp,
                               @RequestBody UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(ObjectUtil.hasNull(user.getName(),user.getPassword(),user.getWx_open_id()  )){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				// ...
				String wxOpenId = user.getWx_open_id();
				user.setWx_open_id(null);
				resultMap = userSerivce.selectOne(user);
				if(ResultMapUtils.isOk(resultMap)){
					// 则更新用户信息
					user.setWx_open_id(wxOpenId);
					user.setId( ((UserInfoBO) ResultMapUtils.getData(resultMap)).getId());
					userSerivce.update(user);
					
					user =  (UserInfoBO) ResultMapUtils.getData(resultMap);
					user.setWx_open_id(wxOpenId);
					req.getSession().setAttribute("user", user);
					// -- 更新缓存
					ProCacheUtil.addCache(CacheName.USERINFO_OPENID, wxOpenId, user);
				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.USERNAME_OR_PASSWORD_ERROR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	/**
	 * 微信小程序 用户绑定
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.User.WP_USER_BIND )
	public ModelAndView userBind(HttpServletRequest req, HttpServletResponse resp,
                                 @RequestBody UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(ObjectUtil.hasNull(user.getName(),user.getPassword(),user.getWp_id() )){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				// ...
				String wp_id = user.getWp_id();
				user.setWp_id(null);
				resultMap = userSerivce.selectOne(user);
				if(ResultMapUtils.isOk(resultMap)){
					// 则更新用户信息
					user.setWp_id(wp_id);
					user.setId( ((UserInfoBO) new ResultMapUtils().getData(resultMap)).getId());
					userSerivce.update(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	/**
	 * 小程序 携带wp_id来获取用户信息
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.User.WP_USER_LOGIN )
	public ModelAndView wPUserLogin(HttpServletRequest req, HttpServletResponse resp,
                                    @RequestParam String code){
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try{
			if( ObjectUtil.isNotEmpty(code) ){
				UserInfoBO userBo = new UserInfoBO();
				userBo.setWp_id(code);
				resultMap = userSerivce.selectOne(userBo);
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	/**
	 * 用户登录
	 * 
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.User.USER_LOGIN )
	public ModelAndView userLogin(HttpServletRequest req, HttpServletResponse resp,
                                  @RequestBody UserInfoBO user) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(CommonUtil.isBlank(user.getName()) || CommonUtil.isBlank(user.getPassword())){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.USERNAME_OR_PASSWORD_ERROR);
			}else{
				UserInfoBO userInfo = ResultMapUtils.getData(userSerivce.selectOneLogin(user));
				if(ObjectUtil.isNotEmpty(userInfo)){
					if(userInfo.getStatus() == Code.UserStatus.UN_ACTIVED){
						ResultMapUtils.putStatusCode(resultMap, Code.UserStatus.UN_ACTIVED); // 未激活
					}else if(userInfo.getStatus() == Code.UserStatus.FORBIDDEN){
						ResultMapUtils.putStatusCode(resultMap,  Code.UserStatus.FORBIDDEN); // 已禁用
					}else if(userInfo.getStatus() == Code.UserStatus.NORMAL){
						req.getSession().setAttribute("user", userInfo);
						userInfo.setPassword(null);
						ResultMapUtils.putData(resultMap, userInfo);
					}else {
						ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
					}
				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.USERNAME_OR_PASSWORD_ERROR);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	/**
	 * 用户退出
	 * @param req
	 * @param resp
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.User.USER_LOGOUT )
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse resp, @RequestParam(required=false) Integer type ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			req.getSession().setAttribute("user", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(type!= null && type == 1){
			return  new ModelAndView("redirect:/service/wiot/login");
		}
		return  new ModelAndView("redirect:/service/iot/login");
	}
	
	/**
	 * 检索用户列表
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.User.USER_PAGE)
	public ModelAndView selectUsers(HttpServletResponse response,
                                    @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                    @RequestBody UserInfoBO userInfoBO,
                                    @RequestParam(required=false) Integer pageSize ,
                                    @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO());
			if(ObjectUtil.isNotEmpty(user)){
				if(!UserUtil.verifyUserRole(userKey,Code.UserType.SUPER) &&  ObjectUtil.isEmpty(userInfoBO.getAid())  ){
					userInfoBO.setAid(user.getId());
				}
				resultMap = userSerivce.select(userInfoBO,new ResultMapUtils().getPageBean(paged,pageSize));
			}else{
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 修改用户信息
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURL.User.USER_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody UserInfoBO userInfoBO ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO());
//			if(!UserUtil.verifyUserRole(userKey,Code.UserType.SUPER) &&  ObjectUtil.isEmpty(userInfoBO.getAid())){
//				userInfoBO.setAid(user.getId());
//			}
			userInfoBO.setMid(user.getId());
			resultMap = userSerivce.update(userInfoBO);
			if(ResultMapUtils.isOk(resultMap)){
				ProCacheUtil.addCache(CacheName.USERINFO, userInfoBO.getUser_key() ,userInfoBO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 修改当前用户信息
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURL.User.USER_INFO_MODIFY)
	public ModelAndView updateSelf(HttpServletResponse response, HttpServletRequest req ,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody UserInfoBO userInfoBO ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO());
			userInfoBO.setId(user.getId());
			resultMap = userSerivce.update(userInfoBO);
			if(ResultMapUtils.isOk(resultMap)){
				UserInfoBO userInfo = UserUtil.getUserInfoBySession(req);
				userInfo.setPhone(userInfoBO.getPhone());
				userInfo.setEmail(userInfoBO.getEmail());
				userInfo.setNick_name(userInfoBO.getNick_name());
				req.getSession().setAttribute("user", userInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	/**
	 * 增加用户
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURL.User.USER_INFO)
	public ModelAndView add(HttpServletResponse response,
                            @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                            @RequestBody UserInfoBO userInfoBO ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 这边需要处理，不允许用户名、邮箱和手机号重复
			UserInfoBO userInfo = new UserInfoBO();
			userInfo.setName(userInfoBO.getName());
			if( userSerivce.count(userInfo) >0){
				// 用户名重复
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.NAME_EXIST);
			}else{
				userInfo.setName(null);
				userInfo.setEmail(userInfoBO.getEmail());
				if( userSerivce.count(userInfo) >0 ){
					// 邮箱重复
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.EMAIL_EXIST);
				}else{
					userInfo.setPhone(userInfoBO.getPhone());
					userInfo.setEmail(null);
					if(userSerivce.count(userInfo) >0){
						// 电话号码重复
						ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.UserInfo.PHONE_EXIST);
					}					
				}
			}
			if(ResultMapUtils.isOk(resultMap)){
				userInfoBO.setAid(ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO()).getId());
				userInfoBO.setUser_key(CommonUtil.UUIDString.getUUIDString());
				userInfoBO.setRegister_time(new Date());
				userInfoBO.setStatus(Code.UserStatus.NORMAL);
				resultMap = userSerivce.insert(userInfoBO);
				if(ResultMapUtils.isOk(resultMap)){
					ProCacheUtil.addCache(CacheName.USERINFO, userInfoBO.getUser_key() ,userInfoBO);
					/**
					 * 添加用户的账户表
					 */
					UserAccountInfoBO userAccountBo = new UserAccountInfoBO();
					userAccountBo.setUser_id(userInfoBO.getId());
					resultMap = userAccountInfoService.insertSimple(userAccountBo);
					if(ResultMapUtils.isOk(resultMap)){
						ProCacheUtil.addCache(CacheName.USERACCOUNT_ID, userAccountBo.getUser_id().toString() ,userAccountBo);
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 删除用户
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURL.User.USER_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				UserInfoBO obj = new UserInfoBO();
				if(id != ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO()).getId()+0){
					if( UserUtil.verifyUserRole(userKey,Code.UserType.SUPER) ){

					}else{
						obj.setAid(ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO()).getId());					
					}
					obj.setId(id);
					obj.setDelete_flag(Constants.DELETE.YES);
					resultMap = userSerivce.update(obj);
					if(ResultMapUtils.isOk(resultMap)){
						// 清空缓存信息
						UserInfoBO tmp =new UserInfoBO();
						tmp.setId(id);
						tmp.setDelete_flag(Constants.DELETE.YES);
						resultMap = userSerivce.selectOne(tmp);
						tmp = ResultMapUtils.getData(resultMap);
						ProCacheUtil.removeCache(CacheName.USERINFO, tmp.getUser_key());
					}
				}else{
					ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 获取用户信息
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.User.USER_INFO)
	public ModelAndView getOne(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				UserInfoBO obj = new UserInfoBO();
				if( UserUtil.verifyUserRole(userKey,Code.UserType.SUPER) ){
					;
				}else{
//					obj.setAid(ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO()).getId());
				}
				obj.setDelete_flag(Constants.DELETE.NO);
				obj.setId(id);
				resultMap = userSerivce.selectOne(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 发送验证码信息
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURL.User.ACCOUNT_SECURITY_CODE)
	public ModelAndView getSecurityCode(HttpServletRequest req, HttpServletResponse resp,
                                        @PathVariable String phone , @RequestParam String code ){
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			// 这边判断验证码有没有填写成功
			String scode = (String) req.getSession().getAttribute("validate_code");
		    Long ltime =  (Long) req.getSession().getAttribute("validate_code_time");
		    if(ObjectUtil.isEmpty(scode)){
		    	ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.CODE_TIME_ERROR);
		    }else{
		    	if( ! scode.equalsIgnoreCase(code) ){
		    		ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.CODE_ERROR);
		    	}
		    	if(  ltime + 2*60*1000 <= new Date().getTime()){
		    		ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.CODE_TIME_ERROR);
		    	}		    	
		    }
		    if(ResultMapUtils.isOk(resultMap)){
		    	req.getSession().removeAttribute("validate_code");
			    req.getSession().removeAttribute("validate_code_time");
		    	
		    	Integer num = ProCacheUtil.getCache(CacheName.USER_SMS, phone) ;
		    	if(ObjectUtil.isNotEmpty(num)){
		    		if( num > 5){
		    			// 验证码超过数量
		    			ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.VALIDATER_ALLER);
		    			return new ResultMapUtils().getModelAndView(resp, resultMap);
		    		}
		    		ProCacheUtil.addCache(CacheName.USER_SMS, phone, num+1);
		    	}else{
		    		ProCacheUtil.addCache(CacheName.USER_SMS, phone, 1 );
		    	}
		    	// ---
		    	String gcode = ObjectUtil.getSixRandomCode();
		    	
		    	// 设置到缓存中，过期时间暂不设置
		    	ProCacheUtil.addCache(CacheName.USER_SMS, phone+"_code", gcode);
		    	
		    	// 智能短信平台，发送短信 云片网
		    	// yunpianUtil.sendSms("您的验证码是"+ gcode + "。如非本人操作，请忽略本短信", phone);
		    	
		    	//
		    	AliyunParamBO aliyunParamBo = new AliyunParamBO();
		    	aliyunParamBo.setSignaName(ProConfig.AliyunShortMessage.SIGNATURE);
		    	aliyunParamBo.setPhonenumber(phone);
		    	aliyunParamBo.setTemplateCode(ProConfig.AliyunShortMessage.SMS_TEMPLATE_CODE);
		    	Map<String,String> map = new HashedMap();
		    	map.put("code", gcode);
		    	aliyunParamBo.setTemplateParam( JSON.toJSONString(map));
		    	AliyunSmsAndVoiceUtil.sendSms(aliyunParamBo);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultMap);
	}
	
	/**
	 * 图片验证码生成
	 * @param req
	 * @param resp
	 * @throws IOException
	 * <img alt="验证码" title="点击更新" id="validate-code" src="<%=basePath%>/service/validate/code" >
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/validate/code")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int width = 90;			// 定义图片的width
	    int height = 38;		// 定义图片的height
	    int codeCount = 4;		// 定义图片上显示验证码的个数
	    int xx = 15;
	    int fontHeight = 22;
	    int codeY = 28;
	    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
	            'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);
        // 随机产生20条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = random.nextInt(255) , green = random.nextInt(255), blue = random.nextInt(255);
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length-1)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            // 用随机产生的颜色将验证码绘制到图像中。
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);
            // 将产生的四个随机数组合在一起。
            randomCode.append(code);
        }
        // 将四位数字的验证码保存到Session中。
        req.getSession().setAttribute("validate_code", randomCode.toString());
        req.getSession().setAttribute("validate_code_time", System.currentTimeMillis());
        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }
}
