/*
用户自己定义的js
 */
$("#queryUser").on("click",function () {
    var page = $("#queryUser").val();
    var url = "queryUser?page="+page;
    clearDiv();
    $.get(url, function (data) {
        var list = data.data.data;
        var pageNum = data.data.page;
        var pageSize = data.data.pageSize;
        changeUserPage((pageNum-1),pageSize);
    });
});

function changeUserPage(pageNum,pageSize){
    if (pageNum <= 0){
        pageNum = "";
    }
    if (pageNum >= pageSize){
        pageNum = pageSize-1;
    }
    clearDiv();
    var url = "queryUser?page="+pageNum;
    $.get(url,function (data) {
        var list = data.data.data;
        var html = "<div id='light'>" +
            "<nav aria-label=\"...\">\n" +
            "  <ul class=\"pager\">\n" +
            "    <li class=\"previous\"><input id='previousPage' type='button' onclick=\"changeUserPage("+ ((pageNum-1),pageSize) +")\" value='上一页' /> </li>\n" +
            "    <li class=\"next\"><input id='nextPage' type='button' onclick=\"changeUserPage("+(pageNum+1)+")\" value='下一页' /> </li>\n" +
            "  </ul>\n" +
            "</nav>" +
            "<table class=\"table\">\n" +
            "            <tr>\n" +
            "                <th>用户名</th>\n" +
            "                <th>密码</th>\n" +
            "                <th>用户姓名</th>\n" +
            "                <th>性别</th>\n" +
            "                <th>年龄</th>\n" +
            "                <th>联系电话</th>\n" +
            "                <th>邮箱</th>\n" +
            "                <th>身份</th>\n" +
            "                <th>操作</th>\n" +
            "            </tr>";
        for(var i=0;i<list.length;i++){
            var tmp = list[i];
            html += parseHtml(tmp);
        }
        html += "</table></div>";
        $("#main").append(html);
    });
}

function parseHtml(user) {
    var html = "<tr>\n" +
        "                <th>" + user.username+ "</th>\n" +
        "                <th>" + user.password + "</th>\n" +
        "                <th>" + user.name + "</th>\n" +
        "                <th>" + user.sex + "</th>\n" +
        "                <th>" + user.age + "</th>\n" +
        "                <th>" + user.phones + "</th>\n" +
        "                <th>" + user.email + "</th>\n" +
        "                <th>" + user.identity + "</th>\n" +
        "                <th><a href=\"update_user?userId="+ user.userId + "\">修改</a><a href=\"insertMessage?userId=" + user.userId + "\">删除</a></th>\n" +
        "            </tr>";
    return html;
}

$("#queryToAudit").on("click",function () {
    var url = "queryToAudit";
    clearDiv();
    $.get(url,function (data) {
        if (data.code == 0){
            var list = data.data;
            var html = "<div id='light'><table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>编号</th>\n" +
                "                <th>学生姓名</th>\n" +
                "                <th>主修专业</th>\n" +
                "                <th>辅修专业</th>\n" +
                "                <th>审核</th>\n" +
                "            </tr>";
            for(var i = 0;i < list.length;i ++){
                var tmp = list[i];
                html += convert(tmp);
            }
            html += "</table></div>";
            $("#main").append(html);
        }else{
            alert(data.msg);
        }
    });
});

function convert(entryForm) {
    var html = "<tr>\n" +
        "                <th>" + entryForm.entryFormId + "</th>\n" +
        "                <th>" + entryForm.userName + "</th>\n" +
        "                <th>" + entryForm.majorName + "</th>\n" +
        "                <th>" + entryForm.minorName + "</th>\n" +
        "                <th><a href=\"/admin/checkApply?entryFormId=" + entryForm.entryFormId + "\"><button class=\"btn btn-info\">审核</button></a></th>\n" +
        "            </tr>"
    return html;
}

$("#queryApplyUser").on("click",function () {
    var url = "queryApplyUser";
    clearDiv();
    $.get(url,function (data) {
        if (data.code == 0){
            var list = data.data;
            var html =  "<div id='light'><table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>姓名</th>\n" +
                "                <th>主修专业</th>\n" +
                "                <th>主修学科</th>\n" +
                "                <th>辅修专业</th>\n" +
                "                <th>平均成绩</th>\n" +
                "                <th>身份证</th>\n" +
                "                <th>状态</th>\n" +
                "                <th>申请原因</th>\n" +
                "            </tr>";
            for(var i = 0;i < list.length;i ++){
                var tmp = list[i];
                html += show(tmp);
            }
            html += "</table></div>";
            $("#main").append(html);
        } else{
            alert(data.msg);
        }
    });
});

