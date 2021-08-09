
function e_charts_init(id) {
    var chartDom = document.getElementById(id);
    var myChart = echarts.init(chartDom, 'dark');
    var option;
    option = {
        title: {
            text: '测试设备1号'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['单位1号', '单位2号', '单位3号', '单位4号', '单位5号']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: '单位1号',
                type: 'line',
                stack: '总量',
                data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
                name: '单位2号',
                type: 'line',
                stack: '总量',
                data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
                name: '单位3号',
                type: 'line',
                stack: '总量',
                data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
                name: '单位4号',
                type: 'line',
                stack: '总量',
                data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
                name: '单位5号',
                type: 'line',
                stack: '总量',
                data: [820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };
    myChart.setOption(option);
}
function iot_dev_history() {
    let str = "";
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3 style='display: inline;margin-right: 5%'>设备历史数据</h3>\n" +
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
        "<div class=\"styled-input\" style='display: inline;'>\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\" style='display: inline;'>\n" +
        "<select style='margin-left: 3%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"genderType\" placeholder=\"场景选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部场景</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "<select style='margin-left: 3%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"temp_dev\" placeholder=\"设备选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部设备</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 3%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "<span style='margin-left: 3%;'>-</span>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 3%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>" +
        "\t\t\t\t\t\t\t</div>\n" +
        "<div id='e_charts' style='width: 55%;height: 400px;float: right;margin-top: 1%;z-index: 999'>" +

        "</div>" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\" style='padding-top: 0;width: 43%;'>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"table-responsive\">\n" +
        "\t\t\t\t\t\t\t\t\t<table class=\"table table-striped no-margin\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>#</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>名称</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>数值 / 状态</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>单位</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>时间</th>\n" +
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
    for(var i=1;i<11;i++){
        $("#tbody").append("\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>"+i+"</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>温度</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>28 / 30</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>.C</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>2021-08-03 11:45:13</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</tr>\n");
    }
    e_charts_init("e_charts");
}

function iot_dev_analyze() {
    let str = "";
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3 style='display: inline;margin-right: 5%'>设备数据分析</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "<select style='margin-left: 3%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"genderType\" placeholder=\"场景选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部场景</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "      </div>"+
        "</div>\n" +
        "<div class=\"row gutter\" id='main'>\n" +

        "</div>");
    for(var i=0;i<10;i++){
        var id = "line_chart_"+i;
        $("#main").append(
            "         <div class=\"col-lg-6 col-md-12 col-sm-12 col-xs-12\">\n" +
            "\t\t\t\t\t\t<div class=\"panel\">\n" +
            "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
            "\t\t\t\t\t\t\t\t<h4>设备"+i+"号</h4>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
            "\t\t\t\t\t\t\t\t<div id='"+id+"' class=\"chart-height3\" style='z-index: 999;'></div>\n" +
            "\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t</div>");
        e_charts_init(id);
    }
}

function iot_dev_trigger() {
    let str = "";
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3 style='display: inline;margin-right: 5%'>历史触发数据</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel panel-green\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "<div class=\"styled-input\" style='display: inline;'>\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"form-group\" style='display: inline;'>\n" +
        "<select style='margin-left: 3%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"genderType\" placeholder=\"场景选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部场景</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试场景3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "<select style='margin-left: 3%;display: inline;width: 200px;height: 35px' class=\"form-control\" id=\"temp_dev\" placeholder=\"设备选择\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>全部设备</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备1</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备2</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<option>测试设备3</option>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</select>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 3%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "<span style='margin-left: 3%;'>-</span>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 3%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\" style='padding-top: 0'>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"table-responsive\">\n" +
        "\t\t\t\t\t\t\t\t\t<table class=\"table table-striped no-margin\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>#</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>触发器</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>触发单元</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>触发条件</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>触发阈值</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>处理类型</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>触发详情</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>时间</th>\n" +
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
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>温度报警器</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>temp</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>数值超过【X】分钟，低于【Y】值</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td style='color: red'>30</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>邮件通知</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>发送邮件给【土豆先生】:产生系统报警消息</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>2021/08/03 11:45:13</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</tr>\n");
    }
}