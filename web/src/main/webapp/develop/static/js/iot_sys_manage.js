
function sys_user_list() {
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
        "          <h3>用户列表</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"用户名 ...\">\n" +
        "         <i class=\"icon-search3\"></i>\n" +
        "      </div>"+
        "</div>\n" +
        "<div id='main' class=\"row gutter\">\n" +

        "</div>\n" +
        "</div>");
    for (let i = 0; i < 9; i++) {
        str += "<div class=\"col-md-4 col-sm-6 col-xs-12\">\n" +
            "    <div class=\"panel users-wrapper blue\">\n" +
            "     <div class=\"users-info clearfix\">\n" +
            "      <div class=\"users-avatar\">\n" +
            "       <img src=\"img/thumbs/user4.png\" class=\"img-responsive\" alt=\"Arise Admin\">\n" +
            "      </div>\n" +
            "      <span style='position: absolute;top: 50%;left: 4%;color: #A9BD7A'>正常状态</span>"+
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
            "       <li>\n" +
            "         <p class=\"light\">注册时间</p>\n" +
            "         <p>2021-05-06 21:30:07</p>\n" +
            "       </li>\n" +
            "       <li>\n" +
            "         <i title='正常状态' class=\"icon-controller-record status\"></i>\n" +
            "       </li>\n" +
            "      </ul>\n" +
            "    </div>\n" +
            "   </div>\n";
    }
    $("#main").append(str);
}

function sys_service_count() {
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3>敬请期待...</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div id='main' class=\"row gutter\">\n" +

        "</div>\n" +
        "</div>");
}

function sys_scene_list() {
    let str = "";
    //加载动画
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").empty().append("\n" +
        "   <div class=\"container-fluid\">\n" +
        "    <div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3>场景列表</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"项目名 ...\">\n" +
        "         <i class=\"icon-search3\"></i>\n" +
        "      </div>"+
        "     </div>\n" +
        "    <div class=\"row gutter\" id=\"main\"></div>\n" +
        "   </div>");
    for (let i = 0; i < 9; i++) {
        str+="<div class=\"col-md-4 col-sm-4 col-xs-12\">\n" +
            "      <div class=\"task-wrapper\" id=\"completed\">\n" +
            "       <h4 class=\"task-name text-green\">测试场景 - <span id=\"completedOdometer\" class=\"odometer\">0</span></h4>\n" +
            "       <div id='scene_more' style='margin-bottom: 3%'>" +
            "         <img width='100%' height='auto' src='/develop/img/timeline.jpg' style='margin-bottom: 3%'>\n" +
            "         <span style='color: #A9BD7A;font-size: 1.6rem'>场景描述：</span>" +
            "         <span>测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述测试场景描述</span><br>" +
            "         <span style='color:#BF7A6A;font-size: 1.6rem'>场景备注：</span>" +
            "         <span>测试场景</span><br>" +
            "         <span style='color: #6FB4CE;font-size: 1.6rem'>新增时间：</span>" +
            "         <span>2021-03-16 14:42:41</span>" +
            "       </div>\n" +
            "       <div class=\"task-block new\">\n" +
            "        <h5 class=\"task-id\">admin<i class=\"icon-controller-record\"></i></h5>\n" +
            "        <div class=\"assigned-user\">\n" +
            "         <img src=\"img/thumbs/user2.png\" class=\"img-responsive\" alt=\"Arise Admin\" />\n" +
            "        </div>\n" +
            "        <p class=\"task-desc\">超级管理员</p>\n" +
            "        <ul class=\"task-footer\">\n" +
            "         <li>手机号：</li>\n" +
            "        </ul>\n" +
            "        <span class=\"task-type\">17677317790</span>\n" +
            "       </div>\n" +
            "      </div>\n" +
            "     </div>";
    }
    $("#main").append(str);
}

function sys_dev_list() {
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
        "          <h3>设备列表</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"设备号（SN） ...\">\n" +
        "         <i class=\"icon-search3\"></i>\n" +
        "      </div>"+
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "      <div class=\"panel\">\n" +
        "       <div class=\"panel-body\">\n" +
        "        <section class=\"pricePlans\">\n" +
        "         <ul id='main' class=\"plans\" style='margin-left: 1%'>\n" +

        "         </ul>\n" +
        "        </section>\n" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>"+
        "</div>\n" +
        "</div>");
    for (let i = 0; i < 4; i++) {
        str += "<li class=\"plan bordered dev-list\" style='margin-right: 1%;margin-top: 10px;border: 0'>\n" +
            "           <ul class=\"planContainer\">\n" +
            "            <li>\n" +
            "              <img title='在线' src='/develop/static/img/devOnLine.png'>"+
            "            </li>\n" +
            "            <li class=\"title\">" +
            "              <h2 style='font-size:2rem;color: #fff;font-weight: bolder'>" +
            "                  <span style='color: #3ba768'>（在线）</span>测试设备 1" +
            "              </h2>" +
            "            </li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>设备号：<span>ZJp29PZR2uJnfahF</span></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>所属项目：<span>研发室测试1</span></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>所属用户：<span>admin</span></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>通讯类型：<span>MQTT</span></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>新增时间：<span>2021-06-11</span></p></li>\n" +
            "            <li class=\"button\"><a href=\"javascript:void(0)\">查看更多</a></li>\n" +
            "           </ul>\n" +
            "          </li>";
    }
    $("#main").append(str);
    $(".container-fluid").append("<ul class=\"pager no-margin\">\n" +
        "        <li>\n" +
        "         <a href=\"javascript:void(0)\" class=\"btn btn-\">← 上一列</a>\n" +
        "        </li>\n" +
        "        <li>\n" +
        "         <a href=\"javascript:void(0)\" class=\"btn btn-\">下一列 →</a>\n" +
        "        </li>\n" +
        "       </ul>");
}

