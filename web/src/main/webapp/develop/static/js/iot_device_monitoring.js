function iot_dev_monitor() {
    let str = "";
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
        "<div id='window' style='width: 60%;float: right;background-color: white;padding: 1%'>" +

        "</div>\n"+
        "<canvas id='canvas'></canvas>\n"+
        "         <ul id='main' class=\"plans\">\n" +

        "         </ul>\n" +
        "        </section>\n" +
        "<div style='margin-left: 10%'>" +
        "         <span class=\"icon-chevron-left temp_icon\"></span>"+
        "         <span class=\"icon-chevron-right temp_icon\"></span>"+
        "</div>"+
        "       </div>\n" +
        "      </div>\n" +
        "     </div>"+
        "</div>\n" +
        "</div>");
    for (let i = 0; i < 10; i++) {
        str += "<li class=\"plan bordered dev-list\" style='border: 0;float: left;width: 10%;display: block;height: 150px;' lock='false'>\n" +
            "           <ul class=\"planContainer\">\n" +
            "            <li>\n" +
            "              <img width='100px' height='100px' title='在线' src='/develop/static/img/devOnLine.png'>\n"+
            "            </li>\n" +
            "            <li class=\"price\"><p style='color: #A9BD7A'>(在线)<span>测试设备1</span></p ></li>\n" +
        "           </ul>\n" +
        "          </li>";
    }
    $("#main").append(str);
    test();
    showInfo();
}

function showInfo() {
    $("#window").empty().append("<div style='width: 100%;background-color: #2a3039'>" +
        "<div style='width: 45%;margin-left:5%;font-size: 20px;font-weight: bold;color: #95a0b1;display:inline-block'>测试设备名1号<a href=\"javascript:void(0)\" class=\"btn btn-success btn-rounded\" style='margin:3% 10%;border-radius: 15px'>在线</a></div>" +
        "<div style='width: 45%;margin-right:5%;font-size: 20px;font-weight: bold;color: red;display:inline-block;text-align: right'>sn号: sgkagf&^*hdake12419d</div>" +
        "\t\t\t\t\t\t\t\t\t<table class=\"table table-bordered no-margin\">\n" +
        "\t\t\t\t\t\t\t\t\t\t<thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>#</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>单位</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>数值 / 状态</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>设置</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t\t<th>开关 / 控制</th>\n" +
        "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
        "\t\t\t\t\t\t\t\t\t\t</thead>\n" +
        "\t\t\t\t\t\t\t\t\t\t<tbody id='tbody'>\n" +

        "\t\t\t\t\t\t\t\t\t\t</tbody>\n" +
        "\t\t\t\t\t\t\t\t\t</table>\n" +
        "</div>");
    for(var i=0;i<4;i++){
        $("#tbody").append("\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>1</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>温度</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>28</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>" +
            "<a href=\"javascript:void(0)\" class=\"btn btn-warning btn-rounded\" style='border-radius: 5px'>自定义</a>" +
            "</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>" +
            "<a href=\"javascript:void(0)\" class=\"btn btn-info btn-rounded\" style='border-radius: 5px'>控制</a>" +
            "</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</tr>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t<tr>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>2</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>继电器</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>OFF</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>" +
            "<a href=\"javascript:void(0)\" class=\"btn btn-warning btn-rounded\" style='border-radius: 5px'>自定义</a>" +
            "</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t<td>" +
            "<img src='/develop/static/img/off.png'>" +
            "</td>\n" +
            "\t\t\t\t\t\t\t\t\t\t\t</tr>\n");
    }
}

