
/**
 * 首页
 */
$("#iot_index").click(function(){
    iot_index();
});
/**
 * 平台概览
 * iot_scene_info() 场景信息
 * iot_dev_address()设备位置
 * iot_data_count()平台统计
 */
$("#iot_scene_info").click(function () {
    iot_scene_info();
});
$("#iot_dev_address").click(function () {
    iot_dev_address();
});
/**
 * 设备监控
 */
$("#iot_dev_monitor").click(function () {
    iot_dev_monitor();
});

/**
 * 报警信息
 * iot_alarm_unread() 未读报警
 * iot_alarm_history()历史报警
 * iot_alarm_contact()报警联系人
 */
$("#iot_alarm_unread").click(function () {
    iot_alarm_unread();
});
$("#iot_alarm_history").click(function () {
    iot_alarm_history();
});
$("#iot_alarm_contact").click(function () {
    iot_alarm_contact();
});

/**
 * 历史数据
 * iot_dev_history() 设备历史数据
 * iot_dev_analyze()历史数据分析图
 * iot_dev_trigger()设备历史触发
 */
$("#iot_dev_history").click(function () {
    iot_dev_history();
});
$("#iot_dev_analyze").click(function () {
    iot_dev_analyze();
});
$("#iot_dev_trigger").click(function () {
    iot_dev_trigger();
});

/**
 * 用户管理
 * iot_user_info()个人信息
 * iot_son_users() 子账户管理
 */
$("#iot_user_info").click(function () {
    iot_user_info()
});
$("#iot_son_users").click(function () {
    iot_son_users();
});

/**
 * 场景管理
 * iot_scene_all() 全部场景
 * iot_scene_shed()农作物大棚
 * iot_scene_orchard()智慧果园
 */
$("#iot_scene_all").click(function () {
    iot_scene_all();
});
$("#iot_scene_live").click(function () {
    iot_scene_live();
});
$("#iot_scene_video").click(function () {
    iot_scene_video();
});

/**
 * 设备管理
 * iot_dev_info() 设备信息
 * iot_trigger_info()触发器信息
 */
$("#iot_dev_info").click(function () {
    iot_dev_info();
});
$("#iot_trigger_info").click(function () {
    iot_trigger_info();
});

/**
 * 系统管理
 * sys_user_list() 用户列表
 * sys_service_count()服务统计
 * sys_scene_list()项目列表
 * sys_dev_list()设备列表
 * sys_dev_store()设备仓库(未归属)
 * sys_dictionary_list()数据字典
 * sys_lpm_list()LPM管理
 * sys_weChat_menu()微信菜单
 * sys_param_setting()系统参数
 */
$("#sys_user_list").click(function () {
    sys_user_list();
});
$("#sys_service_count").click(function () {
    sys_service_count();
});
$("#sys_scene_list").click(function () {
    sys_scene_list();
});
$("#sys_dev_list").click(function () {
    sys_dev_list();
});
$("#sys_dev_store").click(function () {
    sys_dev_store();
});
$("#sys_dictionary_list").click(function () {
    sys_dictionary_list();
});
$("#sys_lpm_list").click(function () {
    sys_lpm_list();
});
$("#sys_weChat_menu").click(function () {
    sys_wechat_menu();
});
$("#sys_param_setting").click(function () {
    sys_param_setting();
});
/**
 * 系统通知与反馈
 * sys_notify() 通知与反馈
 */
$("#sys_notify").click(function () {
    sys_notify()
});