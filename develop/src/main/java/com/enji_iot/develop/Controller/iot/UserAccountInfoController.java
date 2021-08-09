package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.UserAccountInfoService;
import com.enji_iot.util.Entity.bo.UserAccountInfoBO;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller

public class UserAccountInfoController {
	@Autowired
	@Qualifier(value = "UserAccountInfoService")
	private UserAccountInfoService userAccountInfoService;
	/**
	 * 查询单个
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.UserAccountInfo.USER_ACCOUNT_INFO)
	public ModelAndView selectOne(HttpServletResponse response, @RequestParam Integer user_id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = userAccountInfoService.selectDetail(new UserAccountInfoBO(user_id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	
}
