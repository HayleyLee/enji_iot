package com.enji_iot.data.Util;

import com.enji_iot.cache.Cache.CacheName;
import com.enji_iot.cache.Cache.ProCacheUtil;
import com.enji_iot.util.Entity.bo.UserInfoBO;
import com.enji_iot.util.Util.ExcelUtil;
import com.enji_iot.util.Util.ObjectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    /**
     * 判断是否为用户角色
     */
    public static Boolean verifyUserRole(String userKey, Integer userTypeCode){
        UserInfoBO user = ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO());
        if(ObjectUtil.isEmpty(user)){
            return false ;
        }else{
            if(ObjectUtil.isEmpty(userTypeCode)){
                return true ;
            }
            if(userTypeCode+0 == user.getType()){
                return true ;
            }else{
                return false ;
            }
        }
    }
    /**
     * 根据session获取用户信息
     * @param req
     * @return
     */
    public static UserInfoBO getUserInfoBySession(HttpServletRequest req) {
        return (UserInfoBO) req.getSession().getAttribute("user");
    }
    /**
     * 根据userKey获取用户信息
     */
    public static UserInfoBO getUserInfoByUserKey(String userKey){
        return ProCacheUtil.getCache(CacheName.USERINFO, userKey, new UserInfoBO());
    }
    /**
     * Excel 下载
     */
    public static void downExcel(String name, String template, List<?> list, HttpServletResponse response) {
        if(ObjectUtil.isEmpty(list) ||  list.size() > 5000 ){
            list = new ArrayList<>();
        }
        ExcelUtil.exportExcel(name, template, list, response);
    }
}
