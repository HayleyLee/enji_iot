function del_user() {
    layer.confirm('删除后将不可恢复！是否确认删除？', {
        btn: ['删除','取消'] //按钮
    }, function(){
        //todo - delete req ajax
        layer.msg('删除成功', {icon: 1});
    });
}
function iot_son_users() {
    //main
    $(".center").empty().append("\n" +
        "\t\t\t<div class=\"container-fluid\">\n" +
        "\t\t\t\t<div class=\"top-bar clearfix\">\n" +
        "\t\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t\t<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n" +
        "\t\t\t\t\t\t\t<div class=\"page-title\">\n" +
        "\t\t\t\t\t\t\t\t<h3 style='width: 25%;display: inline;'>子账户管理</h3>\n" +
        "      <div class=\"custom-search \" style='float: none;display: inline; margin-left:5%'>\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"账户名(手机号) ...\">\n" +
        "         <i class=\"icon-search3\" style='top: 2%'></i>\n" +
        "      </div>"+
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<a class=\"btn btn-info\" style='float: right;border-radius: 5px'><i class=\"icon-user-check\"></i>新增账户</a>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"row gutter\" id=\"main\">" +

        "</div>\n" +
        "\t\t\t</div>");
    for(var i=0;i<10;i++){
        $("#main").append("<div class=\"col-md-4 col-sm-4 col-xs-12\" style='width: 20%'>\n" +
            "\t\t\t\t\t\t<div class=\"task-wrapper\" id=\"completed\" style='border-radius: 5px'>\n" +
            "<img width='5%' height='5%' src='/develop/static/img/del.png' style='float: right' onclick='del_user()'>" +
            "<img width='5%' height='5%' src='/develop/static/img/update.png' style='float: right;margin-right: 5%'>" +
            "\t\t\t\t\t\t\t<h4 class=\"task-name text-blue\">刘淇昌</h4>\n" +
            "<div id='scene_more' style='margin-bottom: 3%'>" +
            "<img width='50%' height='50%' src='/develop/static/img/test.jpeg' style='margin-bottom: 3%;border-radius: 50%'>\n" +
            "<div>" +
            "<span>简介 / 备注...简介 / 备注...简介 / 备注...简介 / 备注...简介 / 备注...简介 / 备注...</span>" +
            "</div>" +
            "<div style='margin-top: 5%'>" +
            "   <label style='font-weight: bold;color: #BF7A6A'>权限</label>" +
            "   <span style='float: right;color: #A9BD7A'>普通账户</span>" +
            "</div>" +
            "<div>" +
            "<label style='font-weight: bold;color: #BF7A6A'>注册日期</label>" +
            "   <span style='float: right;color: #A9BD7A'>2021-08-03</span>" +
            "</div>" +
            "</div>\n");
    }
}

