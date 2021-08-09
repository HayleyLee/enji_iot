package com.enji_iot.develop.Controller;

import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.util.Util.HttpServiceSender;
import com.enji_iot.util.Util.JsonUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class WebViewController{
	
	private final static String VIEW_PATH = "/oss/base/" ;
	
	/**
	 * 非超级管理员用户登录不了后台
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/oss")
	public String ossManager(HttpServletRequest req, HttpServletResponse resp) {
		UserInfoBO userInfo = UserUtil.getUserInfoBySession(req);
		if(userInfo.getType() == Code.UserType.SUPER){
			return VIEW_PATH + "index";			
		}else{
			return "redirect:/";
		}
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/base/{viewName}")
	public String viewPage(HttpServletRequest req, HttpServletResponse resp,
			@PathVariable String viewName) {
		setModel(req,viewName);
		return VIEW_PATH + viewName;
	}
	
	/**
	 * 根据经纬度获取省市
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/baidu/api/geocoder")
	@ResponseBody
	public String baiduApiGeocoder(String lat,String lon,HttpServletRequest req, HttpServletResponse resp) {
		String url = ProConfig.BD_API_GEOCODER+"?location="+lat+","+lon+"&key="+ProConfig.Map.BAIDU_MAP_KEY+"&output=json";
		String[] strings = HttpServiceSender.doGet(url);
		JSONObject jsonObject = JsonUtils.getJSONObject(strings[1]);
		return jsonObject.toString();
	}
	
	private void setModel(HttpServletRequest req , String viewName){
		
	}
}
