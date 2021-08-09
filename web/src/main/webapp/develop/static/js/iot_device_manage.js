
function iot_dev_info() {
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
        "          <h3>设备信息</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"设备名 ...\">\n" +
        "         <i class=\"icon-search3\"></i>\n" +
        "      </div>"+
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "  <div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n" +
        "    <div class=\"panel height2\" style='height: auto'>\n" +
        "      <div class=\"panel-heading\">\n" +
        "        <h4 class=\"panel-title\">场景列表</h4>\n" +
        "      </div>\n" +
        "      <div class=\"panel-body\" id='scene-rander'>\n" +
        "      <!-- Content goes here -->\n" +
        "      </div>\n" +
        "    </div>\n" +
        "  </div>\n" +
        "  <div class=\"col-lg-6 col-md-6 col-sm-6 col-xs-12\">\n" +
        "    <div class=\"panel height2\" style='height: auto'>\n" +
        "      <div class=\"panel-heading\">\n" +
        "        <h4 class=\"panel-title\">设备列表</h4>\n" +
        "      </div>\n" +
        "      <div class=\"panel-body\" id='device-rander'>\n" +
        "      <!-- Content goes here -->\n" +
        "      </div>\n" +
        "    </div>\n" +
        "  </div>"+
        "</div>\n" +
        "</div>");

    for (let i = 0; i < 4; i++) {
        str += "<div class=\"col-lg-3 col-md-4 col-sm-6 col-xs-12\" style='width: 50%;'>\n" +
            "      <div class=\"card-wrapper pale-green bg-img\">\n" +
            "       <div class=\"card clearfix\">\n" +
            "        <span onclick='openDevList(\"device\")' title='展开设备列表' class=\"card-type\" style='cursor: pointer'><i class='icon-fullscreen'></i></span>\n" +
            // "         <img src='/develop/img/timeline.jpg' style='margin:0;width:100%;height:140px' class=\"img-responsive card-avatar\" alt=\"Arise Admin\">"+
            "        <p style='font-size: 2rem;margin-top: 20px; color: #e2e2d9'>测试场景1</p>\n" +
            "       </div>\n" +
            "       <ul class=\"card-actions clearfix\">\n" +
            "        <li style='width: 100px;margin:0'>\n" +
            "          <a href=\"javascript:void(0)\" class=\"btn btn-info btn-transparent\">添加设备</a>\n" +
            "        </li>\n" +
            "        <li style='width: 100px;margin:0'>\n" +
            "          <a href=\"javascript:void(0)\" class=\"btn btn-success btn-transparent\">快捷添加</a>\n" +
            "        </li>\n" +
            "        <li style='width: 100px;margin:0'>\n" +
            "          <a href=\"javascript:void(0)\" class=\"btn btn-default btn-transparent\">绑定设备</a>\n" +
            "        </li>\n" +
            "       </ul>\n" +
            "      </div>\n" +
            "     </div>";
    }
    $("#scene-rander").append(str);
    openDevList("device");
}

