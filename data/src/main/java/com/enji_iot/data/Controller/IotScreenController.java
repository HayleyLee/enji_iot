package com.enji_iot.data.Controller;

import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.data.Service.*;
import com.enji_iot.data.Util.UserUtil;
import com.enji_iot.util.Common.RequestURLIOT;
import com.enji_iot.util.Entity.bo.*;
import com.enji_iot.util.Util.DateUtils;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.PageBean;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 大屏统计
 */
@Controller
@RequestMapping(value = RequestURLIOT.IotScreen.IOT_SCREEN)
public class IotScreenController{

    @Autowired
    @Qualifier(value = "IotScreenService")
    private IotScreenService iotScreenService;

    @Autowired
    @Qualifier(value = "IotScreenSceneInfoService")
    private IotScreenSceneInfoService iotSceneInfoService;

    @Autowired
    @Qualifier(value = "IotScreenAlarmInfoService")
    private IotScreenAlarmInfoService iotAlarmInfoService;

    @Autowired
    @Qualifier(value = "IotScreenNodeInfoService")
    private IotScreenNodeInfoService iotNodeInfoService;

    @Autowired
    @Qualifier(value = "IotScreenHistoryNodeInfoService")
    private IotScreenHistoryNodeInfoService iotHistoryNodeInfoService;

    /**
     * 查询所有设备（大屏统计）
     * @param response
     * @param userKey
     * @param obj
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.NODE_INFO_ALL)
    public ModelAndView selectAll(HttpServletResponse response,
                                  @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey,
                                  @RequestBody IotNodeInfoBO obj) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
            obj.setUser_id(user.getId());
            resultMap = iotScreenService.selectAll(obj);
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
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.SCENE_INFO_PAGE)
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
     * 报警统计
     * @param response
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.ALARM_INFO_STATISTIC)
    public ModelAndView selectAlarmStatisticInfo(HttpServletResponse response,
                                                 @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                                 @RequestBody IotAlarmInfoBO obj ) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
            obj.setUser_id(user.getId());
            if(ObjectUtil.isEmpty(obj.getStart_time())){
                obj.setStart_time( DateUtils.format(DateUtils.simpleALL, DateUtils.getDate(5)));
            }
            resultMap = iotAlarmInfoService.selectStatisticInfo(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(response, resultMap);
    }
    /**
     * 设备统计
     * @param response
     * @param userKey
     * @param obj
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.NODE_STATISTIC)
    public ModelAndView selectStatisticNode(HttpServletResponse response,
                                            @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                            @RequestBody IotNodeInfoBO obj) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
            obj.setUser_id(user.getId());
            resultMap = iotNodeInfoService.selectStatisticNodeInfo(obj);
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
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.ALARM_INFO_PAGE)
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
     * 检索
     * @param response
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.NODE_INFO_PAGE)
    public ModelAndView selectPage(HttpServletResponse response,
                                   @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                   @RequestBody IotNodeInfoBO obj,
                                   @RequestParam(required=false) Integer pageSize ,
                                   @RequestParam Integer paged ) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
            obj.setUser_id(user.getId());
            resultMap = iotNodeInfoService.selectPageList(obj,new ResultMapUtils().getPageBean(paged,pageSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(response, resultMap);
    }
    /**
     * 检索所有设备单位
     * @param response
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.SELECT_NODE_UNIT_LISTS)
    public ModelAndView selectNodeUnitList(HttpServletResponse response,
                                           @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                           @RequestBody IotNodeUnitDataBO obj) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoByUserKey(userKey);
            obj.setUser_id(user.getId());
            List<IotNodeUnitDataBO> iotUnitList = ProCacheUtil.getCache(CacheName.IOT_NODE_UNIT,"unitList");
            ArrayList<IotNodeUnitDataBO> list = new ArrayList<>();
            //如果缓存有数据就取缓存
            if (ObjectUtil.isNotEmpty(iotUnitList)){
                for (IotNodeUnitDataBO o:iotUnitList) {
                    if (o.getScene_id().equals(obj.getScene_id()) && o.getDevice_code().equals(obj.getDevice_code())){
                        list.add(o);
                    }
                }
                ResultMapUtils.putData(resultMap, list);
            }
            else
                resultMap = iotNodeInfoService.selectNodeUnitList(obj, new PageBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(response, resultMap);
    }
    /**
     * 查找设备历史数据
     */
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.NODE_HISTORY_INFO_NODE)
    public ModelAndView selectHistoryNode(HttpServletResponse response,
                                          @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                          @RequestBody IotHistoryNodeDataBO obj ) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            resultMap = iotHistoryNodeInfoService.selectHistoryNodeData(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(response, resultMap);
    }
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.SENSOR_HISTORY_INFO_NODE_COUNT)
    public ModelAndView selectHistoryNodeCount(HttpServletResponse response,
                                               @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey ,
                                               @RequestBody IotHistoryNodeDataBO obj ) {
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            resultMap = iotHistoryNodeInfoService.selectList(obj);
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
    @RequestMapping(method = RequestMethod.POST, value = RequestURLIOT.IotScreen.ALARM_INFO_ALL)
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
