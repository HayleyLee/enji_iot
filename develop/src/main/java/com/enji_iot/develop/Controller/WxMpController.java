package com.enji_iot.develop.Controller;

import com.enji_iot.develop.Service.UserService;
import com.enji_iot.util.Common.Code;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Config.ProConfig;
import com.enji_iot.develop.Service.base.WeChatFileInfoService;
import com.enji_iot.util.Util.LogUtil;
import com.enji_iot.util.Util.ObjectUtil;
import com.enji_iot.util.Util.ResultMapUtils;
import com.enji_iot.util.Util.SpringApplicationContext;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxMenu;
import me.chanjar.weixin.common.bean.WxMenu.WxMenuButton;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.StringUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpCustomMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class WxMpController {

//	@Resource(name = "wxMpService")
//	@Autowired
//	private WxMpServiceImpl wxMpService;

    //	@Resource(name = "wxMpMessageRouter")
//    @Autowired
//    private WxMpMessageRouter wxMpMessageRouter;

//    @Autowired
//    private WxMpInMemoryConfigStorage wxMpConfigStorage;

    @Autowired
    @Qualifier(value = "UserService")
    private UserService userService;

	@Autowired
	@Qualifier(value = "WeChatFileInfoService")
	private WeChatFileInfoService fileInfoService;


    /**
     * 微信消息 和事件推送
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param req
     * @param response
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "weixin/mp/msg")
    public void verifyMsg(@RequestParam(value = "signature", required = false) String signature,
                          @RequestParam(value = "timestamp", required = false) String timestamp,
                          @RequestParam(value = "nonce", required = false) String nonce,
                          @RequestParam(value = "echostr", required = false) String echostr, HttpServletRequest req,
                          HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write(echostr);
        out.flush();
    }

    /**
     * 微信消息 和事件推送
     *
     * @param req
     * @param response
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "weixin/mp/msg")
    public void rcvMsg(HttpServletRequest req, HttpServletResponse response) throws Exception {
        WxMpXmlMessage msg = WxMpXmlMessage.fromXml(req.getInputStream());
        // 事件
        if (WxConsts.XML_MSG_EVENT.equals(msg.getMsgType())) {
            // subscribe(订阅) 、 unsubscribe(取消订阅)
            if ("subscribe".equals(msg.getEvent())) {
                // 给用户发送含有绑定url的消息通知
                String user_open_id = msg.getFromUserName();

                WxMpCustomMessage ms = new WxMpCustomMessage();
                ms.setToUser(user_open_id);
                ms.setMsgType("text");
                ms.setContent("欢迎关注" + ProConfig.PROJECT_NAME + "公众号服务平台！");
                WxMpServiceImpl wxMpService = (WxMpServiceImpl) SpringApplicationContext.getBean("WxMpServiceImpl");
                wxMpService.customMessageSend(ms);

                // 发送图文消息

            } else if ("unsubscribe".equals(msg.getEvent())) {
                // 取消订阅
            }
        } else if (WxConsts.XML_MSG_TEXT.equals(msg.getMsgType())) {
            // 文字消息

        } else if (WxConsts.XML_MSG_IMAGE.equals(msg.getMsgType())) {
            // 图片消息

        }
    }

    /**
     * 创建微信菜单
     *
     * @param req
     * @param response
     * @throws IOException
     * @throws WxErrorException
     */
    @RequestMapping(method = RequestMethod.GET, value = "weixin/mp/menu/create")
    public void menuCreate(HttpServletRequest req, HttpServletResponse response) throws IOException, WxErrorException {
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        createMenu(out);
        out.write("menu create OK!");
        out.flush();
    }

    /**
     * 微信网页授权回调接口
     *
     * @param req
     * @param response
     * @param state
     * @param code
     * @throws IOException
     * @throws WxErrorException
     */
    @RequestMapping(method = RequestMethod.GET, value = "oauth2/authorize/weixin/mp")
    public String authCallback(HttpServletRequest req, HttpServletResponse response,
                               @RequestParam(required = false) String state, @RequestParam(required = false) String code)
            throws IOException, WxErrorException {

        if (!StringUtils.isEmpty(code)) {

            // 通过code换取网页授权access_token
            WxMpServiceImpl wxMpService = (WxMpServiceImpl) SpringApplicationContext.getBean("WxMpServiceImpl");
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);

            // ---
            String openid = wxMpOAuth2AccessToken.getOpenId();

            UserInfoBO user = ProCacheUtil.getCache(CacheName.USERINFO_OPENID, openid);
            if (ObjectUtil.isEmpty(user) || ObjectUtil.isEmpty(user.getNick_name())
                    || ObjectUtil.isEmpty(user.getWx_img_url())) {
                // 获取用户更多的信息，snap_userinfo 授权
                WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, "zh_CN");

                if (ObjectUtil.isEmpty(user)) {
                    req.getSession().setAttribute("open_id", openid);
                    // 联系人信息绑定
                    if (state.contains("cbind")) {
                        UserInfoBO userx = new UserInfoBO();
                        userx.setWx_open_id(openid);
                        userx.setWx_img_url(wxMpUser.getHeadImgUrl());
                        req.getSession().setAttribute("user", userx);
                        // 这个session 时间设置的短一点
                        req.getSession().setMaxInactiveInterval(30);
                        return "redirect:" + state.replace("*", "&");
                    }
                    // 跳转到绑定页面
                    req.getSession().setAttribute("open_id", openid);
                    return "redirect:/service/wiot/bind";
                } else {
                    user.setNick_name(wxMpUser.getNickname());
                    user.setWx_img_url(wxMpUser.getHeadImgUrl());
//					Map<String,Object> resultMap = service.update("UserInfo.update", user);
                    Map<String, Object> resultMap = userService.update(user);
                    if (ResultMapUtils.isOk(resultMap)) {
                        ProCacheUtil.addCache(CacheName.USERINFO_OPENID, openid, user);
                        ProCacheUtil.addCache(CacheName.USERINFO, user.getUser_key(), user);
                    }
                }
            }

            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("weixin_oauth_callback_date", System.currentTimeMillis());
        }
        return "redirect:" + state.replace("*", "&");
    }

    /**
     * 创建菜单
     *
     * @throws WxErrorException
     * @throws UnsupportedEncodingException test:
     *                                      http://chen.sub.testlg.com/lpro/service/weixin/mp/menu/create
     */

    public String createMenu(PrintWriter out) throws WxErrorException, UnsupportedEncodingException {
        String ret_msg = null;
        // 初始化菜单
        WxMenu wxMenu = new WxMenu();
        // 微信一级菜单列表
        List<WxMenuButton> menuList = new ArrayList<WxMenuButton>();

        String url = ProConfig.LOCAL_DOMAIN;

        generateWeixinMenu(menuList, Arrays.asList("实时监控", url + "/service/wiot/scene"), null);

        generateWeixinMenu(menuList, Arrays.asList("报警管理", url + "/service/wiot/alarm"), null);

        generateWeixinMenu(menuList, Arrays.asList("个人信息"),
                Arrays.asList(
                        Arrays.asList("项目信息", url + "/service/wiot/mscene"),
                        Arrays.asList("设备配网", url + "/service/wiot/deviceConfigwifi")
                ));

//		generateWeixinMenu(menuList, Arrays.asList("查询服务") , 
//				Arrays.asList( 
//							Arrays.asList("待付款", url+"/service/wx/order?seq=93"), 
//								Arrays.asList("所有订单",url+ "/service/wx/order")  
//							));
//		generateWeixinMenu(menuList, Arrays.asList("个人中心", url+ "/service/wx/personal"), null);

        wxMenu.setButtons(menuList);
        WxMpServiceImpl wxMpService = (WxMpServiceImpl) SpringApplicationContext.getBean("WxMpServiceImpl");
        wxMpService.menuCreate(wxMenu);
        return ret_msg;
    }

    /**
     * 工具类
     *
     * @param menuList
     * @param parent
     * @param sub
     */
    private void generateWeixinMenu(List<WxMenuButton> menuList, List<String> parent, List<List<String>> sub) {
        WxMenuButton parentMenu = new WxMenuButton();
        if (parent.size() > 1) {
            parentMenu.setName(parent.get(0));
            parentMenu.setType("view");
            parentMenu.setUrl(parent.get(1));
        } else {
            parentMenu.setName(parent.get(0));
            parentMenu.setType("view");

            List<WxMenuButton> secordBtns = new ArrayList<WxMenuButton>();

            for (int i = 0; i < sub.size(); i++) {
                WxMenuButton askBtn = new WxMenuButton();
                askBtn.setName(sub.get(i).get(0));
                askBtn.setType("view");
                askBtn.setUrl(sub.get(i).get(1));
                secordBtns.add(askBtn);
            }
            parentMenu.setSubButtons(secordBtns);
        }
        menuList.add(parentMenu);
    }

    /**
     * 将提交到微信文件下载并提交到服务器
     *
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/wx/media/{media_id}")
    public ModelAndView getWxMediaInfo(HttpServletRequest req, HttpServletResponse resp,
                                       @RequestHeader(value = ResultMapUtils.USER_KEY, required = true) String userKey, @PathVariable String media_id) {
        // media_id为附件上传到微信平台的id
        Map<String, Object> resultMap = ResultMapUtils.getResultMap();
        try {
            UserInfoBO user = ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO());
            if (ObjectUtil.isNotEmpty(user)) {
                WxMpInMemoryConfigStorage wxMpConfigStorage = (WxMpInMemoryConfigStorage)SpringApplicationContext.getBean("WxMpInMemoryConfigStorage");
                resultMap = fileInfoService.downFileImgFromWxServer(wxMpConfigStorage.getAccessToken(), media_id, user.getId());
            } else {
                ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.NO_AUTHORIZATION);
            }
        } catch (Exception e) {
            ResultMapUtils.putStatusCode(resultMap, Code.ResponseCode.SystemCode.ERROR);
            LogUtil.errorLog(e.getMessage());
        }
        return new ResultMapUtils().getModelAndView(resp, resultMap);
    }


    /**
     * 发送临时图片消息
     *
     * @param open_id
     * @param content
     */
    public void sendWxMpCustomTempImageMessage(String wx_open_id, String imagePath, String title) {
        InputStream inputStream = null;
        try {
            File imageFile = new File(imagePath);
            inputStream = new FileInputStream(imageFile);
            byte b[] = new byte[(int) imageFile.length()]; // 创建合适文件大小的数组
            inputStream.read(b); // 读取文件中的内容到b[]数组

            // 上传临时图片
            WxMpServiceImpl wxMpService = (WxMpServiceImpl) SpringApplicationContext.getBean("WxMpServiceImpl");
            WxMediaUploadResult wxMediaUploadResult = wxMpService.mediaUpload(WxConsts.CUSTOM_MSG_IMAGE, "jpg",
                    inputStream);

            // 发送图片消息
            WxMpCustomMessage wm = new WxMpCustomMessage();
            wm.setToUser(wx_open_id);
            wm.setMsgType(WxConsts.CUSTOM_MSG_IMAGE);
            // wm.setToUser("from " + wx_open_id);
            wm.setMediaId(wxMediaUploadResult.getMediaId());

            wxMpService.customMessageSend(wm);

        } catch (Exception e) {
            LogUtil.errorLog(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