function iot_trigger_info() {
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
        "\t\t\t\t\t\t\t\t<h3 style='width: 25%;display: inline;'>触发器信息</h3>\n" +
        "      <div class=\"custom-search \" style='float: none;display: inline; margin-left:5%'>\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"触发器名称...\">\n" +
        "         <i class=\"icon-search3\" style='top: 2%'></i>\n" +
        "      </div>"+
        "\t\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t</div>\n" +
        "\t\t\t\t\t\t\t\t\t<a class=\"btn btn-info\" style='float: right;border-radius: 5px'><i class=\"icon-user-check\"></i>添加触发器</a>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div id='main' class=\"row gutter\">\n" +
        " <div class=\"col-lg-4 col-md-4 col-sm-6 col-xs-12\">\n" +
        "      <div class=\"panel trigger-big-height\">\n" +
        "       <div class=\"panel-heading\">\n" +
        "        <h4 class=\"panel-title\" style='color: #A9BD7A;font-size: 2rem'>场景列表</h4>\n" +
        "       </div>\n" +
        "       <div class=\"panel-body\"  id='trigger_scene_rander'>\n" +
        "        <!-- Content goes here -->\n" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>\n" +
        "     <div class=\"col-lg-4 col-md-4 col-sm-6 col-xs-12\">\n" +
        "      <div class=\"panel trigger-big-height\">\n" +
        "       <div class=\"panel-heading\">\n" +
        "        <h4 class=\"panel-title\" style='color: #6FB4CE;font-size: 2rem'>设备列表</h4>\n" +
        "       </div>\n" +
        "       <div class=\"panel-body\" id='device-rander'>\n" +
        "        <!-- Content goes here -->\n" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>\n" +
        "     <div class=\"col-lg-4 col-md-4 col-sm-8 col-xs-12\">\n" +
        "      <div class=\"panel trigger-big-height\">\n" +
        "       <div class=\"panel-heading\">\n" +
        "        <h4 class=\"panel-title\" style='color: #BF7A6A;font-size: 2rem'>触发器列表</h4>\n" +
        "       </div>\n" +
        "       <div class=\"panel-body\" id='trigger_rander'>\n" +
        "        <!-- Content goes here -->\n" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>"+
        "</div>\n" +
        "</div>");

    for (let i = 0; i < 9; i++) {
        str +=  "<div id='scene"+i+"' class=\"task-block new\" style='cursor: pointer' onclick='openDevList(\"trigger\")'>\n" +
            "        <h5 class=\"task-id\">测试场景1<i class=\"icon-controller-record\"></i></h5>\n" +
            "        <div class=\"assigned-user\">\n" +
            "         <img src=\"/develop/img/timeline.jpg\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
            "        </div>\n" +
            "        <p class=\"task-desc\">admin</p>\n" +
            "        <ul class=\"task-footer\">\n" +
            "         <li>场景备注：<span>这是测试场景</span></li>\n" +
            "        </ul>\n" +
            "        <span class=\"task-type\">2021-08-05 14:17:16</span>\n" +
            "      </div>\n";
    }
    $("#trigger_scene_rander").append(str);
    $(".task-block").click(function() {
        $(this).css("background-color","#40435a").siblings().css("background-color","#353C48");
    });
}

