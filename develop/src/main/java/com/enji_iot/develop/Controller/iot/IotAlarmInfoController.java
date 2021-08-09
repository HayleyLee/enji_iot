package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotAlarmInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Common.CodeIot;
import com.enji_iot.util.Common.Constants;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.IotAlarmInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.DateUtils;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Controller
public class IotAlarmInfoController {
	@Autowired
	@Qualifier(value = "IotAlarmInfoService")
	private IotAlarmInfoService iotAlarmInfoService;

	/**
	 * 检索
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmInfo.ALARM_INFO_PAGE)
	public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotAlarmInfoBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			System.err.println("userKey:"+userKey);
			System.err.println("scene_id:"+obj.getScene_id());
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotAlarmInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
			System.err.println(resultMap.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 报警统计
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmInfo.ALARM_INFO_STATISTIC)
	public ModelAndView selectAlarmStatisticInfo(HttpServletResponse response,
                                                 @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                                 @RequestBody IotAlarmInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			if(ObjectUtil.isEmpty(obj.getStart_time())){
				obj.setStart_time( DateUtils.format(DateUtils.simpleALL, DateUtils.getDate(5))  );
			}
			resultMap = iotAlarmInfoService.selectStatisticInfo(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * Excel 下载
	 * @param response
	 * @param obj
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.AlarmInfo.ALARM_INFO_EXCEL )
	public void excel(HttpServletResponse response, 
			 IotAlarmInfoBO obj ) {
		try {
			List<?> list = ResultMapUtils.getData(iotAlarmInfoService.select(obj));
			UserUtil.downExcel("报警信息下载", "tpl/xls/alarm_info_template", list, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 插入
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmInfo.ALARM_INFO )
	public ModelAndView save(HttpServletResponse response,
                             @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                             @RequestBody IotAlarmInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			resultMap = iotAlarmInfoService.insert(obj);
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
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.AlarmInfo.ALARM_INFO)
	public ModelAndView selectOne(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                  @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			IotAlarmInfoBO obj =new IotAlarmInfoBO(id);
			obj.setUser_id(user.getId());
			resultMap = iotAlarmInfoService.selectOne(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 标记所有为已读
	 * @param response
	 * @param userKey
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = RequestURLIOT.AlarmInfo.ALARM_INFO_READ)
	public ModelAndView allRead(HttpServletResponse response,
                                @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey
			) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotAlarmInfoBO obj = new IotAlarmInfoBO();
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			obj.setIot_alarm_process_status(CodeIot.PROCESS_STATUS.YES);
			resultMap = iotAlarmInfoService.setMyAllread(obj);
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
	@RequestMapping(method = RequestMethod.PUT, value = RequestURLIOT.AlarmInfo.ALARM_INFO)
	public ModelAndView update(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestBody IotAlarmInfoBO obj ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			resultMap = iotAlarmInfoService.update(obj);
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
	@RequestMapping(method = RequestMethod.DELETE, value = RequestURLIOT.AlarmInfo.ALARM_INFO)
	public ModelAndView delete(HttpServletResponse response,
                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                               @RequestParam Integer id ) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			IotAlarmInfoBO obj = new IotAlarmInfoBO();
			if(ObjectUtil.isEmpty(id)){
				ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.PARAM_ERROR);
			}else{
				UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
				obj.setUser_id(user.getId());
				obj.setId(id);
				obj.setDelete_flag(Constants.DELETE.YES);
				resultMap = iotAlarmInfoService.update(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	/**
	 * 查询(未读报警)的数量
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmInfo.ALARM_INFO_UNREAD)
	public ModelAndView selectUnreadCount(HttpServletResponse response,
                                          @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                          @RequestBody IotAlarmInfoBO obj) {
		Map<String, Object> resultMap = ResultMapUtils.getResultMap();
		try {
			UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
			obj.setUser_id(user.getId());
			Integer count = iotAlarmInfoService.count(obj);
			resultMap.put("count", count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultMapUtils().getModelAndView(response, resultMap);
	}
	
	
    /**
     * 查询所有报警（大屏统计）
     *
     * @param response
     * @param userKey
     * @param obj
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.AlarmInfo.ALARM_INFO_ALL)
    public ModelAndView selectAll(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                  @RequestBody IotAlarmInfoBO obj) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
            obj.setUser_id(user.getId());
            resultMap = iotAlarmInfoService.selectAll(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(response, resultMap);
    }
}
