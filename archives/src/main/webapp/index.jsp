<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>七里河区人社局人事档案管理系统</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

    <script src="../data/jquery.js"></script>
    <script src="../data/ajaxTemp.js"></script>
    <script src="../data/before.js"></script>

    <style type="text/css">
        html,body{
            height:100%;
        }
        img{
            width: 100%;
            height: 100%;
        }

        tr{
            height: 53px;
        }
        td,th{
            width: 120px;
        }
        tr{
            font-family: 黑体;
        }
        #a_div_id_05 tr {
            height: 40px;
        }
        #a_div_id_05 td{
            width: 80px;
        }
        th{
            color: #000000;
        }
        a:link{
            text-decoration: none;
        }
    </style>
</head>
<body style="margin:0px;background-color:#D7D7D7;overflow-y:hidden;">
<%--最上层div  用来等待加载数据 --%>
<div id="top_div_1" style="display: none; text-align: center; width: 100%;height: 100%;background-color: rgba(95,95,95,0.5);z-index: 2;position: absolute;">
    <div style="position: absolute;width: 142.7px;height: 10px;top: 50%;left: 50%;margin-left: -71.3px;margin-top: -5px;" align="center"><img src="../images/index/loading.gif"></div>
</div>
<%--灰色背景--%>
<div id="top_div_2" style="display: none;width: 100%;height: 100%;background-color: rgba(95,95,95,0.4);z-index: 2;position: absolute;">
</div>
<!--最外层div-->
<div>
    <!-- 最头部导航条div -->
    <div style="height: 100px;width: 100%;background-color: #30B8BE;position:absolute;">
        <!-- 左侧div -->
        <div>
            <!-- logo -->
            <div style="margin-top:21px;margin-left:11px;float:left;">
                <img src="../images/index/archive.png" style="width:58px;height:58px;border-radius:29px;">
            </div>

            <!-- 系统名称 -->
            <div style="float:left;margin-left:20px;margin-top:30px;">
                <a href="/page/index">
                    <span style="font-family:'黑体';font-weight: 400;font-style: normal;font-size: 36px;color: #FFFFFF;text-align: center;"> 七里河区人社局人事档案管理系统</span>
                </a>
            </div>


            <!--小竖线-->
            <img src="../images/index/xiaoshuxian.png" style="margin-top:36.5px;float:left;width: auto;height: auto;margin-left: 20px;">
            <!-- 隐藏功能栏的图标 -->
            <div id="yincang_div_id" title="隐藏或显示左侧功能栏" style="float:left;width: 26px;height: 22px;margin-left: 20px;margin-top: 36.5px;overflow:-Scroll;" onmouseover="onmouseover_yincang_div('yincang_div_id')" onmouseout="onmouseout_yincang_div('yincang_div_id')" onclick="click_yincang_div('leftDiv')">
                <img src="../images/index/yccd.png" style="width: 100%;height: 100%;"></div>
        </div>
        <!-- 右侧div -->
        <div style="width:540px;height:90%;float:right;">
            <!--小竖线-->
            <img src="../images/index/xiaoshuxian.png" style="margin-top:36.5px;float:left;width: auto;height: auto;">
            <!-- 用户基本信息 -->
            <div style="float:left;">
                <!-- 登录用户头像 -->
                <div id="headPortrait_div_id" style="width:50px;height:50px;float: left;margin-left:28px;margin-top:25px;" onmouseover="headPortrait_in('headPortrait_div_id')" onmouseout="headPortrait_out('headPortrait_div_id')">
                    <c:if test="${sessionScope.employee.imageUrl!=null}">
                        <img id="myImage1" src="../images/index/my_image/${sessionScope.employee.imageUrl}.png" style="width:100%;height:100%;border-radius:25px;" title="当前登录用户">
                    </c:if>
                    <c:if test="${sessionScope.employee.imageUrl==null}">
                        <img id="myImage1" src="../images/index/my_image/dengluyonghutouxiang.png" style="width:100%;height:100%;border-radius:25px;" title="当前登录用户">
                    </c:if>
                </div>
                <!-- 工号 -->
                <div style="width:85px;float:left;line-height:8px;font-family: 'Arial Negreta', 'Arial Normal','Arial';font-weight: 700;font-style: normal;color: #FFFFFF;margin-left:20px;margin-top:22px;font-size: 15px;">
                    <p align="center">工号</p>
                    <p align="center">${sessionScope.employee.employeeNumber }</p>
                </div>
            </div>
            <!-- 小竖线 -->
            <img src="../images/index/xiaoshuxian.png" style="margin-top:36.5px;float:left;margin-left:28px;width: auto;height: auto;">
            <!-- 系统时间 -->
            <div style="float:left;line-height:8px;font-family: 'Arial Negreta', 'Arial Normal','Arial';font-weight: 700;font-style: normal;color: #FFFFFF;margin-left:28px;margin-top:22px;font-size: 15px;">
                <p align="center">当前时间</p>
                <p align="center"><span id="showNowTime" ></span></p>
            </div>
            <!-- 小竖线 -->
            <img src="../images/index/xiaoshuxian.png" style="margin-top:36.5px;float:left;margin-left:28px;width: auto;height: auto;">
            <!-- 退出按钮 -->
            <div id="out_img_div" onmouseover="out_img_1()" onmouseout="out_img_style()" style="float:left;margin-left:20px;margin-top:41px;width:23px;height:19px;" title="退出系统" onclick="out_System()">
                <img src="../images/index/tuichuxitongtubiao.png" style="width:100%;height:100%;">
            </div>
        </div>
    </div>
    <%--页面body--%>
    <div style="margin-top: 0px;height: 95%" align="center">
        <%--左侧功能栏--%>
        <div align="center" id="leftDiv" style="text-align: center; background-color:#EEF1F6 ;width: 15%;height: 100%;margin-top: 100px;overflow:-Scroll;margin-left: 0px;float: left;overflow:auto;">
            <div id="u01_div_1" style="width: 100%;height: 65px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_1')" onmouseout="leftOnMouseOut('u01_div_1')"  onclick="openShutManager(this,'div_down_1')"><span id="div_span_1" style="font-size: 20px;display: block; position: absolute; top: 16%;left:3%; transform:translateY(-50%);"><p style="display: inline-block">员工列表</p><img id= "div_img_1" src="../images/index/arrows.png" style="height: 20px; width: 20px; display: inline-block;position: absolute;left: 150px;top:24px"></span></div>
            <div id="div_down_1"  style="display: none; background-color: #E4E8F1">
                <div id="u01_div_6" style="width: 100%;height: 55px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_6')" onmouseout="leftOnMouseOut('u01_div_6')" onclick="selectWindow('div_span_6')"><span id="div_span_6" style="font-size: 18px;display: block; position: relative; top: 50%;left:-13%; transform:translateY(-50%);"><p style="display: inline-block">证书信息</p></span></div>
                <div id="u01_div_7" style="width: 100%;height: 55px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_7')" onmouseout="leftOnMouseOut('u01_div_7')" onclick="selectWindow('div_span_7')"><span id="div_span_7" style="font-size: 18px;display: block; position: relative; top: 50%;left:-13%; transform:translateY(-50%);"><p style="display: inline-block">职称信息</p></span></div>