function openDevList(flag) {
    let str = "";
    $("#device-rander").empty();
    $("#device-rander").removeAttr("style");
    if (flag === "device"){
        for (let i = 0; i < 9; i++) {
            str +=  "<div id='dev"+i+"' class=\"task-block new\" style='cursor: pointer'>\n" +
                "        <h5 class=\"task-id\">模拟压力测试<i class=\"icon-controller-record\"></i></h5>\n" +
                "        <div class=\"assigned-user\">\n" +
                "         <img src=\"/develop/static/img/devOffLine.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
                "        </div>\n" +
                "        <p class=\"task-desc\">MQTT</p>\n" +
                "        <ul class=\"task-footer\">\n" +
                "         <li>新增时间：<span>2021-08-05 14:17:16</span></li>\n" +
                "        </ul>\n" +
                "        <span class=\"task-type\">ZJp29PZR2uJnfahF</span>\n" +
                "       <div id='menu-dev"+i+"' class=\"menu-dev\">\n" +
                "            <ul>\n" +
                "                <li title='编辑'><i style='font-size: 2rem;color: #6FB4CE' class='icon-edit'></i></li>\n" +
                "                <li title='迁移设备'><i style='font-size: 2rem;color: #A9BD7A' class='icon-aircraft-take-off'></i></li>\n" +
                "                <li title='克隆新增'><i style='font-size: 2rem;color: #556174' class='icon-stack3'></i></li>\n" +
                "                <li title='删除'><i style='font-size: 1.8rem;color: #BF7A6A' class='icon-bin'></i></li>\n" +
                "            </ul>\n" +
                "        </div>"+
                "      </div>\n";
        }
        $("#device-rander").append(str);
        //循环为不同的设备添加事件
        for (let i = 0; i < 9; i++) {
            let dev = "#dev"+i;
            let menu_dev = "#menu-dev"+i;
            let menu;
            // $(dev).on('mousedown',function(e){
            //     //获取鼠标键位
            //     let key = e.which;
            //     //1：代表左键；2：代表中键；3：代表右键
            //     if(key===3){
            //         //每次右键都隐藏一次所有菜单
            //         for (let j = 0; j < dev_list.length; j++) {
            //             menu = "#menu-dev"+j;
            //             $(menu).hide();
            //         }
            //         //获取右键点击坐标
            //         var x = e.offsetX;
            //         var y = e.offsetY;
            //         $(menu_dev).show().css({left:x+20,top:y});
            //     }
            // });
            // $(dev).on({
            //     mouseover:function () {
            //         // $(menu_dev).show();
            //         $(menu_dev).animate({
            //             height:'show'
            //         });
            //     },
            //     mouseout : function(){
            //         // $(menu_dev).hide()
            //         $(menu_dev).animate({
            //             height:'hide'
            //         });
            //     }
            // });
            $(dev).hover(function(){
                $(menu_dev).show(200);
            },function(){
                $(menu_dev).hide(200);
            });
        }

    }
    else {
        for (let i = 0; i < 9; i++) {
            str +=  "<div id='dev"+i+"' class=\"task-block new\" style='cursor: pointer' onclick='openTriggerList()'>\n" +
                "        <h5 class=\"task-id\">模拟压力测试<i class=\"icon-controller-record\"></i></h5>\n" +
                "        <div class=\"assigned-user\">\n" +
                "         <img src=\"/develop/static/img/devOffLine.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
                "        </div>\n" +
                "        <p class=\"task-desc\">MQTT</p>\n" +
                "        <ul class=\"task-footer\">\n" +
                "         <li>新增时间：<span>2021-08-05 14:17:16</span></li>\n" +
                "        </ul>\n" +
                "        <span class=\"task-type\">ZJp29PZR2uJnfahF</span>\n" +
                "      </div>\n";
        }
        $("#device-rander").append(str);
        $(".task-block").click(function() {
            $(this).css("background-color","#40435a").siblings().css("background-color","#353C48");
        });
    }
}

function openTriggerList() {
    let str = "";
    $("#trigger_rander").empty();
    $("#trigger_rander").removeAttr("style");
    for (let i = 0; i < 5; i++) {
        str +=  "<div id='dev"+i+"' class=\"task-block new\" style='cursor: pointer'>\n" +
            "        <h5 class=\"task-id\">超温报警器<i class=\"icon-controller-record\"></i></h5>\n" +
            "        <div class=\"assigned-user\" style='width: 70px;height: 70px'>\n" +
            "         <img style='width: 70px;height: 70px' src=\"/develop/static/img/devOffLine.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
            "        </div>\n" +
            "        <p class=\"task-desc\">关联设备：<span>模拟压力测试/temp</span></p>\n" +
            "        <p class=\"task-desc\">执行动作：<span>邮件通知</span></p>\n" +
            "        <p class=\"task-desc\">状态：<span>正常</span></p>\n" +
            "        <ul class=\"task-footer\">\n" +
            "         <li>触发条件：<span>数值高于{X}</span></li>\n" +
            "         <li>触发参数：<span>40</span></li>\n" +
            "         <li>循环间隔：<span>0</span></li>\n" +
            "        </ul>\n" +
            "        <span class=\"task-type\">" +
            "           <span style='font-size: 2rem;color: #6FB4CE' class='icon-edit'></span>" +
            "           <span style='font-size: 1.8rem;color: #BF7A6A' class='icon-bin'></span>" +
            "        </span>\n" +
            "      </div>\n";
    }
    /**
     * 此代码为在底部添加新增按钮
     */
    // str +=  "<div class=\"task-block new\" style='cursor: pointer;width: 40%;margin-left: 30%;text-align:center;border: 2px solid rgba(18, 164, 244, 0.5);margin-top: 1%'>\n" +
    //     "        <h3 style='color: #8c96a5;margin-bottom: 6%'>添加触发器</h3>\n" +
    //     "      </div>\n";
    $("#trigger_rander").append(str);
}
