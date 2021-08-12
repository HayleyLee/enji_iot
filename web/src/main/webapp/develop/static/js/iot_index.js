var time = new Date();
let year = time.getFullYear();
let month = time.getMonth()+1;
let day = time.getDate();

function data_count() {
    //scene_count
    $.ajax({
       type:"post",
       url:"/scene/count",
       data:{"user_id":"1"},
       success:function (data) {
           if(data[0].responseId==="S10001"){
               var Data = data[1];
               bounty.default({ el:'#appointmentsOdometer',value:Data[0].toString() })
           }
           else layer.msg("获取场景统计数据失败！错误码："+data[0].responseMsg);
       }
    });
    //device_count
    $.ajax({
        type:"post",
        url:"/node/count",
        data:{"user_id":"1"},
        success:function (data) {
            if(data[0].responseId==="S10001"){
                var Data = data[1];
                bounty.default({ el:'#projectsOdometer',value:Data[0].toString() })
            }
            else layer.msg("获取设备统计数据失败！错误码："+data[0].responseMsg);
        }
    });
    //trigger_count
    $.ajax({
        type:"post",
        url:"/trigger/count",
        data:{"user_id":"1"},
        success:function (data) {
            if(data[0].responseId==="S10001"){
                var Data = data[1];
                bounty.default({ el:'#shopOdometer',value:Data[0].toString() })
            }
            else layer.msg("获取触发器统计数据失败！错误码："+data[0].responseMsg);
        }
    });
    //history_count
    $.ajax({
        type:"post",
        url:"/history/count",
        data:{"user_id":"1"},
        success:function (data) {
            if(data[0].responseId==="S10001"){
                var Data = data[1];
                bounty.default({ el:'#interviewsOdometer',value:Data[0].toString() })
            }
            else layer.msg("获取历史收发数据失败！错误码："+data[0].responseMsg);
        }
    });
}
function avg_month(data) {
    var res = 0;
    if(data>0){
        res = data/day;
    }
    bounty.default({el:"#avg",value:res.toString()});
}
function contrast(this_month,previous_month) {
    var res = 0;
    if(previous_month>0){
        res = (this_month-previous_month) / previous_month * 100;
    }
    bounty.default({el:"#contrast",value:res+"%"});
}
function history_3month_count() {
    $.ajax({
        type:"post",
        url:"/history/count/3month",
        data:{"user_id":"1"},
        success:function (data) {
            if(data[0].responseId==="S10001"){
                var Data = data[1];
                var month_array = Data[0];
                var this_month = month_array[0];
                var previous_month = month_array[1];
                avg_month(this_month);
                contrast(this_month,previous_month);
                bounty.default({ el:'#month',value:this_month.toString() });
                bounty.default({ el:'#month_0',value:this_month.toString() });
                bounty.default({ el:'#month_1',value:previous_month.toString() });
                bounty.default({ el:'#month_2',value:month_array[2].toString() });
            }
            else layer.msg("获取月份统计数据失败！错误码："+data[0].responseMsg);
        }
    });
}
function month_send() {
    $.ajax({
        type:"post",
        url:"/node_action/month_count",
        data:{"user_id":"1"},
        success:function (data) {
            if(data[0].responseId=="S10001"){
                bounty.default({ el:'#month_send',value:data[1].toString() });
            }
            else layer.msg("获取本月发送数据失败！错误码："+data[0].responseMsg);
        }
    })
}

function month_data_echarts() {
    $.ajax({
        type:"post",
        url:"/node_action/month_data",
        data:{"user_id":"1"},
        success:function (data) {
            if(data[0].responseId=="S10001"){
                let data_obj = data[1];
                let arrays = data_obj[0];
                var time_line = arrays[0];
                var send_arrays = arrays[1];
                var back_arrays = arrays[2];
                var chartDom = document.getElementById("audienceOverview");
                var myChart = echarts.init(chartDom, 'dark');
                var option;
                option = {
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['发送数据', '回传数据']
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
                        data: time_line
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '发送数据',
                            type: 'line',
                            stack: '总量',
                            data: send_arrays
                        },
                        {
                            name: '回传数据',
                            type: 'line',
                            stack: '总量',
                            data: back_arrays
                        }
                    ]
                };
                myChart.setOption(option);
            }
            else layer.msg("获取本月发送数据失败！错误码："+data[0].responseMsg);
        }
    });

}