function sys_dev_store() {
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
        "          <h3>设备仓库</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"设备号（SN） ...\">\n" +
        "         <i class=\"icon-search3\"></i>\n" +
        "      </div>"+
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "    <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "      <div class=\"panel\">\n" +
        "       <div class=\"panel-body\">\n" +
        "        <section class=\"pricePlans\">\n" +
        "         <ul id='main' class=\"plans dev-store\" style='margin-left: 1%;'>\n" +

        "         </ul>\n" +
        "        </section>\n" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>"+
        "</div>\n" +
        "</div>");
    for (let i = 0; i < 10; i++) {
        str += "<li class=\"plan bordered dev-list\" style='margin-right: 1%;margin-top: 10px;border: 0'>\n" +
            "           <ul class=\"planContainer\">\n" +
            "            <li>\n" +
            "              <img title='在线' src='/develop/static/img/devOffLine.png'>"+
            "            </li>\n" +
            "            <li class=\"title\">" +
            "              <h2 style='font-size:2rem;color: #fff;font-weight: bolder'>" +
            "                  <span style='color: #a72d1c'>（未连接）</span>导入设备 1" +
            "              </h2>" +
            "            </li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>所属用户：<span>暂无</span></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>设备号：<span>enji20210706</span></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>通讯类型：<span>MQTT</span></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>二维码：<img src='/develop/static/img/qrTest.jpg' height='35px'></p></li>\n" +
            "            <li class=\"price\"><p style='margin: 0 0 5px;text-align: left;padding-left: 25%'>新增时间：<span>2021-06-11</span></p></li>\n" +
            "            <li class=\"button\"><a href=\"javascript:void(0)\">删除</a></li>\n" +
            "           </ul>\n" +
            "          </li>";
    }
    $("#main").append(str);
    $(".container-fluid").append("<ul class=\"pager no-margin\">\n" +
        "        <li>\n" +
        "         <a href=\"javascript:void(0)\" class=\"btn btn- prev\">← 上一列</a>\n" +
        "        </li>\n" +
        "        <li>\n" +
        "         <a href=\"javascript:void(0)\" class=\"btn btn- next\">下一列 →</a>\n" +
        "        </li>\n" +
        "       </ul>");


}