<%--
                <div id="u01_div_9" style="width: 100%;height: 55px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_9')" onmouseout="leftOnMouseOut('u01_div_9')" onclick="selectWindow('div_span_9')"><span id="div_span_9" style="font-size: 18px;display: block; position: relative; top: 50%;left:-13%; transform:translateY(-50%);"><p style="display: inline-block">培训信息</p></span></div>
--%>
                <div id="u01_div_10" style="width: 100%;height: 55px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_10')" onmouseout="leftOnMouseOut('u01_div_10')" onclick="selectWindow('div_span_10')"><span id="div_span_10" style="font-size: 18px;display: block; position: relative; top: 50%;left:-13%; transform:translateY(-50%);"><p style="display: inline-block">学历信息</p></span></div>
                <div id="u01_div_11" style="width: 100%;height: 55px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_11')" onmouseout="leftOnMouseOut('u01_div_11')" onclick="selectWindow('div_span_11')"><span id="div_span_11" style="font-size: 18px;display: block; position: relative; top: 50%;left:-13%; transform:translateY(-50%);"><p style="display: inline-block">社会关系</p></span></div>
                <div id="u01_div_12" style="width: 100%;height: 55px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_12')" onmouseout="leftOnMouseOut('u01_div_12')" onclick="selectWindow('div_span_12')"><span id="div_span_12" style="font-size: 18px;display: block; position: relative; top: 50%;left:-13%; transform:translateY(-50%);"><p style="display: inline-block">工作经历</p></span></div>
                <div id="u01_div_13" style="width: 100%;height: 55px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_13')" onmouseout="leftOnMouseOut('u01_div_13')" onclick="selectWindow('div_span_13')"><span id="div_span_13" style="font-size: 18px;display: block; position: relative; top: 50%;left:-13%; transform:translateY(-50%);"><p style="display: inline-block">奖惩信息</p></span></div>
            </div>
            <c:if test="${sessionScope.employee.permissions > 1}"></c:if>
            <div id="u01_div_2" style="width: 100%;height: 65px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_2')" onmouseout="leftOnMouseOut('u01_div_2')" onclick="selectWindow('div_span_2')"><span id="div_span_2" style="font-size: 20px;display: block; position: relative; top: 50%;left:-14%; transform:translateY(-50%);"><p style="display: inline-block">部门信息</p></span></div>
            <div id="u01_div_3" style="width: 100%;height: 65px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_3')" onmouseout="leftOnMouseOut('u01_div_3')" onclick="selectWindow('div_span_3')"><span id="div_span_3" style="font-size: 20px;display: block; position: relative; top: 50%;left:-14%; transform:translateY(-50%);"><p style="display: inline-block">培训管理</p></span></div>
            <div id="u01_div_4" style="width: 100%;height: 65px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_4')" onmouseout="leftOnMouseOut('u01_div_4')" onclick="selectWindow('div_span_4')"><span id="div_span_4" style="font-size: 20px;display: block; position: relative; top: 50%;left:-14%; transform:translateY(-50%);"><p style="display: inline-block">我的账号</p></span></div>
            <div id="u01_div_5" style="width: 100%;height: 65px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_5')" onmouseout="leftOnMouseOut('u01_div_5')" onclick="selectWindow('div_span_5')"><span id="div_span_5" style="font-size: 20px;display: block; position: relative; top: 50%;left:-14%; transform:translateY(-50%);"><p style="display: inline-block">公告管理</p></span></div>
