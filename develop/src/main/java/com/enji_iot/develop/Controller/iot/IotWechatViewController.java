package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.DAO.UserInfoMapper;
import com.enji_iot.develop.Service.ContactUserInfoService;
import com.enji_iot.develop.Service.IotVideoInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.ContactUserInfoBO;
import com.enji_iot.util.Entity.bo.IotVideoInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.BrowserJudge;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import com.enji_iot.util.Util.SpringApplicationContext;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class IotWechatViewController {
	@Resource
	private UserInfoMapper userInfoMapper;

//	@Resource(name = "wxMpService")
//	@Resource
//	private WxMpServiceImpl wxMpService;

	@Autowired
	@Qualifier(value = "IotVideoInfoService")
	private IotVideoInfoService iotVideoInfoService;

	@Autowired
	@Qualifier(value = "ContactUserInfoService")
	private ContactUserInfoService contactUserInfoService;

	private final static String VIEW_PATH = "/wechat/iot/" ;

	/**
	 * 获取jsapi ticket
	 * 
	 * @param req
	 * @param url
	 * @throws WxErrorException
	 */
	private void getJsApiTicket(HttpServletRequest req, String url)  {
		Map<String, String> ret;
		try {
			WxMpServiceImpl wxMpService = (WxMpServiceImpl) SpringApplicationContext.getBean("WxMpServiceImpl");
			ret = BrowserJudge.Weixin.sign(wxMpService.getJsapiTicket(), url);
			req.setAttribute("noncestr", ret.get("nonceStr"));
			req.setAttribute("timestamp", ret.get("timestamp"));
			req.setAttribute("signature", ret.get("signature"));
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/wiot/{viewName}")
	public String wechatPage(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable String viewName) {
		setModelWeb(req,viewName);
		return VIEW_PATH + viewName;
	}
	private void setModelWeb(HttpServletRequest req , String viewName){
		if("edevice".equals(viewName) || "deviceConfigwifi".equals(viewName) || "edevicescene".equals(viewName) ){
			// 增加微信公众号的识别
			if(ObjectUtil.isEmpty(req.getQueryString())){
				getJsApiTicket(req, req.getRequestURL().toString());
			}else{
				getJsApiTicket(req, req.getRequestURL().toString() + "?"+req.getQueryString());
			}
		}
	}
	@RequestMapping(method = RequestMethod.GET, value = "/wiot/user")
	public ModelAndView userInfo(HttpServletRequest req, HttpServletResponse resp) {
		Map<String,Object> resultData = ResultMapUtils.getResultMap();
		try{
			UserInfoBO user = UserUtil.getUserInfoBySession(req);
			// 查询子账户的数量
			UserInfoBO userInfo = new UserInfoBO() ;
			userInfo.setAid(user.getId());
			int num = userInfoMapper.selectCount(userInfo);
			ResultMapUtils.putData(resultData, num);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultData,VIEW_PATH+"user","info");
	}
	@RequestMapping(method = RequestMethod.GET, value = "/wiot/live")
	public ModelAndView live(HttpServletRequest req, HttpServletResponse resp , @RequestParam Integer id ) {
		Map<String,Object> resultData = ResultMapUtils.getResultMap();
		try{
			resultData = iotVideoInfoService.selectOne(new IotVideoInfoBO(id));
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultData,VIEW_PATH+"live","info");
	}
	@RequestMapping(method = RequestMethod.GET, value = "/wiot/cbind")
	public ModelAndView cbind(HttpServletRequest req, HttpServletResponse resp , @RequestParam Integer id ) {
		Map<String,Object> resultData = ResultMapUtils.getResultMap();
		try{
			ContactUserInfoBO obj = new ContactUserInfoBO(id);
			resultData = contactUserInfoService.selectOne(obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(resp, resultData,VIEW_PATH+"cbind","info");
	}
}
