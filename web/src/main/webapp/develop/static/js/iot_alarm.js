
function iot_alarm_unread() {
    let str = "";
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3 style='display: inline;margin-right: 5%'>未读报警</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel panel-green\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "          <button class=\"btn btn-success\" type=\"button\">全部选择</button>\n" +
        "          <button class=\"btn btn-info\" type=\"button\">取消选择</button>\n" +
        "<div class=\"styled-input\" style='display: inline;'>\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\" style='display: inline;'>\n" +
        "<select style='margin-left: 2%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"genderType\" placeholder=\"场景选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部场景</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "<select style='margin-left: 2%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"temp_dev\" placeholder=\"设备选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部设备</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "<select style='margin-left: 2%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"temp_dev\" placeholder=\"等级选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部等级</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>严重</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>紧急</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>一般</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 2%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "<span style='margin-left: 1%;'>-</span>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 1%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>" +
        "          <button style='float:right' class=\"btn btn-warning\" type=\"button\">已读</button>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\" style='padding-top: 0'>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"table-responsive\">\n" +
        "\t\t\t\t\t\t\t\t\t<table class=\"table table-striped no-margin\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>#</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警名称</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警数值 / 阀值</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警详情</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警级别</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>处理标志</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>时间</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>操作</th>\n" +
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
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>温度正常报警器报警</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>28 / 30</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>温度正常报警器,传感器（研发室测试1/测试设备1/temp），当前为28 °C低于30 °C，超过60分钟，请及时处理。</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>一般</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td style='color: red'>未处理</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>2021/08/03 11:45:13</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>" +
            "<img src='/develop/static/img/unmarker.png' width='20px' height='20px'>" +
            "</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</tr>\n");
    }
}

function iot_alarm_history() {
    let str = "";
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3 style='display: inline;margin-right: 5%'>历史报警</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"设备号（SN） ...\">\n" +
        "         <i class=\"icon-search3\"></i>\n" +
        "      </div>"+
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel panel-green\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "          <button class=\"btn btn-success\" type=\"button\">全部选择</button>\n" +
        "          <button class=\"btn btn-info\" type=\"button\">取消选择</button>\n" +
        "<div class=\"styled-input\" style='display: inline;'>\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\" style='display: inline;'>\n" +
        "<select style='margin-left: 2%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"genderType\" placeholder=\"场景选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部场景</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "<select style='margin-left: 2%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"temp_dev\" placeholder=\"设备选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部设备</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "<select style='margin-left: 2%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"temp_dev\" placeholder=\"等级选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部等级</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>严重</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>紧急</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>一般</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 2%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "<span style='margin-left: 1%;'>-</span>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 1%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>" +
        "          <button style='float:right' class=\"btn btn-warning\" type=\"button\">已读</button>\n" +
        "          <button style='float:right' class=\"btn btn-danger\" type=\"button\">删除</button>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\" style='padding-top: 0'>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"table-responsive\">\n" +
        "\t\t\t\t\t\t\t\t\t<table class=\"table table-striped no-margin\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>#</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警名称</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警数值 / 阀值</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警详情</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>报警级别</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>处理标志</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>时间</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>操作</th>\n" +
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
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>温度正常报警器报警</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>28 / 30</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>温度正常报警器,传感器（研发室测试1/测试设备1/temp），当前为28 °C低于30 °C，超过60分钟，请及时处理。</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>一般</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td style='color: red'>未处理</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>2021/08/03 11:45:13</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>" +
            "<img src='/develop/static/img/unmarker.png' width='20px' height='20px'>" +
            "</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</tr>\n");
    }
}

function iot_alarm_contact() {
    let str = "";
    //加载动画
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3 style='width: 25%;display: inline;'>所有联系人</h3>\n" +
        "      <div class=\"custom-search \" style='float: none;display: inline; margin-left:5%'>\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"账户名(手机号) ...\">\n" +
        "         <i class=\"icon-search3\" style='top: 2%'></i>\n" +
        "      </div>"+
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "   <a class=\"btn btn-info\" style='float: right;border-radius: 5px'><i class=\"icon-user-check\"></i>添加联系人</a>\n" +
        "</div>\n" +
        "<div id='main' class=\"row gutter\">\n" +

        "</div>\n" +
        "</div>");
    for (let i = 0; i < 9; i++) {
        str += "<div class=\"col-md-4 col-sm-6 col-xs-12\" style='width: 25%;'>\n" +
            "    <div class=\"panel users-wrapper blue\">\n" +
            "     <div class=\"users-info clearfix\">\n" +
            "      <div class=\"users-avatar\">\n" +
            "       <img src=\"img/thumbs/user4.png\" class=\"img-responsive\" alt=\"Arise Admin\">\n" +
            "      </div>\n" +
            "      <span style='position: absolute;top: 50%;left: 5%;color: #A9BD7A'>正常状态</span>"+
            "      <div class=\"users-detail\">\n" +
            "       <h5>admin</h5>\n" +
            "       <p>超级管理员</p>\n" +
            "      </div>\n" +
            "      <div style='margin-top: 10px;margin-left: 60px'>" +
            "        <span title='编辑' class='icon-edit operate' style='padding-left: 12px;color: #A9BD7A'></span>"+
            "        <span title='删除' class='icon-trashcan operate' style='color: #FFBEC2'></span>"+
            "        <span title='增加报警量' class='icon-plus operate' style='color: #6FB4CE'></span>"+
            "      </div>"+
            "     </div>\n" +
            "      <ul class=\"users-footer clearfix\">\n" +
            "       <li>\n" +
            "         <p class=\"light\">手机号</p>\n" +
            "         <p>3891236678</p>\n" +
            "       </li>\n" +
            "       <li style='width: 50%;'>\n" +
            "         <p class=\"light\">注册时间</p>\n" +
            "         <p>2021-05-06 21:30:07</p>\n" +
            "       </li>\n" +
            "      </ul>\n" +
            "    </div>\n" +
            "   </div>\n";
    }
    $("#main").append(str);
}