function show(user) {
    var check = "未审核";
    if (user.checked == 0){
        check = "已审核";
    }
    var html = "<tr>\n" +
        "                <th>" + user.name + "</th>\n" +
        "                <th>" + user.majorName + "</th>\n" +
        "                <th>" + user.majorCourse + "</th>\n" +
        "                <th>" + user.minorName + "</th>\n" +
        "                <th>" + user.averageScore + "</th>\n" +
        "                <th>" + user.cardId + "</th>\n" +
        "                <th>" + check + "</th>\n" +
        "                <th>" + user.interestCourse + "</th>\n" +
        "            </tr>";
    return html;
}

$("#queryStudent").on("click",function () {
    var page = $("#queryStudent").val();
    var url = "queryStudent?page=" + page;
    clearDiv();
    $.get(url,function (data) {
        var pageNum = data.count;
        changeStudentPage(pageNum);
    });
});

function changeStudentPage(pageNum) {
    var url = "queryStudent?page=" + pageNum;
    clearDiv();
    $.get(url,function (data) {
        if (data.code == 0){
            var list = data.data;
            var html =  "<div id='light'>" +
                "<nav aria-label=\"...\">\n" +
                "  <ul class=\"pager\">\n" +
                "    <li class=\"previous\"><input id='previousPage' type='button' onclick=\"changeStudentPage("+(pageNum-1)+")\" value='上一页' /> </li>\n" +
                "    <li class=\"next\"><input id='nextPage' type='button' onclick=\"changeStudentPage("+(pageNum+1)+")\" value='下一页' /> </li>\n" +
                "  </ul>\n" +
                "</nav>" +
                "<table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>姓名</th>\n" +
                "                <th>班级</th>\n" +
                "                <th>主修专业</th>\n" +
                "                <th>学院</th>\n" +
                "                <th>操作</th>\n" +
                "            </tr>";
            for(var i = 0;i < list.length;i ++){
                var tmp = list[i];
                html += showStudent(tmp);
            }
            html += "</table></div>";
            $("#main").append(html);
        } else{
            alert(data.msg);
        }
    });
}

function showStudent(student){
    var html = "<tr>\n" +
        "                <th>" + student.userName + "</th>\n" +
        "                <th>" + student.classes + "</th>\n" +
        "                <th>" + student.majorName + "</th>\n" +
        "                <th>" + student.academyName + "</th>\n" +
        "                <th><a href=\"selectStudent?studentId=" + student.studentId + "\">修改</a><a href=\"deleteStudent?studentId=" + student.studentId + "\">删除</a></th>\n" +
        "            </tr>";
    return html;
}

$("#queryTeacher").on("click",function () {
    var url = "queryTeacher";
    clearDiv();
    $.get(url,function (data) {
        if (data.code == 0){
            var list = data.data;
            var html =  "<div id='light'><table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>姓名</th>\n" +
                "                <th>学院</th>\n" +
                "                <th>介绍</th>\n" +
                "                <th>操作</th>\n" +
                "            </tr>";
            for(var i = 0;i < list.length;i ++){
                var tmp = list[i];
                html += showTeacher(tmp);
            }
            html += "</table></div>";
            $("#main").append(html);
        } else{
            alert(data.msg);
        }
    });
});

function showTeacher(teacher) {
    var html = "<tr>\n" +
        "                <th>" + teacher.userName + "</th>\n" +
        "                <th>" + teacher.academyName + "</th>\n" +
        "                <th>" + teacher.professional + "</th>\n" +
        "                <th><a href=\"selectTeacher?teacherId=" + teacher.teacherId + "\">修改</a><a href=\"deleteTeacher?teacherId="+ teacher.teacherId + "\">删除</a></th>\n" +
        "            </tr>";
    return html;
}

$("#queryInformation").on("click",function () {
    clearDiv();
    var html = "<div id=\"light\" class=\"row\" style=\"height: 400px;width: 400px\">\n" +
        "            <button id=\"pie\" class=\"btn btn-success\">饼图</button>\n" +
        "            <button id=\"histogram\" class=\"btn btn-success\">柱状图</button>\n" +
        "        </div>";
    $("#main").append(html);
});

function clearDiv(){
    var i = 0;
    $("#light").each(function () {
        if(i >= 0) {
            $(this).remove();
        }
        i++;
    });
    $("#light1").each(function () {
        if(i >= 0) {
            $(this).remove();
        }
        i++;
    });
}