function iot_user_info() {
    $(".center").empty().append("\n" +
        "\t\t\t<div class=\"container-fluid\">\n" +
        "\t\t\t\t<div class=\"top-bar clearfix\">\n" +
        "\t\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t\t<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n" +
        "\t\t\t\t\t\t\t<div class=\"page-title\">\n" +
        "\t\t\t\t\t\t\t\t<h3 style='width: 25%;display: inline;'>个人信息</h3>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<a class=\"btn btn-info\" style='float: right;border-radius: 5px'><i class=\"icon-user-check\"></i>修改信息</a>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"row gutter\" id=\"main\">" +
        "\t\t\t\t\t<div class=\"col-lg-5 col-md-5 col-sm-9 col-xs-12\" style='width: 25%'>\n" +
        "\t\t\t\t\t\t<div class=\"panel height2 random-bg-five\" style='height: auto;border-radius: 5px'>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"user-profile clearfix\">\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"user-img\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<img src=\"/develop/static/img/test.jpeg\" alt=\"User Info\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<span class=\"completed-info\">80%</span>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<h3 style='margin-top: 8%'>李清华</h3>\n" +
        "\t\t\t\t\t\t\t\t\t<h4>超级管理员</h4>\n" +
        "\t\t\t\t\t\t\t\t\t<h5>上次登陆: " +
        "\t\t\t\t\t\t\t\t\t\t<span style='margin-left: 5%'>中国</span> -" +
        "\t\t\t\t\t\t\t\t\t\t<span>广西</span> -" +
        "\t\t\t\t\t\t\t\t\t\t<span>梧州</span>" +
        "\t\t\t\t\t\t\t\t\t</h5>\n" +
        "\t\t\t\t\t\t\t\t<div style='border-top: 1px solid white;width: 80%;margin-left: 10%;padding: 2.5% 5%'>" +
        "\t\t\t\t\t\t\t\t\t<div><h5 style='text-align: left'>我的活跃(天):<span style='font-size: 25px;font-weight: bold;float: right'>784</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t\t<div style='margin-top: 1%;'><h5 style='text-align: left'>我的场景:<span style='font-size: 25px;font-weight: bold;float: right'>7</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t\t<div style='margin-top: 1%;'><h5 style='text-align: left'>我的设备:<span style='font-size: 25px;font-weight: bold;float: right'>74</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div style='border-top: 1px solid white;width: 80%;margin-left: 10%;padding: 2.5% 5%'>" +
        "\t\t\t\t\t\t\t\t\t<div><h5 style='text-align: left'>我的短信:<span style='font-size: 25px;font-weight: bold;float: right'>11</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t\t<div style='margin-top: 1%;'><h5 style='text-align: left'>我的语音:<span style='font-size: 25px;font-weight: bold;float: right'>3</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t\t<div style='margin-top: 1%;'><h5 style='text-align: left'>我的私信:<span style='font-size: 25px;font-weight: bold;float: right'>29</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div style='border-top: 1px solid white;width: 80%;margin-left: 10%;padding: 2.5% 0'>" +
        "\t\t\t\t\t\t\t\t\t<div><img width='8%' style='display: inline;float: left;margin-right:2%' src='/develop/static/img/mail.png'><h5 style='text-align: left'>我的邮箱:<span style='font-weight: bold;float: right;font-size: 10px'>abc777666321@gmail.com</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t\t<div style='margin-top: 1%;'><img width='8%' style='display: inline;float: left;margin-right:2%' src='/develop/static/img/phone.png'><h5 style='text-align: left'>我的手机:<span style='font-size: 15px;font-weight: bold;float: right'>11007741234</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t\t<div style='margin-top: 1%;'><img width='8%' style='display: inline;float: left;margin-right:2%' src='/develop/static/img/id_card.png'><h5 style='text-align: left'>实名认证:<span style='font-size: 15px;font-weight: bold;float: right'>未认证</span></h5></div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\" style='width: 50%;float: right'>\n" +
        "\t\t\t\t\t\t<div class=\"timeline animated\" id='time_line'>\n" +

        "\t\t\t\t\t\t</div>\n" +
        "<div style='width: 11%;margin-left: 44%'>" +
        "   <a href=\"javascript:void(0)\" onclick='show_all_history()' class=\"btn btn-info btn-transparent btn-rounded\">查看全部</a>" +
        "</div>" +
        "\t\t\t\t\t</div>" +
        "\t\t</div>\n" +
        "</div>");

    for(var i=0;i<3;i++){
        $("#time_line").append("\t\t\t\t\t\t\t<div class=\"timeline-row\">\n" +
            "\t\t\t\t\t\t\t\t<div class=\"timeline-time\">\n" +
            "\t\t\t\t\t\t\t\t\t7:51下午<small>2021-08-02</small>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"timeline-icon\">\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"red-bg\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-pencil2\"></i>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"timeline-content\">\n" +
            "\t\t\t\t\t\t\t\t\t<h4>登入 / 登出</h4>\n" +
            "\t\t\t\t\t\t\t\t\t<p>您于【2021-08-02 上午7:51】登入了平台。如非本人操作，请及时检查账户安全问题！</p>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"timeline-row\">\n" +
            "\t\t\t\t\t\t\t\t<div class=\"timeline-time\">\n" +
            "\t\t\t\t\t\t\t\t\t7:51上午<small>2021-08-02</small>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"timeline-icon\">\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"green-bg\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-folder-images\"></i>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"timeline-content\">\n" +
            "<img width='15%' style='float: right;' src='/develop/static/img/devOnLine.png'>" +
            "\t\t\t\t\t\t\t\t\t<h4>设备 / 修改</h4>\n" +
            "\t\t\t\t\t\t\t\t\t<p>您于【2021-08-02 上午7:51】执行了【设备 - xxx号】-【修改】操作。测试功能，以后可能会记录修改的具体选项供用户回溯</p>\n" +
            "\t\t\t\t\t\t\t\t\t<div class=\"\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"btn btn-default btn-xs\">查看此设备</a>\n" +
            "\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t</div>\n");
    }
    show_history();
}
function show_history() {
    var timelineAnimate;
    timelineAnimate = function(elem) {
        return $(".timeline.animated .timeline-row").each(function(i) {
            var bottom_of_object, bottom_of_window;
            bottom_of_object = $(this).position().top + $(this).outerHeight();
            bottom_of_window = $(window).scrollTop() + $(window).height();
            if (bottom_of_window > bottom_of_object) {
                return $(this).addClass("active");
            }
        });
    };
    timelineAnimate();
    return $(window).scroll(function() {
        return timelineAnimate();
    });
}

function show_all_history() {
    let str = "";
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3 style='display: inline;margin-right: 5%'>行为记录</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel panel-green\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "<div class=\"styled-input\" style='display: inline;'>\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\" style='display: inline;'>\n" +
        "<select style='margin-left: 2%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"temp_dev\" placeholder=\"等级选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部账户</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>子账户1号</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>子账户2号</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>子账户3号</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 2%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "<span style='margin-left: 1%;'>-</span>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 1%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\" style='padding-top: 0'>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"table-responsive\">\n" +
        "\t\t\t\t\t\t\t\t\t<table class=\"table table-striped no-margin\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th style='color: #A9BD7A'>#</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th style='color: #A9BD7A'>账户名称</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th style='color: #A9BD7A'>【行为/操作 - 对象】</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th style='color: #A9BD7A'>行为/操作描述</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th style='color: #A9BD7A'>时间</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t</thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t<tbody id='tbody'>\n" +

        "\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
        "\t\t\t\t\t\t\t\t\t</table>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "     </div>"+
        "</div>\n" +
        "</div>");
    for(var i=1;i<16;i++){
        $("#tbody").append("\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>"+i+"</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>子账户3号</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>【修改】行为，【设备】- 【xxx号】</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>账户【子账户3号】于【2021/08/03 11:45】执行【修改】操作，操作对象：【设备】- 【xxx号】</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>2021/08/03 11:45:13</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</tr>\n");
    }
}