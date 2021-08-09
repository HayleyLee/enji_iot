
function iot_scene_info() {
    //main
    $(".center").empty().append("\n" +
        "\t\t\t<div class=\"container-fluid\">\n" +
        "\t\t\t\t<div class=\"top-bar clearfix\">\n" +
        "\t\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t\t<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n" +
        "\t\t\t\t\t\t\t<div class=\"page-title\">\n" +
        "\t\t\t\t\t\t\t\t<h3>场景信息</h3>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"row gutter\" id=\"main\"></div>\n" +
        "\t\t\t</div>");
    for(var i=0;i<10;i++){
        $("#main").append("<div class=\"col-md-4 col-sm-4 col-xs-12\" style='width: 25%'>\n" +
            "\t\t\t\t\t\t<div class=\"task-wrapper\" id=\"completed\" style='border-radius: 5px'>\n" +
            "\t\t\t\t\t\t\t<h4 class=\"task-name text-green\">测试场景 - <span id=\"completedOdometer\" class=\"odometer\">0</span></h4>\n" +
            "<div id='scene_more' style='margin-bottom: 3%'>" +
            "<img width='100%' height='auto' src='/develop/img/timeline.jpg' style='margin-bottom: 3%'>\n" +
            "<span>测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述</span>" +
            "</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"task-block critical\">\n" +
            "\t\t\t\t\t\t\t\t<h5 class=\"task-id\">测试设备<i class=\"icon-controller-record\"></i></h5>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"assigned-user\">\n" +
            "\t\t\t\t\t\t\t\t\t<img src=\"img/thumbs/user7.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<p class=\"task-desc\">模拟风速测试</p>\n" +
            "\t\t\t\t\t\t\t\t<ul class=\"task-footer\">\n" +
            "\t\t\t\t\t\t\t\t\t<li>离线</li>\n" +
            "\t\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"task-type\">afhla2hfia4fheh6ugca</span>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"task-block new\">\n" +
            "\t\t\t\t\t\t\t\t<h5 class=\"task-id\">测试设备<i class=\"icon-controller-record\"></i></h5>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"assigned-user\">\n" +
            "\t\t\t\t\t\t\t\t\t<img src=\"img/thumbs/user2.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<p class=\"task-desc\">模拟压力测试</p>\n" +
            "\t\t\t\t\t\t\t\t<ul class=\"task-footer\">\n" +
            "\t\t\t\t\t\t\t\t\t<li>离线</li>\n" +
            "\t\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"task-type\">afhla2hfia4fheh6ugca</span>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"task-block bug\">\n" +
            "\t\t\t\t\t\t\t\t<h5 class=\"task-id\">测试设备<i class=\"icon-controller-record\"></i></h5>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"assigned-user\">\n" +
            "\t\t\t\t\t\t\t\t\t<img src=\"img/thumbs/user5.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<p class=\"task-desc\">模拟压力测试</p>\n" +
            "\t\t\t\t\t\t\t\t<ul class=\"task-footer\">\n" +
            "\t\t\t\t\t\t\t\t\t<li>45 分钟前</li>\n" +
            "\t\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"task-type\">afhla2hfia4fheh6ugca</span>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"task-block critical\">\n" +
            "\t\t\t\t\t\t\t\t<h5 class=\"task-id\">测试设备<i class=\"icon-controller-record\"></i></h5>\n" +
            "\t\t\t\t\t\t\t\t<div class=\"assigned-user\">\n" +
            "\t\t\t\t\t\t\t\t\t<img src=\"img/thumbs/user4.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
            "\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t<p class=\"task-desc\">模拟温度测试</p>\n" +
            "\t\t\t\t\t\t\t\t<ul class=\"task-footer\">\n" +
            "\t\t\t\t\t\t\t\t\t<li>2 小时前</li>\n" +
            "\t\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t\t<span class=\"task-type\">afhla2hfia4fheh6ugca</span>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t</div>\n");
    }
}
function iot_dev_address() {
    //main
    $(".center").empty().append("\n" +
        "\t\t\t<div class=\"container-fluid\">\n" +
        "\t\t\t\t<div class=\"top-bar clearfix\">\n" +
        "\t\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t\t<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n" +
        "\t\t\t\t\t\t\t<div class=\"page-title\">\n" +
        "\t\t\t\t\t\t\t\t<h3>设备位置</h3>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t<div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n" +
        "\t\t\t\t\t\t\t<ul class=\"right-stats\" id=\"mini-nav-right\">\n" +
        "\t\t\t\t\t\t\t\t<li>\n" +
        "\t\t\t\t\t\t\t<div class=\"styled-input-wrapper\">\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"input-icon\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-ticket3\"></i>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"styled-input\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<label for=\"creditCard\">场景选择</label>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"creditCard\" placeholder=\"Select Card\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>"+
        "\t\t\t\t\t\t\t\t</li>\n" +
        "\t\t\t\t\t\t\t</ul>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>\n" +
        "\t\t\t\t<div class=\"row gutter\" id=\"main\"></div>\n" +
        "\t\t\t</div>");
    $("#main").append("<div class=\"row gutter\">\n" +
        "\t\t\t\t\t<div class=\"col-lg-3 col-md-4 col-sm-6 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel height2\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t<h4>警报数据</h4>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"demography\">\n" +
        "\t\t\t\t\t\t\t<div class=\"styled-input-wrapper\" style='margin: 0 5% 8% 5%'>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"input-icon\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-ticket3\"></i>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"styled-input\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<label for=\"creditCard\">设备选择</label>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<select class=\"form-control\" id=\"creditCard\" placeholder=\"Select Card\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备4</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>"+
        "\t\t\t\t\t\t\t\t\t<h3 id=\"visits\">设备总报警量: 18789</h3>\n" +
        "\t\t\t\t\t\t\t\t\t<p>当前数据采集自:2021-08-02</p>\n" +
        "\t\t\t\t\t\t\t\t\t<h3 id=\"visits\">今日数据: 0</h3>\n" +
        "\t\t\t\t\t\t\t\t\t<p>当前数据采集自:2021-08-02 12:40:21</p>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div id=\"maleVsFemale\" class=\"chart-height2\"></div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-3 col-md-4 col-sm-6 col-xs-12\" style='width: 75%;height: 620px;float: right;background-color: white'>\n" +

        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-3 col-md-4 col-sm-6 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel height2\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t<h4>运行情况</h4>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"demography\">\n" +
        "\t\t\t\t\t\t\t\t\t<ul class=\"male-female\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<li>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<h4><i class=\"male icon-man\"></i>在线: 123</h4>\n" +
        "\t\t\t\t\t\t\t\t\t\t</li>\n" +
        "\t\t\t\t\t\t\t\t\t\t<li>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<h4><i class=\"female icon-woman\"></i>离线: 456</h4>\n" +
        "\t\t\t\t\t\t\t\t\t\t</li>\n" +
        "\t\t\t\t\t\t\t\t\t</ul>\n" +
        "\t\t\t\t\t\t\t\t\t<h3 id=\"visits\">设备总数据量: 118789</h3>\n" +
        "\t\t\t\t\t\t\t\t\t<p>当前数据采集自:2021-08-02</p>\n" +
        "\t\t\t\t\t\t\t\t\t<h3 id=\"visits\">今日数据: 1187</h3>\n" +
        "\t\t\t\t\t\t\t\t\t<p>当前数据采集自:2021-08-02 12:40:21</p>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div id=\"maleVsFemale\" class=\"chart-height2\"></div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>");
}