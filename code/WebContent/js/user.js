//折叠菜单效果
$(function () {
    var Accordion = function (el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;

        var links = this.el.find('.link');

        links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
    }

    Accordion.prototype.dropdown = function (e) {
        var $el = e.data.el;
        $this = $(this),
            $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');

        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        }
        ;
    }

    var accordion = new Accordion($('#accordion'), false);
});

/*-------------------课程操作----------------------------*/

//查询课程平均分信息
function course_avg() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = "/StudentManagement/AdminDao?action=course_avg";
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
//查询课程不及格率信息
function fail_rate() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var url = "/StudentManagement/AdminDao?action=fail_rate";
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
//显示查询成绩排名信息
function show_course_ranking() {
    var result = document.getElementById("result");
    var show = "<div id='course_ranking'  class='d_form'>"
        + "<h3>请输入课程编号</h3>"
        + "<input id='course_ranking_value' type='text' autofocus='autofocus' name='cno' value placeholder='课程号'>"
        + "<input id='submit' onclick='course_ranking()' type='button' name='submit' value='查询'>"
        + "</div>";
    result.innerHTML = show;
}
//查询成绩排名信息
function course_ranking() {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    var Cno = document.getElementById("course_ranking_value").value;
    var url = "/StudentManagement/AdminDao?action=course_ranking&cno=" + Cno;

    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

/*-------------------------全部信息-------------------------*/

function query_all(object) {
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            document.getElementById("result").innerHTML = xmlhttp.responseText;
        }
    }
    xmlhttp.open("GET", "/StudentManagement/AdminDao?action=query_all_" + object, true);
    xmlhttp.send();
}