function sys_dictionary_list() {
    let str = "";
    //加载动画
    $(".loading-wrapper").fadeIn();
    $(".loading-wrapper").fadeOut(1000);
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <span style='float:right'>当前菜单：数据字典&nbsp;&nbsp;&nbsp;<a href='javascript:void(0)' style='color: #1dabdd'>返回上一级</a></span>"+
        "      <div class=\"col-lg-6\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3>数据字典</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "     <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "      <div class=\"panel panel-red\">\n" +
        "       <div class=\"panel-heading\" style='height: 40px'>\n" +
        "            <div class=\"form-group no-margin col-lg-6 hidden-sm hidden-xs hidden-md\" style='width: 100%;'>\n" +
        "              <div style='display: inline-block;width: 15%'>"+
        "               <input placeholder='名称' type=\"text\" class=\"form-control\" id=\"inputDefault\">\n" +
        "              </div>"+
        "              <div  style='display: inline-block;width: 15%;'>"+
        "               <input placeholder='分类名' type=\"text\" class=\"form-control\" id=\"inputDefault\">\n" +
        "              </div>"+
        "              <div style='display: inline-block;width: 15%'>"+
        "               <input placeholder='状态码' type=\"text\" class=\"form-control\" id=\"inputDefault\">\n" +
        "              </div>"+
        "              <div style='display: inline-block;width: 15%'>"+
        "               <select class=\"form-control\" name=\"genre\" >\n" +
        "                   <option value=\"comedy\">请选择</option>\n" +
        "                   <option value=\"action\">正常显示</option>\n" +
        "                   <option value=\"horror\">已隐藏</option>\n" +
        "               </select>\n" +
        "              </div>"+
        "              <div style='display: inline-block;width: 22%;margin-left: 1%'>"+
        "              <a href=\"javascript:void(0)\" class=\"btn btn-info btn-transparent\">查询</a>"+
        "              <a href=\"javascript:void(0)\" class=\"btn btn-success btn-transparent\">增加数据字典</a>\n" +
        "              </div>"+
        "            </div>"+
        "       </div>\n" +
        "       <div class=\"panel-body\">\n" +
        "        <div class=\"table-responsive\">\n" +
        "         <table class=\"table table-condensed table-hover no-margin\">\n" +
        "          <thead>\n" +
        "           <tr>\n" +
        "            <th class=\"span1\">#</th>\n" +
        "            <th class=\"span4\">名称</th>\n" +
        "            <th class=\"span5 hidden-phone\">分类</th>\n" +
        "            <th class=\"span2 hidden-phone\">状态码</th>\n" +
        "            <th class=\"span2 hidden-phone\">父类</th>\n" +
        "            <th class=\"span2 hidden-phone\">状态码/父类</th>\n" +
        "            <th class=\"span4\">操作</th>\n" +
        "           </tr>\n" +
        "          </thead>\n" +
        "          <tbody id='main'>\n" +

        "          </tbody>\n" +
        "         </table>\n" +
        "        </div>\n" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>\n" +
        "    </div>\n"+
        " </div>");
    for (let i = 0; i < 9; i++) {
        str +=  "       <tr>\n" +
            "            <td>2</td>\n" +
            "            <td>摄像头类型</td>\n" +
            "            <td>CAMERA_TYPE</td>\n" +
            "            <td>281</td>\n" +
            "            <td></td>\n" +
            "            <td></td>\n" +
            "            <td>" +
            "              <a style='height: 30px;line-height: 15px' href=\"javascript:void(0)\" class=\"btn btn-success btn-transparent btn-rounded\">修改</a>\n" +
            "              <a style='height: 30px;line-height: 15px' href=\"javascript:void(0)\" class=\"btn btn-info btn-transparent btn-rounded\">子菜单</a>\n" +
            "              <a style='height: 30px;line-height: 15px' href=\"javascript:void(0)\" class=\"btn btn-warning btn-transparent btn-rounded\">隐藏</a>\n" +
            "              <a style='height: 30px;line-height: 15px' href=\"javascript:void(0)\" class=\"btn btn-danger btn-transparent btn-rounded\">删除</a>" +
            "            </td>\n" +
            "           </tr>\n";
    }
    $("#main").append(str);
}

function sys_lpm_list() {
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3>敬请期待...</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div id='main' class=\"row gutter\">\n" +

        "</div>\n" +
        "</div>");
}

function sys_wechat_menu() {
    $(".center").empty();
    $(".center").removeAttr("style");
    $(".center").append("<div class=\"container-fluid\">\n" +
        "<div class=\"top-bar clearfix\">\n" +
        "      <div class=\"col-lg-6 hidden-sm hidden-xs hidden-md\">\n" +
        "        <div class=\"page-title\">\n" +
        "          <h3>敬请期待...</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "</div>\n" +
        "<div id='main' class=\"row gutter\">\n" +

        "</div>\n" +
        "</div>");
}

function sys_param_setting() {
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
        "          <h3>系统参数</h3>\n" +
        "        </div>\n" +
        "      </div>\n" +
        "      <div class=\"custom-search \">\n" +
        "         <input type=\"text\" class=\"search-query\" placeholder=\"用户名 ...\">\n" +
        "         <i class=\"icon-search3\"></i>\n" +
        "      </div>"+
        "</div>\n" +
        "<div class=\"row gutter\">\n" +
        "     <div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">\n" +
        "      <div class=\"panel panel-red\">\n" +
        "       <div class=\"panel-body\">\n" +
        "        <div class=\"table-responsive\">\n" +
        "         <table class=\"table table-condensed table-hover no-margin\">\n" +
        "          <thead>\n" +
        "           <tr>\n" +
        "            <th class=\"span1\">#</th>\n" +
        "            <th class=\"span4\">名称key</th>\n" +
        "            <th class=\"span5 hidden-phone\">值</th>\n" +
        "            <th class=\"span2 hidden-phone\">备注</th>\n" +
        "            <th class=\"span4\">操作</th>\n" +
        "           </tr>\n" +
        "          </thead>\n" +
        "          <tbody id='main'>\n" +

        "          </tbody>\n" +
        "         </table>\n" +
        "        </div>\n" +
        "       </div>\n" +
        "      </div>\n" +
        "     </div>\n" +
        "    </div>\n"+
        " </div>");

    for (let i = 0; i < 9; i++) {
        str +=  "       <tr>\n" +
            "            <td>1</td>\n" +
            "            <td>tsys.tech.help</td>\n" +
            "            <td>广西恩吉科技有限公司</td>\n" +
            "            <td>\t系统服务访问URL,不需要斜杠结尾</td>\n" +
            "            <td>" +
            "             <span title='编辑/修改' class='icon-edit edit'></span>" +
            "            </td>\n" +
            "           </tr>\n";
    }
    $("#main").append(str);
}