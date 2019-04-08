<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="../data/before.js"></script>
<style type="text/css">
    #a3_form_id input {
        width: 250px;
        height: 30px;
        border-radius: 5px;
    }
</style>

<div style="width: 990px;height: 480px;margin-top: 35px;">
    <%--要查看的选项--%>
    <div style="float: left;width: 260px;height: 370px;margin-top: 52px;">
        <%--基本信息按钮--%>
        <div id="a_div_id_01"
             style="height: 45px;width: 100%;border-radius: 22.5px;background-color: #30B8BE;margin-top: 64px;"
             onmouseover="login_btn_div_over('a_div_id_01')" onmouseout="login_btn_div_out('a_div_id_01')"
             onclick="select_window01('a_div_id_05')" onmousedown="login_btn_down('a_div_id_01')"
             onmouseup="login_btn_up('a_div_id_01')">
            <div style="margin-top: 14.5px;position: absolute;margin-left: 86px;cursor: pointer;"><strong
                    style="color: #FFFFFF;letter-spacing:5px;">基本信息</strong></div>
        </div>
        <%--上传头像按钮--%>
        <%--<div id="a_div_id_02"
             style="height: 45px;width: 100%;border-radius: 22.5px;background-color: #30B8BE;margin-top: 20px;"
             onmouseover="login_btn_div_over('a_div_id_02')" onmouseout="login_btn_div_out('a_div_id_02')"
             onclick="select_window01('a_div_id_12')" onmousedown="login_btn_down('a_div_id_02')"
             onmouseup="login_btn_up('a_div_id_02')">
            <div style="margin-top: 14.5px;position: absolute;margin-left: 86px;cursor: pointer;"><strong
                    style="color: #FFFFFF;letter-spacing:5px;">上传头像</strong></div>
        </div>--%>
        <%--修改密码按钮--%>
        <div id="a_div_id_03"
             style="height: 45px;width: 100%;border-radius: 22.5px;background-color: #30B8BE;margin-top: 20px;"
             onmouseover="login_btn_div_over('a_div_id_03')" onmouseout="login_btn_div_out('a_div_id_03')"
             onclick="select_window01('a_div_id_16')" onmousedown="login_btn_down('a_div_id_03')"
             onmouseup="login_btn_up('a_div_id_03')">
            <div style="margin-top: 14.5px;position: absolute;margin-left: 86px;cursor: pointer;"><strong
                    style="color: #FFFFFF;letter-spacing:5px;">修改密码</strong></div>
        </div>
        <%--清除登录按钮--%>
        <div id="a_div_id_04"
             style="height: 45px;width: 100%;border-radius: 22.5px;background-color: #30B8BE;margin-top: 20px;"
             onmouseover="login_btn_div_over('a_div_id_04')" onmouseout="login_btn_div_out('a_div_id_04')"
             onclick="clear_login()" onmousedown="login_btn_down('a_div_id_04')"
             onmouseup="login_btn_up('a_div_id_04')">
            <div style="margin-top: 14.5px;position: absolute;margin-left: 86px;cursor: pointer;"><strong
                    style="color: #FFFFFF;letter-spacing:5px;">清除登录</strong></div>
        </div>
    </div>
    <!--员工信息具体数据-->
    <div id="a_div_id_05" style="float: right;width: 640px;height: 150px;margin-top: 52px;margin-right: 0px;">
        <table border="1px;" width="640px" height="150px" rules="all" style="border-color: #30B8BE;margin-top: 90px;">
            <tr>
                <td align="center">姓名</td>
                <td colspan="5" align="center"><span
                        style="font-family: 黑体;font-size: 25px;">${sessionScope.employee.employeeName}</span></td>
                <td colspan="2" rowspan="4" align="center">
                    <c:if test="${sessionScope.employee.imageUrl!=null}">
                        <img src="../images/index/my_image/${sessionScope.employee.imageUrl}.png"
                             style="width: 90px;height: 90px;">
                    </c:if>
                    <c:if test="${sessionScope.employee.imageUrl==null}">
                        <img src="../images/index/my_image/dengluyonghutouxiang.png" style="width: 90px;height: 90px;">
                    </c:if>
                </td>
            </tr>
            <tr>
                <td align="center">性别</td>
                <td colspan="2" align="center">${sessionScope.employee.sex}</td>
                <td align="center">工号</td>
                <td colspan="2" align="center">${sessionScope.employee.employeeNumber}</td>
            </tr>
            <tr>
                <td align="center">电话</td>
                <td colspan="2" align="center">${sessionScope.employee.tel}</td>
                <td align="center">是否在职</td>
                <td colspan="2" align="center">${sessionScope.employee.ifOnDuty}</td>
            </tr>
            <tr>
                <td align="center">身份证号码</td>
                <td colspan="2" align="center">${sessionScope.employee.idCard}</td>
                <td align="center">权限等级</td>
                <td colspan="2" align="center">${sessionScope.employee.permissions}</td>
            </tr>
            <tr>
                <td align="center">创建人</td>
                <td colspan="2" align="center">${sessionScope.employee.founder}</td>
                <td align="center">创建时间</td>
                <td colspan="2" align="center"><fmt:formatDate value="${employee.createTime}"
                                                               pattern="yyyy-MM-dd"/></td>
                <td colspan="2" align="center"><a href="#"
                                                  onclick="modify_employee('${sessionScope.employee.employeeNumber}')">修改资料
                    ></a></td>
            </tr>
        </table>
    </div>
    <%--修改员工自己信息面板--%>
    <div id="a_div_id_07"
         style="display:none;width: 1000px;height: 600px;background-color: #ffffff;position: absolute;z-index: 3;top: 40%;left: 35%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
         align="center">
        <div style="width: 150px;margin-top: 5px;margin-left: 20px;float: left;"><h2>修改资料</h2></div>
        <div style="float: right;margin-right: 20px;margin-top: 25px;cursor: pointer;"
             onclick="close_update_panel1('a_div_id_07')" title="关闭窗口"><img id="close_img2"
                                                                            src="../images/index/close1.png"
                                                                            style="width: 16px;height: 16px;"></div>
        <hr width="960">
        <%--员工信息--%>
        <div style="height: 240px;">
            <form id="a_div_id_08" action="">
                <input type="hidden" name="e_id"/>

                <div style="height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">员工编号:</span>
                    <input type="text" name="employeeNumber" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">姓&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
                    <input type="text" name="employeeName" style="height: 35px;width: 250px;margin-left: 10px;">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -370px;font-family: 黑体;font-size: 20px;margin-top: 5px">邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</span>
                    <input type="text" name="email" style="height: 35px;width: 250px;margin-left: 10px;">
                    <span style="margin-left: 20px;font-family: 黑体;font-size: 20px;">性&nbsp;&nbsp;&nbsp;&nbsp;别:</span>
                    <input style="margin-left: 20px;" type="radio" name="sex" value="男">男
                    <input type="radio" name="sex" value="女">女
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">出生年月:</span>
                    <input type="date" name="birth" style="height: 35px;width: 250px;margin-left: 10px;">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">民&nbsp;&nbsp;&nbsp;&nbsp;族:</span>
                    <input type="text" name="nation" style="height: 35px;width: 250px;margin-left: 10px;">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">政治面貌:</span>
                    <input type="text" name="zzmm" style="height: 35px;width: 250px;margin-left: 10px;">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">婚姻状态:</span>
                    <select id="updateMyAccount_marriage_input" name="marriage"
                            style="width: 250px; height: 35px;margin-left: 10px;">
                        <option value="已婚">已婚</option>
                        <option value="未婚">未婚</option>
                        <option value="未知">未知</option>
                    </select>
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">证件号码:</span>
                    <input type="text" name="idCard" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">联系电话:</span>
                    <input type="text" name="tel" style="height: 35px;width: 250px;margin-left: 10px;">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</span>
                    <input type="text" name="root" style="height: 35px;width: 250px;margin-left: 10px;">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">现居住地:</span>
                    <input type="text" name="address" style="height: 35px;width: 250px;margin-left: 10px;">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">入职时间:</span>
                    <input type="date" name="beginTime" style="height: 35px;width: 250px;margin-left: 10px;">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">转正时间:</span>
                    <input type="date" name="becomeTime" style="height: 35px;width: 250px;margin-left: 10px;">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">离职时间:</span>
                    <input type="date" name="leaveTime" style="height: 35px; width: 250px; margin-left: 10px;">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">任职状态:</span>
                    <select id="a_div_id_11" name="ifOnDuty" style="height: 35px;width: 250px;margin-left: 10px;"
                            disabled="disabled">
                        <option value="0">在职</option>
                        <option value="1">离职</option>
                    </select>
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;">权限等级:</span>
                    <select id="a_div_id_10" name="permissions" style="width: 250px; height: 35px;margin-left: 10px;"
                            disabled="disabled">
                        <option value="0">（0）员工</option>
                        <option value="1">（1）超级管理员</option>
                        <option value="2">（2）经理</option>
                    </select>
                    <span style="margin-left: 20px;font-family: 黑体;font-size: 20px;">所属部门:</span>
                    <select id="look_myAccount_input" name="departmentName"
                            style="width: 250px; height: 35px;margin-left: 10px;"disabled="disabled">
                        <option value="职介中心">职介中心</option>
                        <option value="人才中心">人才中心</option>
                        <option value="社保中心">社保中心</option>
                        <option value="劳动仲裁中心">劳动仲裁中心</option>
                        <option value="劳动监察大队">劳动监察大队</option>
                        <option value="人事部">人事部</option>
                    </select>
                </div>
            </form>
            <%--确定修改按钮--%>
            <div id="a_div_id_09"
                 style="float: right;width: 100px;height: 40px;margin-right:230px;margin-top:-10px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                 align="center" onmouseover="login_btn_div_over('a_div_id_09')"
                 onmouseout="login_btn_div_out('a_div_id_09')" onclick="determine_modify_employee('a_div_id_08')"
                 onmousedown="login_btn_down('a_div_id_09')" onmouseup="login_btn_up('a_div_id_09')">
                <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong></div>
            </div>
        </div>
    </div>
    <%--上传头像面板--%>
    <div id="a_div_id_12"
         style="display:none;float: left;width: 240px;height: 250px;margin-top: 118px;margin-left: 250px;border-style: dotted;border-width: 1px;"
         align="center">
        <form id="a_div_id_15" action="/employee/uploadMyImage" method="post" enctype="multipart/form-data">
            <%--头像预览--%>
            <c:if test="${sessionScope.employee.imageUrl!=null}">
                <img id="a_div_id_14" src="../images/index/my_image/${sessionScope.employee.imageUrl}.png"
                     style="width: 146px;height: 146px;margin-top: 30px;">
            </c:if>
            <c:if test="${sessionScope.employee.imageUrl==null}">
                <img id="a_div_id_14" src="../images/index/my_image/dengluyonghutouxiang.png"
                     style="width: 146px;height: 146px;margin-top: 30px;">
            </c:if>
            <input id="a1_form_input" type="file" name="myImage" onchange="imgPreview(this)"
                   style="cursor: pointer;opacity:0;width: 146px;height: 146px;position: absolute;margin-top: 30px;margin-left: -162px;">
        </form>
        <%--上传按钮--%>
        <div id="a_div_id_13"
             style="width: 146px;height: 36px;background-color: #30B8BE;margin-top: 15px;border-radius: 6px;cursor: pointer;"
             onmouseover="login_btn_div_over('a_div_id_13')" onmouseout="login_btn_div_out('a_div_id_13')"
             onclick="upload()" onmousedown="login_btn_down('a_div_id_13')" onmouseup="login_btn_up('a_div_id_13')">
            <span style="font-family: 黑体;font-size: 20px;color: #FFFFFF;float: left;margin-left: 43px;margin-top: 7px;">上&nbsp;&nbsp;传</span>
        </div>
    </div>
    <%--修改密码面板--%>
    <div id="a_div_id_16"
         style="display:none;float: left;width: 542px;height: 268px;margin-top: 105px;margin-left: 160px;border-style: dotted;border-width: 1px;border-radius: 10px;"
         align="center">
        <%--标题--%>
        <div style="width: 540px;height: 46px;">
            <span style="font-family: 黑体;font-size: 30px;float: left;margin-left: 30px;margin-top: 10px;">修改密码</span>
        </div>
        <hr>
        <%--修改表单--%>
        <div style="margin-top: 10px;">
            <form id="a3_form_id" action="/employee/modifyPassword" method="post">
                当前密码<sup style="color: red;">*</sup>&nbsp;<input type="password" name="oldPassword"/><br/><br/>
                新密码<sup style="color: red;">*</sup> &nbsp;&nbsp;<input type="password" name="newPassword"/><br/><br/>
                确认密码<sup style="color: red;">*</sup>&nbsp;<input type="password" name="againPassword"/>
            </form>
        </div>
        <hr style="margin-top: 15px;">
        <%--确认修改密码按钮--%>
        <div id="a_div_id_18"
             style="width: 146px;height: 30px;background-color: #30B8BE;margin-top: 13px;border-radius: 6px;cursor: pointer;"
             onmouseover="login_btn_div_over('a_div_id_18')" onmouseout="login_btn_div_out('a_div_id_18')"
             onclick="determine_modify_password('a3_form_id')" onmousedown="login_btn_down('a_div_id_18')"
             onmouseup="login_btn_up('a_div_id_18')">
            <span style="font-family: 黑体;font-size: 17px;color: #FFFFFF;float: left;margin-left: 43px;margin-top: 5px;">修&nbsp;&nbsp;改</span>
        </div>
    </div>
</div>
