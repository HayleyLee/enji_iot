package com.enji_iot.util.Util;

/**
 * @author Tsinghua Lee
 * 请求的返回值，及常见异常，在使用时如无对应选项，请自行添加
 */
public class ResponseType {
    /**
     * this's about success msg
     */
    public final static String LOGIN_SUCCESS_ID = "S10000";
    public final static String LOGIN_SUCCESS_MESSAGES = "登陆成功!";
    public final static String SUCCESS_ID = "S10001";
    public final static String SUCCESS_MESSAGES = "返回成功!";
    public final static String DEL_SUCCESS_ID = "S10002";
    public final static String DEL_SUCCESS_MESSAGES = "删除成功!";
    public final static String INSERT_SUCCESS_ID = "S10003";
    public final static String INSERT_SUCCESS_MESSAGES = "新增成功!";
    public final static String UPDATE_SUCCESS_ID = "S10004";
    public final static String UPDATE_SUCCESS_MESSAGES = "修改成功!";
    /**
     * this's about error msg
     */
    public final static String ERROR_ID = "E20000";
    public final static String ERROR_MESSAGES = "返回失败!未知错误";
    public final static String NULL_POINT_ERROR_ID = "E20001";
    public final static String NULL_POINT_ERROR_MESSAGES = "参数为空！";
}
