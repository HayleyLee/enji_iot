package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.AlarmTriggerRecordService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.AlarmTriggerRecordBO;
import com.enji_iot.util.Entity.bo.UserAccountInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
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

public class AlarmTriggerRecordController {
	@Autowired
	@Qualifier(value = "AlarmTriggerRecordService")
	private AlarmTriggerRecordService alarmTriggerRecordService;
	/**
	 * 检索
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmTriggerRecord.ALARM_TRIGGER_RECORD_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                   @RequestBody AlarmTriggerRecordBO obj, @RequestParam(required = false) Integer pageSize,
                                   @RequestParam Integer paged) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = alarmTriggerRecordService.selectPageList(obj,new ResultMapUtils().getPageBean(paged, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 检索 短信和语音的发送量
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmTriggerRecord.ALARM_TRIGGER_RECORD_STATISTIC_PAGE)
	public ModelAndView selectStatisticPage(HttpServletResponse response,
                                            @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                            @RequestBody AlarmTriggerRecordBO obj, @RequestParam(required = false) Integer pageSize,
                                            @RequestParam Integer paged) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = alarmTriggerRecordService.selectStatisticPage(obj,new ResultMapUtils().getPageBean(paged, pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 插入
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmTriggerRecord.ALARM_TRIGGER_RECORD)
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                             @RequestBody AlarmTriggerRecordBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = alarmTriggerRecordService.insert(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 查询单个
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.AlarmTriggerRecord.ALARM_TRIGGER_RECORD)
	public ModelAndView selectOne(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = alarmTriggerRecordService.selectOne(new AlarmTriggerRecordBO(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 更新
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.AlarmTriggerRecord.ALARM_TRIGGER_RECORD)
	public ModelAndView update(HttpServletResponse response, @RequestBody AlarmTriggerRecordBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = alarmTriggerRecordService.update(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}

	/**
	 * 修改短信和语音的数量
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.AlarmTriggerRecord.ALARM_TRIGGER_RECORD_UPDATE)
	public ModelAndView updateInfoNum(HttpServletResponse response, @RequestBody AlarmTriggerRecordBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = alarmTriggerRecordService.updateSmsVocieNum(obj);
			// 这边更新的该用户的缓存
			if(ResultMapUtils.isOk(resultMap)){
				// 
				UserAccountInfoBO userAccount = ProCacheUtil.getCache(CacheName.USERACCOUNT_ID	, obj.getUser_id().toString()) ;
				if( ObjectUtil.isEmpty(userAccount.getSms_num())  ){
					userAccount.setSms_num(0);
				}
				if( ObjectUtil.isEmpty(userAccount.getVoice_num())  ){
					userAccount.setVoice_num(0);
				}
				userAccount.setSms_num( userAccount.getSms_num()+ 0 + obj.getSms_num()  );
				userAccount.setVoice_num( userAccount.getVoice_num()+ 0 + obj.getVoice_num()  );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.AlarmTriggerRecord.ALARM_TRIGGER_RECORD)
	public ModelAndView delete(HttpServletResponse response, @RequestParam Integer id) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			AlarmTriggerRecordBO obj = new AlarmTriggerRecordBO();
			if (ObjectUtil.isEmpty(id)) {
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			} else {
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = alarmTriggerRecordService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
}
