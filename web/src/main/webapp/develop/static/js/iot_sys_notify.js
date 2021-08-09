
function sys_notify() {
    $(".sys_notify_li").addClass("active selected").siblings().removeClass("active selected");
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
        "          <h3>通知与反馈</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        " <div class=\"row gutter\">\n" +
        "     <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "      <div id='add_scroll' class=\"panel height1\" style='height: auto'>\n" +
        "       <div class=\"panel-body\" id='main' style='padding: 0'>\n" +
        "        <!-- Content goes here -->\n" +
        "        <a href=\"javascript:void(0)\" class=\"btn btn-info btn-transparent\" style='margin-bottom: 1.2%'><i class='icon-checkmark2'></i>全部全选</a>"+
        // "        <a class=\"btn btn-info\" style='margin-bottom: 10px'><i class=\"icon-checkmark2\"></i>全选</a>" +
        "       <a href=\"javascript:void(0)\" class=\"btn btn-default\" style='margin-bottom: 1.2%;float: right'>问题反馈</a>" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>\n" +
        "    </div>"+
        "</div>");

    for (let i = 0; i < 5; i++) {
        str +=  "<div id='notify"+i+"' class=\"task-block new houver-big\" style='cursor: pointer'>\n" +
            "        <div style='width: 30px;float: left'>" +
            "          <i class='icon-checkmark2' style='font-size: 20px'></i>"+
            "        </div>"+
            "        <h5 class=\"task-id\" style='font-weight: bolder;color: #d4d3d2'>2021年度物联网评选”投票开始！" +
            "          <span class=\"info-label red-bg\">未读</span>" +
            "        </h5>\n" +
            "        <div class=\"assigned-user hidden-sm hidden-xs hidden-md\" style='height: auto;width: 100px'>\n" +
            "          <a href='javascript:void(0)' title='回复'>" +
            "            <i class='icon-reply-all' style='font-size: 20px;color:#6FB4CE'></i>"+
            "          </a>"+
            "          <a href='javascript:void(0)' title='已读' style='margin-left: 10px;'>" +
            "            <i class='icon-check2' style='font-size: 20px;color:#9eb569'></i>"+
            "          </a>"+
            "          <a href='javascript:void(0)' title='删除' style='margin-left: 10px;'>" +
            "            <i class='icon-bin' style='font-size: 20px;color: #BF7A6A'></i>"+
            "          </a>"+
            "        </div>\n" +
            "        <p class=\"task-desc\">梧州物联网奖节2021的“年度物联”评选新增“海外人气奖”.</p>\n" +
            "        <ul class=\"task-footer\">\n" +
            "         <li>时间：<span>2021-08-05 14:17:16</span></li>\n" +
            "        </ul>\n" +

            "      </div>\n";
    }
    $("#main").append(str);

    if($("#main").children().length>7){
        $("#add_scroll").css("height","780px");
        $("#add_scroll").css("overflow-y","auto");
    }
}