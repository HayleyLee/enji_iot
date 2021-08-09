package com.enji_iot.develop.Service.base.impl;

import com.enji_iot.develop.Service.base.ShortMessageService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Util.ResultMapUtils;
import com.enji_iot.util.Util.yunpianUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;


/**
 * 
 * 短信服务
 *
 */

@Service
public class ShortMessageServiceImpl extends ResultMapUtils  implements ShortMessageService {

	@Override
	public Map<String, Object> sendSms(String tpl_id, Map<String, Object> tpl_value, String mobile) {
		return null;
	}

	@Override
	public Map<String, Object> sendValidateCode(String mobile, String code) {
		return null;
	}
	
	@Override
	public Map<String, Object> sendSms(String message, String mobile) {
		Map<String, Object> resultMap = getResultMap();
		try {
			yunpianUtil.sendSms(message, mobile);
		} catch (IOException e) {
			super.exception(e, message,mobile);
			putStatusCode(resultMap, Code.ResponseCode.SystemCode.EXEC_FAIL);
		}
		return resultMap;
	}

	
	
}