function test() {
    var canvas = document.getElementById("canvas");
    var ctx = canvas.getContext("2d");
    var docks = document.getElementsByClassName("plan bordered dev-list");
    for(var i=0;i<docks.length;i++){
        docks[i].addEventListener("click", function () {
            if(this.getAttribute("lock") === "false") {
                var win = document.getElementById("window");
                var _this = this;
                _this.setAttribute("lock", "true");
                // canvas.width = document.body.offsetWidth;
                canvas.width = document.getElementsByClassName("pricePlans")[0].offsetWidth;
                // canvas.height = _this.parentNode.offsetTop - win.offsetTop;
                canvas.height = _this.offsetHeight+_this.offsetTop;
                canvas.style.top = win.offsetTop + "px";
                canvas.style.zIndex = 1;
                win.style.visibility = "hidden";
                var s1 = win.offsetLeft;
                var s2 = win.offsetLeft + win.offsetWidth;
                var p1 = _this.offsetLeft;
                var p2 = _this.offsetLeft + _this.offsetWidth;
                var cname = _this.className;
                if(cname.indexOf(" folded") === -1) {
                    scale(s1, s2, p1, p2, "zoomout", function () {
                        canvas.style.zIndex = -1;
                        _this.setAttribute("lock", "false");
                    });
                    _this.className = cname + " folded";
                } else {
                    scale(s1, s2, p1, p2, "zoomin", function () {
                        canvas.style.zIndex = -1;
                        win.style.visibility = "visible";
                        _this.setAttribute("lock", "false");
                    });
                    _this.className = cname.replace(" folded", "");
                }
            }
            showInfo();
        }, false);
    }
    /**
     * 绘制形状
     * @param s1 {Number} 起点一
     * @param s2 {Number} 起点二
     * @param p1 {Number} 结束点一
     * @param p2 {Number} 结束点二
     */
    function draw(s1, s2, p1, p2) {
        ctx.clearRect(0, 0,canvas.width, canvas.height);
        ctx.beginPath();
        ctx.moveTo(s1, 0);
        ctx.bezierCurveTo(s1, canvas.height * 0.2, p1, canvas.height * 0.6, p1, canvas.height);
        ctx.lineTo(p2, canvas.height);
        ctx.bezierCurveTo(p2, canvas.height * 0.6, s2, canvas.height * 0.2, s2, 0);
        ctx.lineTo(s1, 0);
        ctx.fillStyle = "rgba(255, 255, 255, 1)";
        ctx.fill();
    }
    /**
     * 擦除方式
     * @param y {Number}
     * @param speed {Number}
     * @param type 类型，放大或缩小 zoomin、zoomout
     */
    function clearRect(y, speed, type) {
        if(type === "zoomout") {
            ctx.clearRect(0, y, canvas.width, speed);
        } else if(type === "zoomin") {
            ctx.clearRect(0, 0, canvas.width, y);
        }
    }
    /**
     * 缩放效果
     * @param s1 {Number} 起点一
     * @param s2 {Number} 起点二
     * @param p1 {Number} 结束点一
     * @param p2 {Number} 结束点二
     * @param type {String} 类型，放大或缩小 zoomin、zoomout
     */
    function scale(s1, s2, p1, p2, type, callback) {
        var dist1 = Math.abs(p1 - s1);
        var dist2 = Math.abs(p2 - s2);
        var d1, d2, _p1, _p2, speed1, y, speed2;
        if(dist1 === 0 || dist2 === 0) {
            dist1 = 1;
            dist2 = 1;
        }
        speed1 = 20;
        speed2 = 20;
        if(type === "zoomout") {
            d1 = (p1 >= s1 && p1 < speed1) ? 0 : p1 < s1 ? -speed1 : speed1;
            d2 = p2 < s2 ? -speed1 * dist2 / dist1 : speed1 * dist2 / dist1;
            _p1 = s1;
            _p2 = s2;
            y = 0;
            var t = setInterval(function () {
                if(_p2 - _p1 <= p2 - p1) {
                    clearInterval(t);
                    var timer = setInterval(function () {
                        if(y > canvas.height) {
                            clearInterval(timer);
                            callback && callback();
                        }
                        clearRect(y, speed2, type);
                        y += speed2;
                        speed2 += 1;
                    }, 10);
                }
                draw(s1, s2, _p1, _p2);
                _p1 += d1;
                _p2 += d2;
                if((d1 < 0 && _p1 <= p1) || (d1 > 0 && _p1 >= p1)) {
                    _p1 = p1;
                }
                if((d2 < 0 && _p2 <= p2) || (d2 > 0 && _p2 >= p2)) {
                    _p2 = p2;
                }
            }, 10);
        }
        else if(type === "zoomin") {
            d1 = (p1 >= s1 && p1 < speed1) ? 0 : p1 < s1 ? speed1 : -speed1;
            d2 = p2 < s2 ? speed1 * dist2 / dist1 : -speed1 * dist2 / dist1;
            _p1 = p1;
            _p2 = p2;
            y = canvas.height;
            var timer = setInterval(function () {
                if(y <= 0) {
                    clearInterval(timer);
                    var t = setInterval(function () {
                        if(_p2 - _p1 >= s2 - s1) {
                            clearInterval(t);
                            callback && callback();
                        }
                        draw(s1, s2, _p1, _p2);
                        _p1 += d1;
                        _p2 += d2;
                    }, 10);
                }
                draw(s1, s2, _p1, _p2);
                clearRect(y, speed2, type);
                y -= speed2;
                speed2 += 1;
            }, 10);
        }
    }
}