<%--
            <div id="u01_div_8" style="width: 100%;height: 65px;vertical-align: middle" onmouseover="leftOnMouseOver('u01_div_8')" onmouseout="leftOnMouseOut('u01_div_8')" onclick="selectWindow('div_span_8')"><span id="div_span_8" style="font-size: 20px;display: block; position: relative; top: 50%;left:-14%; transform:translateY(-50%);"><p style="display: inline-block">详细信息</p></span></div>
--%>
        </div>
        <%--右侧操作面板--%>
        <div id="rightDiv" style="background-color: #ffffff;width: 84.7%;height: 100%;margin-top: 80px;margin-left: 2px;overflow:-Scroll;margin-right:1px;float:left;overflow-y:hidden;overflow-x:hidden;" align="center">

                            <div><div><div><div><div><div><div><div><div>
            <%--欢迎页面--%>
            <div id="window_div_0" style="width: 95%;height: 520px;margin-top: 0px;"align="center" >
                <%--名言警句--%>
                <div style="float: left;margin-top: 10%;margin-left: 20%;width: 500px;height: 400px;">
                    <%--标题--%>
<%--
                    <div style="float: left;margin-top: 0px;clear: both;width: 460px;"><img src="../images/index/meirijitang.png" style="width: auto;height: auto;float: left;"></div><br>
--%>
                    <%--名句--%>
                    <div id="famous_sentence" style="line-height: 50px;margin-top: 80px;font-size: 25px;font-family: 'Adobe 黑体 Std R';letter-spacing: 3px;border-style: dotted;border-color: rgba(0,0,0,0.98);border-width: 1px;height: 300px;">
                        <p style="float: left;margin-top: 5px;width: 450px;"><span style="font-style: normal;font-size: 50px;font-family: 楷体;font-weight: 900;">多做</span>一点点，</p>
                        <p style="float: right;margin-right: 20px;"><span style="font-style: normal;font-size: 50px;font-family: 楷体;font-weight: 900;">细心</span>一点点，</p>
                        <p style="float: left;"><span style="font-style: normal;font-size: 50px;font-family: 楷体;font-weight: 900;">努力</span>一点点，</p>
                        <p style="float: right;margin-right: 23px;">事业成功<span style="font-style: normal;font-size: 50px;font-family: 楷体;font-weight: 900;">新起点！</span></p>
                    </div>
                </div>
            </div>
            <%--员工列表--%>
            <div id="window_div_1" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="page_div"><%@include file="employeeInfo.jsp" %></div>
            </div>
            <%--部门信息--%>
            <div id="window_div_2" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="d_div_id_19"><%@include file="department.jsp" %></div>
            </div>
            <%--培训管理--%>
            <div id="window_div_3" style="width: 95%;display: none;overflow-x:hidden;" >
                <div id="f_div_id_19"><%@include file="cultivate.jsp" %></div>
            </div>
            <%--我的账号--%>
            <div id="window_div_4" style="width: 95%;display: none;overflow-x:hidden;" >
                <div id="a_div_id_06"><%@include file="myAccount.jsp" %></div>
            </div>
            <%--公告管理--%>
            <div id="window_div_5" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="a_div_id_19"><%@include file="bulletin.jsp" %></div>
            </div>
            <%--证书信息--%>
            <div id="window_div_6" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="g_div_id_19"><%@include file="certificate.jsp" %></div>
            </div>
            <%--职称信息--%>
            <div id="window_div_7" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="t_div_id_19"><%@include file="title.jsp" %></div>
            </div>
            <%--&lt;%&ndash;详细信息&ndash;%&gt;
            <div id="window_div_8" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="z_div_id_19"><%@include file="information.jsp" %></div>
            </div>--%>
            <%--培训信息--%>
            <%--<div id="window_div_9" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="ff_div_id_19"><%@include file="cultivate.jsp" %></div>
            </div>--%>
            <%--学历信息--%>
            <div id="window_div_10" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="e_div_id_19"><%@include file="education.jsp" %></div>
            </div>
            <%--社会关系--%>
            <div id="window_div_11" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="h_div_id_19"><%@include file="community.jsp" %></div>
            </div>
            <%--工作经历--%>
            <div id="window_div_12" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="i_div_id_19"><%@include file="experience.jsp" %></div>
            </div>
            <%--奖惩信息--%>
            <div id="window_div_13" style="width: 95%;display: none;overflow-x:hidden;">
                <div id="c_div_id_19"><%@include file="awardAndPunish.jsp" %></div>
            </div>
                            </div></div></div></div></div></div></div></div></div>


        </div>
    </div>
</div>
<script src="../data/after.js"></script>

</body>
</html>