$("#pie").on("click",function () {
    clearDiv();
    addDiv();
    var minorId = $("#1").val();
    var myChart = echarts.init(document.getElementById('light'));
    pie(minorId,myChart);
    var falg = true;
    myChart.on('click',function(params){
        if (falg){
            histogram(minorId,params.name);
            falg = false;
        } else{
            pie(minorId,myChart);
            falg = true;
        }
    });
});

function addDiv() {
    var html = "<div id=\"light\" class=\"col-sm-5\" style=\"height: 450px;\">" +
        "</div><div id=\"light1\" class=\"col-sm-5\" style=\"height: 450px;\"></div>";
    $("#main").append(html);
}

function pie(minorId,myChart) {
    var url = "pie?minorId=" + minorId;
    $.get(url, function (data) {
        var option = {
            title: {
                text: '学院报名比例',
                left: 'center',
                textStyle: {
                    color: "#000",
                    fontSize: 15,
                    fontWeight: 'normal'
                },
                subtext: '',
                subtextStyle: {
                    color: "#aaa",
                    fontSize: 12,
                    fontWeight: 'normal'
                },
                itemGap: 300
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b} :<br>{c}<br> {d}%"
            },
            legend: {
                bottom: 10,
                data: [],
                itemGap: 10
            },
            color: ['#809df2', '#72f7ff', '#ed8238', '#a5f264', '#ed4aeb', '#eddb14'],
            series: [
                {
                    name: '学院报名比例',
                    type: 'pie',    // 设置图表类型为饼图
                    radius: '55%', // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
                    center: ['40%', '50%'],
                    data: data.data,
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
    });
}

function histogram(minorId,name) {
    var myChart = echarts.init(document.getElementById('light1'));
    var url = "histogram?minorId=" + minorId + "&academyName=" + name;
    $.post(url,function (data) {
        var option = {
            legend: {},
            tooltip: {},
            xAxis: {
                type: 'category',
                data: data.data.dataX,
                axisLabel: {
                    inside: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                z: 15
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: data.data.dataY,
                type: 'bar',
                showBackground: true,
            }]
        };
        myChart.setOption(option);
    });
}

function queryMinor(){
    var url = "queryMinor";
    clearDiv();
    $.get(url,function (data) {
        if (data.code == 0){
            var list = data.data;
            var html = "<div id=\"light\">\n" +
                "        <table class=\"table\">\n" +
                "            <tr>\n" +
                "                <td>课程编号</td>\n" +
                "                <td>辅修专业</td>\n" +
                "                <td>开设学院</td>\n" +
                "                <td>报名人数</td>\n" +
                "                <td>操作</td>\n" +
                "            </tr>"
            for (var i = 0;i < list.length;i ++){
                var minor = list[i];
                html += showMinor(minor);
            }
            html += "</table>" +
                "</div>";
            $("#main").append(html)
        } else {
            alert(data.msg);
        }
    });
}

function showMinor(minor){
    var html = "<tr>\n" +
        "                <td>" + minor.minorId + "</td>\n" +
        "                <td>" + minor.minorName + "</td>\n" +
        "                <td>" + minor.academy + "</td>\n" +
        "                <td>" + minor.count + "</td>\n" +
        "                <td><a href=\"entryFrom?minorId=" + minor.minorId + "\">去报名</a></td>\n" +
        "            </tr>";
    return html;
}

$("#queryTeacherCourse").on("click",function () {
    var url = "queryTeacherCourse";
    clearDiv();
    $.get(url,function (data) {
        if (data.code == 0){
            var list = data.data;
            var html = "<div id=\"light\"><table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>课程名称</th>\n" +
                "                <th>上课时间</th>\n" +
                "            </tr>";
            for (var i = 0;i < list.length;i ++){
                var tmp = list[i];
                html += "            <tr>\n" +
                    "                <th>" + tmp.courseName + "</th>\n" +
                    "                <th>" + tmp.timeTable + "</th>\n" +
                    "            </tr>";
            }
            html += "</table></div>";
            $("#main").append(html)
        } else {
            alert(data.msg);
        }
    });
});

$("#query_about_course").on("click",function () {
    var minorId = $("#query_about_course").val();
    var url = "queryAboutCourse";
    clearDiv();
    $.ajax({
        type : "post",
        url : url,
        data: {"minorId":minorId},
        success:function(data){
            if (data.code == 0){
                var list = data.data;
                var html = "<div id=\"light\"><table class=\"table\">\n" +
                    "            <tr>\n" +
                    "                <th>课程名称</th>\n" +
                    "                <th>上课时间</th>\n" +
                    "                <th>讲课老师</th>\n" +
                    "            </tr>";
                for (var i = 0;i < list.length;i ++){
                    var tmp = list[i];
                    html += "            <tr>\n" +
                        "                <th>" + tmp.courseName + "</th>\n" +
                        "                <th>" + tmp.timeTable + "</th>\n" +
                        "                <th>" + tmp.teacherName + "</th>\n" +
                        "            </tr>";
                }
                html += "</table></div>";
                $("#main").append(html);
            } else{
                alert(data.msg);
            }
        }
    });
});

$("#board").on("click",function () {
    var url = "board";
    clearDiv();
    $.ajax({
        type : "post",
        url : url,
        success:function(data){
            if (data.code == 0){
                var list = data.data;
                var html = "<div id=\"light\">\n" +
                    "                <ul class=\"nav nav-pills\">\n" +
                    "                    <li role=\"presentation\" class=\"active\"><a href=\"#\">全部通告</a></li>\n" +
                    "                    <li role=\"presentation\" class=\"active\"><a href=\"#\">和我相关</a></li>\n" +
                    "                </ul>";
                for (var i = 0;i < list.length;i ++){
                    var tmp = list[i];
                    html += "<div class=\"\">\n" +
                        "    <h4><a href=\"#\">"+ tmp.title + "</a></h4>\n" +
                        "    <p>"+ tmp.callBoard + "<br>" + tmp.time + "</p>\n" +
                        "</div>";
                }
                html += "            </div>";
                $("#main").append(html);
            } else{
                alert(data.msg);
            }
        }
    });
});

$("#queryMyStudent").on("click",function () {
    clearDiv();
    var url = "queryMyStudent";
    $.ajax({
        type:'post',
        url:url,
        success:function (data) {
            if (data.code == 0){
                var html = " <div id=\"light\">\n" +
                    "            <table class=\"table\">\n" +
                    "                <tr>\n" +
                    "                    <th>学号</th>\n" +
                    "                    <th>姓名</th>\n" +
                    "                    <th>辅修学位</th>\n" +
                    "                </tr>";
                var list = data.data;
                for(var i = 0;i < list.length;i ++){
                    var tmp = list[i];
                    html += "<tr>\n" +
                        "                    <th>" + tmp.username + "</th>\n" +
                        "                    <th>" + tmp.name + "</th>\n" +
                        "                    <th>" + tmp.minorName + "</th>\n" +
                        "                </tr>";
                }
                html += "</table>\n" +
                    "        </div>";
                $("#main").append(html);
            } else{
                alert(data.msg);
            }
        }
    });
});

$("#upload-table").on("click",function () {
    window.location.href = "uploadTable";
});

$("#queryScore").on("click",function(){
    clearDiv();
    var url = "queryScore";
    $.get(url,function (data) {
        if (data.code == 0){
            var html = " <div id=\"light\">\n" +
                "            <table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>学号</th>\n" +
                "                <th>姓名</th>\n" +
                "                <th>辅修学位</th>\n" +
                "                <th>课程</th>\n" +
                "                <th>成绩</th>\n" +
                "            </tr>";
            var list = data.data;
            for(var i = 0;i < list.length;i ++){
                var tmp = list[i];
                html += "            <tr>\n" +
                    "                <th>" + tmp.userName + "</th>\n" +
                    "                <th>" + tmp.name + "</th>\n" +
                    "                <th>" + tmp.minorName + "</th>\n" +
                    "                <th>" + tmp.courseName + "</th>\n" +
                    "                <th>" + tmp.score + "</th>\n" +
                    "            </tr>";
            }
            html += "</table></div>";
            $("#main").append(html);
        } else{
            alert(data.msg);
        }
    });
});

$("#queryMyScore").on("click",function(){
    clearDiv();
    var url = "queryMyScore";
    $.get(url,function (data){
        if (data.code == 0){
            var html = " <div id=\"light\">\n" +
                "            <table class=\"table\">\n" +
                "            <tr>\n" +
                "                <th>学号</th>\n" +
                "                <th>姓名</th>\n" +
                "                <th>辅修学位</th>\n" +
                "                <th>课程</th>\n" +
                "                <th>成绩</th>\n" +
                "            </tr>";
            var list = data.data;
            for(var i = 0;i < list.length;i ++){
                var tmp = list[i];
                html += "            <tr>\n" +
                    "                <th>" + tmp.userName + "</th>\n" +
                    "                <th>" + tmp.name + "</th>\n" +
                    "                <th>" + tmp.minorName + "</th>\n" +
                    "                <th>" + tmp.courseName + "</th>\n" +
                    "                <th>" + tmp.score + "</th>\n" +
                    "            </tr>";
            }
            html += "</table></div>";
            $("#main").append(html);
        } else{
            alert(data.msg);
        }
    });
});