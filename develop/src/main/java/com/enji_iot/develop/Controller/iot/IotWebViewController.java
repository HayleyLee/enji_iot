package com.enji_iot.develop.Controller.iot;

import com.enji_iot.develop.Service.IotSceneInfoService;
import com.enji_iot.develop.Service.IotVideoInfoService;
import com.enji_iot.develop.Util.UserUtil;
import com.enji_iot.util.Entity.bo.IotSceneInfoBO;
import com.enji_iot.util.Entity.bo.IotVideoInfoBO;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Common.Code;
import com.enji_iot.develop.Service.IotOtherInfoService;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
public class IotWebViewController {
    @Autowired
    @Qualifier(value = "IotSceneInfoService")
    private IotSceneInfoService iotSceneInfoService;
    @Autowired
    @Qualifier(value = "IotVideoInfoService")
    private IotVideoInfoService iotVideoInfoService;
    @Autowired
    @Qualifier(value = "IotOtherInfoService")
    private IotOtherInfoService IotOtherInfoService;

    private final static String VIEW_PATH2 = "/oss/piot/";
    private final static String VIEW_PATH = "/oss/iot/";
    private final static String VIEW_PATH3 = "/sample/";

    @RequestMapping(method = RequestMethod.GET, value = "/piot/{viewName}")
    public String ossPage2(HttpServletRequest req, HttpServletResponse resp,
                           @PathVariable String viewName) {
        setModelWeb(req, viewName);
        return VIEW_PATH2 + viewName;
    }

    private void setModelWeb(HttpServletRequest req, String viewName) {
        if (viewName.contains("index")) {
            // ?????????????????????????????????
            UserInfoBO userInfo = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            if (ObjectUtil.isNotEmpty(userInfo)) {
                if (userInfo.getType() != Code.UserType.SUPER + 0) {
                    obj.setUser_id(userInfo.getId());
                }
                req.setAttribute("sceneInfo", ResultMapUtils.getData(iotSceneInfoService.selects(obj)));
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/iot/{viewName}")
    public String ossPage(HttpServletRequest req, HttpServletResponse resp,
                          @PathVariable String viewName) {
        return VIEW_PATH + viewName;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sample/{viewName}")
    public String samplePage(HttpServletRequest req, HttpServletResponse resp,
                             @PathVariable String viewName) {
        return VIEW_PATH3 + viewName;
    }

    /**
     * ??????????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/app_status")
    public ModelAndView appStatusPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            // ??????????????????????????????????????????
            resultData = IotOtherInfoService.getAppStatusInfo(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "app_status", "info");
    }

    /**
     * ????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/monitor")
    public ModelAndView monitorPage(HttpServletRequest req, HttpServletResponse resp,
                                    @RequestParam(required = false) Integer id) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            if (ObjectUtil.isNotEmpty(id)) {
                req.setAttribute("id", id);
            }
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "monitor", "info");
    }

    /**
     * ??????????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/mvideo")
    public ModelAndView monitorVideoPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "mvideo", "info");
    }

    /**
     * ????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/alarm_manager")
    public ModelAndView alarmManagerPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "alarm_manager", "info");
    }

    /**
     * ????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/alarm_all")
    public ModelAndView alarmAllPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "alarm_all", "info");
    }

    /**
     * ????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/history_sensor_data")
    public ModelAndView historySensorDataPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "history_sensor_data", "info");
    }

    /**
     * ??????????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/history_sensor_chart")
    public ModelAndView historySensorChartPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "history_sensor_chart", "info");
    }

    /**
     * ????????????????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/history_trigger_data")
    public ModelAndView historyTriggerDataPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "history_trigger_data", "info");
    }

    /**
     * ????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/gateway_manager")
    public ModelAndView gatewayManagerPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "gateway_manager", "info");
    }

    /**
     * ????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/device_list")
    public ModelAndView deviceListPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "device_list", "info");
    }

    /**
     * ???????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/sensor_manager")
    public ModelAndView sensorManagerPage(HttpServletRequest req, HttpServletResponse resp,
                                          @RequestParam(required = false) Integer id) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            if (ObjectUtil.isNotEmpty(id)) {
                req.setAttribute("id", id);
            }
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "sensor_manager", "info");
    }

    /**
     * ???????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/trigger_manager")
    public ModelAndView triggerManagerPage(HttpServletRequest req, HttpServletResponse resp,
                                           @RequestParam(required = false) Integer id) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            if (ObjectUtil.isNotEmpty(id)) {
                req.setAttribute("id", id);
            }
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "trigger_manager", "info");
    }

    /**
     * ??????????????????
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/video_manager")
    public ModelAndView videoManagerPage(HttpServletRequest req, HttpServletResponse resp,
                                         @RequestParam(required = false) Integer id) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            if (ObjectUtil.isNotEmpty(id)) {
                req.setAttribute("id", id);
            }
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotSceneInfoBO obj = new IotSceneInfoBO();
            obj.setUser_id(user.getId());
            resultData = iotSceneInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "video_manager", "info");
    }

    /**
     * ??????????????????
     *
     * @param req
     * @param resp
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/video")
    public ModelAndView videoDetailPage(HttpServletRequest req, HttpServletResponse resp,
                                        @RequestParam Integer sid, @RequestParam Integer id) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotVideoInfoBO obj = new IotVideoInfoBO();
            obj.setUser_id(user.getId());
            obj.setScene_id(sid);
            resultData = iotVideoInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
            req.setAttribute("videoId", id);
            req.setAttribute("sceneId", sid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "video_live", "info");
    }

    /**
     * ??????????????????
     *
     * @param req
     * @param resp
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/iot/video/back")
    public ModelAndView videoPlayBackPage(HttpServletRequest req, HttpServletResponse resp,
                                          @RequestParam Integer sid, @RequestParam Integer id) {
        Map<String, Object> resultData = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = UserUtil.getUserInfoBySession(req);
            IotVideoInfoBO obj = new IotVideoInfoBO();
            obj.setUser_id(user.getId());
            obj.setScene_id(sid);
            resultData = iotVideoInfoService.selectPageList(obj, new ResultMapUtils().getPageBean(1, 200));
            req.setAttribute("videoId", id);
            req.setAttribute("sceneId", sid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMapUtils().getModelAndView(resp, resultData, VIEW_PATH + "video_playback", "info");
    }

}
