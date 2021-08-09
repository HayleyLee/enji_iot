
// document.write("<script src=\"/develop/js/jquery.js\"></script>");
// document.write("<script src=\"/develop/js/bootstrap.min.js\"></script>");
// document.write("<script src=\"/develop/js/scrollup/jquery.scrollUp.js\"></script>");
//
// document.write("<script src=\"/develop/js/gallery/baguetteBox.js\" async></script>");
// document.write("<script src=\"/develop/js/gallery/plugins.js\" async></script>");
// document.write("<script src=\"/develop/js/gallery/custom-gallery.js\" async></script>");
// document.write("<script src=\"/develop/js/custom.js\"></script>");

function iot_scene_all() {
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
        "          <h3>全部场景</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "   <a class=\"btn btn-info\" style='float: right;border-radius: 5px'><i class=\"icon-user-check\"></i>添加新场景</a>\n" +
        "</div>\n" +
        "<div id='main' class=\"row gutter\">\n" +
        "<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "\t\t\t\t\t\t<div class=\"panel\">\n" +
        "\t\t\t\t\t\t\t<div class=\"panel-body\">\n" +
        "\t\t\t\t\t\t\t\t<section class=\"pricePlans\">\n" +
        "\t\t\t\t\t\t\t\t\t<ul class=\"plans\" id='scene'>\n" +

        "\t\t\t\t\t\t\t\t\t</ul>\n" +
        "\t\t\t\t\t\t\t\t</section>\n" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>" +
        "</div>\n" +
        "</div>");
    for(var i=0;i<9;i++){
        $("#scene").append("<li class=\"plan bordered\" style='margin: 0 1% 2% 0;'>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t<ul class=\"planContainer\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"title\"><h2 style='color:#c9c9cf;'>智能农业大棚1号</h2></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<ul class=\"options\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li><img width='50%' src='/develop/static/img/test.jpeg'></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li><span style='font-weight: bold'>中国</span><span> - </span><span style='font-weight: bold'>广西</span><span> - </span><span style='font-weight: bold'>梧州</span></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li style='margin-top: -3%;color: #c9c9cf'>设备: <span>11</span></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li style='margin-top: -3%;color: #c9c9cf'>触发器: <span>31</span></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t<li style='margin-top: -3%;color: #c9c9cf'>创建日期: <span>2021-08-03</span></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t<ul class=\"options\" id='tree'>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t</li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"button\"><a href=\"javascript:void(0)\" style='color: #c9c9cf'>详情</a></li>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</ul>\n" +
            "\t\t\t\t\t\t\t\t\t\t</li>");
    }
}

function iot_scene_video() {
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
        "          <h3>视频回放</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "\t\t\t\t\t<div class=\"col-lg-12 col-md-12 col-sx-12 col-sm-12\">\n" +
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
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 3%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "<span style='margin-left: 3%;'>-</span>" +
        "\t\t\t\t\t\t\t\t\t\t\t<input style='margin-left: 3%;display: inline;width: 200px;height: 35px' type=\"date\" class=\"form-control\" id=\"inputDate\" placeholder=\"Enter Date\">\n" +
        "\t\t\t\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t</div>" +
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t<div class=\"baguetteBoxThree gallery\">\n" +
        "\t\t\t\t\t\t\t<div class=\"row gutter\" id='main'>\n" +

        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t</div>" +
        "\t\t\t\t</div>" +
        "</div>\n" +
        "</div>");
    for(var i=0;i<9;i++){
        $("#main").append(
            "\t\t\t\t\t\t\t\t<div class=\"col-lg-3 col-md-3 col-sm-3 col-xs-6\">\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"javascript:void (0)\" style='margin: 5% 0 0 0'>\n" +
            "\t\t\t\t\t\t\t\t\t\t<img src=\"/develop/img/thumbs/lg-one.jpg\" class=\"img-responsive\" alt=\"Arise Admin\">\n" +
            "\t\t\t\t\t\t\t\t\t\t<div class=\"overlay\">\n" +
            "\t\t\t\t\t\t\t\t\t\t\t<span class=\"expand\"><img width='100%' style='position: absolute;left: 5%' src='/develop/static/img/play.png'></span>\n" +
            "\t\t\t\t\t\t\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t</a>\n" +
            "<div>" +
            "<span style='float: right;margin-right: 3%;color: #D2A968;font-weight: bold'>【2021-08-05】 14:30 / 15:30</span>" +
            "</div>" +
            "\t\t\t\t\t\t\t\t</div>\n");
    }
}