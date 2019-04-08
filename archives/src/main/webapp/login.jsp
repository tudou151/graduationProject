<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <script src="../data/jquery.js"></script>
    <script src="../data/before.js"></script>
    <script type="text/javascript">

    </script>
    <style type="text/css">
        body{
            background:url("/images/login/bg.jpg");
            background-size:100%;
            margin: 0px;
        }
        input{
            border:none;
            width:238px;
            height:40px;
            font-size: 15px;
        }
    </style>
</head>
<body>
<!-- 最外层div -->
<div style="margin:0px;width: 100%;height: 100%;" align="center">
    <!-- 登录面板div -->
    <div style="position:absolute;width: 600px;height:450px;top:50%;left:50%;margin-top: -225px;margin-left: -300px;">
        <!-- 头部面板 -->
        <div style="width: 100%;height: 90px;" >
            <!-- logo -->
            <div style="width:90px;float:left;margin-left: 25px;"><img src="../images/login/logo.png" style="border-radius:45px;"></div>
            <!-- 系统名 -->
            <div style="height:50%;width:400px;float: left;margin-left:10px;"><span style="font-family: '黑体';color: #FFFFFF;font-size: 50px;line-height:90px;">七里河区人社局</span></div>
            <br>
            <div style="height:50%;width:400px;float: left;margin-left:125px;"><span style="font-family: '黑体';color: #FFFFFF;font-size: 50px;line-height:90px;">人事档案管理系统</span></div>
        </div>
        <!-- 下半部分 -->
        <div style="width: 100%;height:360px;margin-top:0px;margin-left:25px;" align="center">
            <div style="width: 284px;height:100%;">
                <div style="height:100px;width:100%;"></div>
                <form id="login_form" action="/employee/login" method="post" >
                    <!-- 工号输入框背景div -->
                    <div style="background-color:#12B3BA;width: 284px;height:42px;margin-top:5px;padding-top:2.5px;">
                        <div style="width:auto;height:auto;float:left;"><img src="../images/login/yonghu.png"></div>
                        <div style="background-color:#FFFFFF;width:240px;height:40px;float:left;">
                            <input type="text" name="employeeNumber" placeholder="工号"/>
                        </div>
                    </div>
                    <!-- 密码输入框背景div -->
                    <div style="background-color:#12B3BA;width: 284px;height:42px;margin-top:30px;padding-top:2.5px;">
                        <div style="width:auto;height:auto;float:left;"><img src="../images/login/mima.png"></div>
                        <div style="background-color:#FFFFFF;width:240px;height:40px;float:left;">
                            <input type="password" name="password" placeholder="密码"/>
                        </div>
                    </div>
                </form>
                <!-- 提示信息 -->
                <div style="width: 284px;height:42px;line-height:42px;">
                    <span id="show_pageMsg_span" style="float:left;font-family:'黑体';font-size:15px;color:red;"><c:if test="${!empty pageMsg }"> ${requestScope.pageMsg}</font></c:if></span>
                </div>
                <!-- 登录按钮div -->
                <div id="login_btn_div1" style="background-color:#12B3BA;width: 284px;height:42px;margin-top:20px;border-radius:6px;line-height:42px;cursor: pointer;" onmouseover="login_btn_div_over('login_btn_div1')" onmouseout="login_btn_div_out('login_btn_div1')" onclick="submit_form('login_form')" onmousedown="login_btn_down('login_btn_div1')" onmouseup="login_btn_up('login_btn_div1')">
                    <span style="font-family:'黑体';color:#FFFFFF;font-size:16px;">登&nbsp;&nbsp;&nbsp;&nbsp;录</span>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