function iot_index() {
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
        "          <h3>广西恩吉科技IOT平台 v0.2</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t<div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel\">\n" +
        "\t\t\t\t\t\t\t<div class=\"social-details clearfix\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-icon pull-left\" style='margin-top: 3%'>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"round-icon red-icon\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-list-numbered\"></i>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-num\">\n" +
        "\t\t\t\t\t\t\t\t\t<h2><span id=\"appointmentsOdometer\" class=\"odometer\" style='fill: white'></span> <span class=\"label label-danger\">7%</span></h2>\n" +
        "\t\t\t\t\t\t\t\t\t<p>我的场景</p>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"progress progress-xsx\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"progress-bar progress-bar-danger\" role=\"progressbar\" aria-valuenow=\"65\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 65%\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel\">\n" +
        "\t\t\t\t\t\t\t<div class=\"social-details clearfix\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-icon pull-left\" style='margin-top: 3%'>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"round-icon yellow-icon\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-drive\"></i>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-num\">\n" +
        "\t\t\t\t\t\t\t\t\t<h2><span id=\"projectsOdometer\" class=\"odometer\" style='fill: white'></span><span class=\"label label-warning\">8%</span></h2>\n" +
        "\t\t\t\t\t\t\t\t\t<p>我的设备</p>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"progress progress-xsx\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"progress-bar progress-bar-warning\" role=\"progressbar\" aria-valuenow=\"45\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 45%\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel\">\n" +
        "\t\t\t\t\t\t\t<div class=\"social-details clearfix\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-icon pull-left\" style='margin-top: 3%'>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"round-icon green-icon\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-equalizer2\"></i>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-num\">\n" +
        "\t\t\t\t\t\t\t\t\t<h2><span id=\"shopOdometer\" class=\"odometer\" style='fill: white'></span><span class=\"label label-success\">9%</span></h2>\n" +
        "\t\t\t\t\t\t\t\t\t<p>我的触发器</p>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"progress progress-xsx\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"progress-bar progress-bar-success\" role=\"progressbar\" aria-valuenow=\"72\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 72%\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel\">\n" +
        "\t\t\t\t\t\t\t<div class=\"social-details clearfix\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-icon pull-left\" style='margin-top: 3%'>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"round-icon blue-icon\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<i class=\"icon-line-graph\"></i>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t<div class=\"social-num\">\n" +
        "\t\t\t\t\t\t\t\t\t<h2><span id=\"interviewsOdometer\" class=\"odometer\" style='fill: white'></span><span class=\"label label-info\">9+</span></h2>\n" +
        "\t\t\t\t\t\t\t\t\t<p>收发数据</p>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"progress progress-xsx\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"progress-bar progress-bar-info\" role=\"progressbar\" aria-valuenow=\"30\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 30%\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>\n" +

        "\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t<div class=\"col-lg-6 col-md-9 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel height2\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t<h4>本月收发数据</h4>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t<div id=\"audienceOverview\" class=\"chart-height1\" style='z-index: 999'></div>\n" +
        // "\t\t\t\t\t\t\t\t<h1 class=\"audience-total\"><i class=\"icon-triangle-up\"></i>3%<span>本月 / 上月</span></h1>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-2 col-md-3 col-sm-4 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t\t\t<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"panel height2\">\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<h4>历史3月收发数据</h4>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<ul class=\"sales-q2\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<li class=\"clearfix\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"month-type warning\">"+month+"月</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"sale-info\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t<h3 id='month_0' style='display: inline;fill: white'></h3><h3 style='display: inline;margin-left: 5%'><span class=\"text-yellow\"><i class=\"icon-triangle-up\"></i></span></h3>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<li class=\"clearfix\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"month-type info\">"+(month-=1)+"月</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"sale-info\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t<h3 id='month_1' style='display: inline;fill: white'></h3><h3 style='display: inline;margin-left: 5%'><span class=\"text-blue\"><i class=\"icon-triangle-up\"></i></span></h3>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<li class=\"clearfix\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"month-type\">"+(month-=1)+"月</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"sale-info\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t\t<h3 id='month_2' style='display: inline;fill: white'></h3><h3 style='display: inline;margin-left: 5%'><span class=\"text-red\"><i class=\"icon-triangle-down\"></i></span></h3>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
        "\t\t\t\t\t\t\t\t\t\t</ul>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-4 col-md-12 col-sm-8 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t\t\t<div class=\"col-lg-6 col-md-3 col-sm-6 col-xs-6\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"panel height1\">\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<h4>本月发送数据</h4>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"sessions\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<h2 id='month_send' style='fill: white'></h2>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<div id=\"sessions\" class=\"graph\"></div>\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"col-lg-6 col-md-3 col-sm-6 col-xs-6\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"panel height1\">\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<h4>本月回传数据</h4>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"sessions\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<h2 id='month' style='fill: white'></h2>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<div id=\"users\" class=\"graph\"></div>\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"col-lg-6 col-md-3 col-sm-6 col-xs-6\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"panel height1\">\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<h4>本月平均每日收发</h4>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"sessions\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<h2 id='avg' style='fill: white'></h2>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<div id=\"duration\" class=\"graph\"></div>\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"col-lg-6 col-md-3 col-sm-6 col-xs-6\">\n" +
        "\t\t\t\t\t\t\t\t<div class=\"panel height1\">\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<h4>同比上月</h4>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<div class=\"sessions\">\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<h2 style='display: inline;position: absolute;left: 20%;top: 25%'><span class=\"text-red\"><i class=\"icon-triangle-down\"></i></span></h2><h2 id='contrast' style='display: inline;fill: white'></h2>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<div id=\"bouncerate\" class=\"graph\"></div>\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>\n" +

        "\t\t\t\t<div class=\"row gutter\">\n" +
        "\t\t\t\t\t<div class=\"col-lg-6 col-md-9 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel height2\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t<h4>场景汇总</h4>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t<div id=\"iot_scene\" class=\"chart-height1\"></div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t<div class=\"col-lg-6 col-md-9 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel height2\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-heading\">\n" +
        "\t\t\t\t\t\t\t\t<h4>场景定位</h4>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t<div id=\"audienceOverview\" class=\"chart-height1\"></div>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>\n" +
        "\t\t\t\t</div>\n" +
        "</div>");

    for(var i=0;i<9;i++){
        $("#iot_scene").append("<div class=\"col-md-4 col-sm-6 col-xs-12\" style='width: 25%;cursor:pointer;'>\n" +
            "    <div class=\"panel users-wrapper blue\" style='padding: 5%;border: 1px solid rgba(18,164,244,0.6);border-radius: 15%;'>\n" +
            "     <div class=\"users-info clearfix\" style='padding: 0'>\n" +
            "      <div class=\"users-avatar\">\n" +
            "       <img src=\"img/thumbs/user4.png\" class=\"img-responsive\" alt=\"Arise Admin\">\n" +
            "      </div>\n" +
            "      <div class=\"users-detail\">\n" +
            "       <h5>场景1号</h5>\n" +
            "      <p style='color: #A9BD7A'>正常状态</p>"+
            "      </div>\n" +
            "     </div>\n" +
            "      <ul class=\"users-footer clearfix\" style='border-bottom-left-radius: 30%;border-bottom-right-radius: 30%;'>\n" +
            "       <li style='width: 100%;'>\n" +
            "         <p class=\"light\">注册时间</p>\n" +
            "         <p>2021-05-06</p>\n" +
            "       </li>\n" +
            "      </ul>\n" +
            "    </div>\n" +
            "   </div>\n");
    }
    month_data_echarts();
    data_count();
    history_3month_count();
    month_send();
}