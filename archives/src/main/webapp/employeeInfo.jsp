<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="../data/before.js"></script>

<%--员工信息展示--%>
<div id="employee_info_div" style="width: 100%;height: 84%;">
    <div style="width: 100%;height: 30px;"></div>
    <%-- 查询--%>
    <div style="height: 60px;background-color: #F2F2F2;text-align: left;">
        <form id="selectEmployee_byemployeeName_input" action="" style="display: inline-block">
            <input style=" border-radius: 10px; outline: none; margin-left: 20px; border: 1px solid #BCBCBC; width: 300px; height: 40px;margin-top: 10px;"
                   type="text" name="employeeName" placeholder="请输入姓名">
        </form>
        <div id="y_div_id_28"
             style="height: 31px; width: 80px; display: inline-block; margin-left: 20px; background-color: #30B8BE; border-radius: 10px; text-align: center; padding:4px;"
             onmouseover="login_btn_div_over('y_div_id_28')" onmouseout="login_btn_div_out('y_div_id_28')"
             onclick="selectEmployee_byEmployeeName('selectEmployee_byemployeeName_input')"
             onmousedown="login_btn_down('y_div_id_28')" onmouseup="login_btn_up('y_div_id_28')">
            <span style="display: inline-block; font-size: 20px;color:white; text-align: center">查询</span>
        </div>
    </div>
    <div style="width: 100%;height: 10px;"></div>
    <table rules="rows" border="0px" width="100%">
        <tr bgcolor="#30B8BE">
            <th><span style="cursor: pointer;" onclick="select_all(${sessionScope.page.lists.size()})">全选</span></th>
            <th>员工编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>电话</th>
            <th>身份证号</th>
            <th>所属部门</th>
            <%--<th>创建人</th>--%>
            <%--<th>创建日期</th>--%>
            <%--<th>是否在职</th>--%>
            <%--<th>离职时间</th>--%>
            <th>权限等级</th>
            <c:if test="${sessionScope.employee.permissions > 1}">
            <th>是否已删除</th>
            </c:if>
        </tr>
        <c:if test="${sessionScope.page==null || sessionScope.page.allPageNum==0}">
            <tr>
                <td colspan="8" style="color: #CDCDCD;" align="center"><h3>当前没有员工信息</h3></td>
            </tr>
        </c:if>
        <c:if test="${sessionScope.page!=null && sessionScope.page.allPageNum>0}">
            <%int i = 1; %>
            <c:forEach var="employee" items="${sessionScope.page.lists }">
                <tr style="border-bottom: 2px solid #C8C8C8">
                    <td align="center"><input id="checkbox_<%=i%><%i++;%>" type="checkbox" name="ids"
                                              value="${employee.id}"/></td>
                        <%--<td align="center">
                            <a href="#" onclick="look_bulletin_content('a_div_id_28','${bulletin.title}','${bulletin.content}','<fmt:formatDate value="${bulletin.startDate}" pattern="yyyy-MM-dd HH:mm:ss" />','<fmt:formatDate value="${bulletin.endDate}" pattern="yyyy-MM-dd HH:mm:ss" />')">查看详情</a></td>
                        --%>
                    <td id="look_td" align="center"
                        onmouseover="login_btn_td_over('look_td')" onmouseout="login_btn_td_out('look_td')"
                        onclick="look_employee('${employee.employeeNumber}')"
                        style="color: #30B8BE">${employee.employeeNumber}</td>
                    <td align="center">${employee.employeeName}</td>
                    <td align="center">${employee.sex}</td>
                    <td align="center">${employee.tel}</td>
                    <td align="center">${employee.idCard}</td>
                    <td align="center">${employee.departmentName}</td>
                        <%--<td align="center">${employee.founder}</td>--%>
                        <%--<td align="center"><fmt:formatDate value="${employee.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                        <%--<td align="center">${employee.ifOnDuty}</td>--%>
                        <%--<td align="center"><fmt:formatDate value="${employee.leaveDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>--%>
                    <td align="center">${employee.permissions}</td>
                    <c:if test="${sessionScope.employee.permissions > 1}">
                        <td align="center">${employee.deleteMark}</td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div id="look_employee_panel"
         style="display:none;width: 1000px;height: 600px;background-color: #ffffff;position: absolute;z-index: 3;top: 40%;left: 35%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
         align="center">
        <div style="width: 150px;margin-top: 5px;margin-left: 20px;float: left;"><h2>查看员工信息</h2></div>
        <div style="float: right;margin-right: 20px;margin-top: 25px;cursor: pointer;"
             onclick="close_updateEmployee_panel('look_employee_panel')" title="关闭窗口">
            <img id="close_img3" src="../images/index/close1.png" style="width: 16px;height: 16px;"></div>
        <hr width="960">
        <%--员工信息--%>
        <div style="height: 240px;">
            <form id="look_employee_form" action="">
                <input type="hidden" name="e_id"/>

                <div style="height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">员工编号:</span>
                    <input type="text" name="employeeNumber" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">姓&nbsp;&nbsp;&nbsp;&nbsp;名:</span>
                    <input type="text" name="employeeName" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -370px;font-family: 黑体;font-size: 20px;margin-top: 5px">邮&nbsp;&nbsp;&nbsp;&nbsp;箱:</span>
                    <input type="text" name="email" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px;font-family: 黑体;font-size: 20px;">性&nbsp;&nbsp;&nbsp;&nbsp;别:</span>
                    <input style="margin-left: 20px;" type="radio" name="sex" value="男">男
                    <input type="radio" name="sex" value="女">女
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">出生年月:</span>
                    <input type="date" name="birth" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">民&nbsp;&nbsp;&nbsp;&nbsp;族:</span>
                    <input type="text" name="nation" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">政治面貌:</span>
                    <input type="text" name="zzmm" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">婚姻状态:</span>
                    <select id="look_marriage_input" name="marriage"
                            style="width: 250px; height: 35px;margin-left: 10px;" disabled="disabled">
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
                    <input type="text" name="tel" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</span>
                    <input type="text" name="root" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">现居住地:</span>
                    <input type="text" name="address" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">入职时间:</span>
                    <input type="date" name="beginTime" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">转正时间:</span>
                    <input type="date" name="becomeTime" style="height: 35px;width: 250px;margin-left: 10px;"
                           disabled="disabled">
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">离职时间:</span>
                    <input type="date" name="leaveTime" style="height: 35px; width: 250px; margin-left: 10px;"
                           disabled="disabled">
                    <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">任职状态:</span>
                    <select id="look_ifOnDuty_input" name="ifOnDuty"
                            style="height: 35px;width: 250px;margin-left: 10px;" disabled="disabled">
                        <option value="0">在职</option>
                        <option value="1">离职</option>
                    </select>
                </div>
                <div style="width: 550px;height: 45px;margin-top: 5px;">
                    <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;">权限等级:</span>
                    <select id="look_permissions_input" name="permissions"
                            style="width: 250px; height: 35px;margin-left: 10px;" disabled="disabled">
                        <option value="0">（0）员工</option>
                        <option value="1">（1）超级管理员</option>
                        <option value="2">（2）经理</option>
                    </select>
                    <span style="margin-left: 20px;font-family: 黑体;font-size: 20px;">所属部门:</span>
                    <select id="look_department_input" name="departmentName"
                            style="width: 250px; height: 35px;margin-left: 10px;" disabled="disabled">
                        <option value="职介中心">职介中心</option>
                        <option value="人才中心">人才中心</option>
                        <option value="社保中心">社保中心</option>
                        <option value="劳动仲裁中心">劳动仲裁中心</option>
                        <option value="劳动监察大队">劳动监察大队</option>
                        <option value="人事部">人事部</option>
                    </select>
                </div>
            </form>
        </div>
    </div>
</div>
<hr>
<%--底部操作栏--%>
<div style="height: 34px;width: 100%;margin-bottom: 0px;margin-top:0px;">
    <%--左侧页数操作--%>
    <div style="float: left;height: 100%;">
        <span>共</span><span>${sessionScope.page.allPageNum }</span><span>页</span>&nbsp;<span>总共${sessionScope.page.totalCount }条数据</span>
        <a href="#" onclick="upPage(${sessionScope.page.currentPage })">上一页</a>
        <input id="pageInput" type="number" name="pageNum" value="${sessionScope.page.currentPage }"
               style="width: 50px;"/>
        <a href="#" onclick="downPage(${sessionScope.page.currentPage })">下一页</a>
        <a href="#"
           onclick="kipPage('pageInput',${sessionScope.page.allPageNum },${sessionScope.page.currentPage })">跳转</a>
    </div>
    <%--右侧数据操作--%>
    <div style="float: right;height: 100%;width: 500px;">
        <%--增加按钮--%>
        <c:if test="${sessionScope.employee.permissions > 0}">

            <div id="add_btn_div"
                 style="float: right;width: 100px;height: 34px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                 onmouseover="login_btn_div_over('add_btn_div')" onmouseout="login_btn_div_out('add_btn_div')"
                 onclick="add_employee('add_employee_panel')" onmousedown="login_btn_down('add_btn_div')"
                 onmouseup="login_btn_up('add_btn_div')">
                <img src="../images/index/add.png"
                     style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
                <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong
                        style="color: #FFFFFF;">增加</strong></div>
            </div>
        </c:if>
        <%--增加员工面板--%>
        <div id="add_employee_panel"
             style="display:none;width: 1000px;height: 600px;background-color: #ffffff;position: absolute;z-index: 3;top: 40%;left: 35%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="width: 150px;margin-top: 5px;margin-left: 15px;float: left;">
                <h2>增加员工</h2>
            </div>
            <div style="float: right;margin-right: 20px;margin-top: 25px;cursor: pointer;"
                 onclick="close_addEmployee_panel('add_employee_panel')" title="关闭窗口">
                <img id="close_img1"
                     src="../images/index/close1.png"
                     style="width: 16px;height: 16px;">
            </div>
            <hr width="960">
            <%--员工信息--%>
            <div style="width: 550px; height: 240px;">
                <form id="add_employee_form" action="">
                    <div style="height: 45px;margin-top: 5px;">
                        <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">员工编号:</span>
                        <input type="text" name="employeeNumber" style="height: 35px;width: 250px;margin-left: 10px;">
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
                        <%----%>
                        <input type="text" name="nation" style="height: 35px;width: 250px;margin-left: 10px;">
                    </div>
                    <div style="width: 550px;height: 45px;margin-top: 5px;">
                        <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">政治面貌:</span>
                        <input type="text" name="zzmm" style="height: 35px;width: 250px;margin-left: 10px;">
                        <span style="margin-left: 20px; font-family: 黑体;font-size: 20px;">婚姻状态:</span>
                        <select id="marriage_input" name="marriage"
                                style="width: 250px; height: 35px;margin-left: 10px;">
                            <option value="已婚">已婚</option>
                            <option value="未婚">未婚</option>
                            <option value="未知">未知</option>
                        </select>
                    </div>
                    <div style="width: 550px;height: 45px;margin-top: 5px;">
                        <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;margin-top: 5px">证件号码:</span>
                        <input type="text" name="idCrad" style="height: 35px;width: 250px;margin-left: 10px;">
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
                        <select id="ifOnDuty_input" name="ifOnDuty"
                                style="height: 35px;width: 250px;margin-left: 10px;">
                            <option value="0">在职</option>
                            <option value="1">离职</option>
                        </select>
                    </div>
                    <div style="width: 550px;height: 45px;margin-top: 5px;">
                        <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;">权限等级:</span>
                        <select id="permissions_input" name="permissions"
                                style="width: 250px; height: 35px;margin-left: 10px;">
                            <option value="0">（0）员工</option>
                            <option value="1">（1）超级管理员</option>
                            <option value="2">（2）经理</option>
                        </select>
                        <span style="margin-left: 20px;font-family: 黑体;font-size: 20px;">所属部门:</span>
                        <select id="department_input" name="departmentName"
                                style="width: 250px; height: 35px;margin-left: 10px;">
                            <option value="职介中心">职介中心</option>
                            <option value="人才中心">人才中心</option>
                            <option value="社保中心">社保中心</option>
                            <option value="劳动仲裁中心">劳动仲裁中心</option>
                            <option value="劳动监察大队">劳动监察大队</option>
                            <option value="人事部">人事部</option>
                        </select>
                    </div>
                    <sapn style="margin-left: -620px;font-family: 黑体;font-size: 20px;">注：初始密码为 666666</sapn>
                </form>
                <%--确定添加员工按钮--%>
                <div id="determine_btn_div"
                     style="float: right;width: 100px;height: 40px;margin-right:20px;margin-top:-30px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('determine_btn_div')"
                     onmouseout="login_btn_div_out('determine_btn_div')"
                     onclick="determine_add_employee('add_employee_form')"
                     onmousedown="login_btn_down('determine_btn_div')" onmouseup="login_btn_up('determine_btn_div')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>
        <%--删除按钮--%>
        <c:if test="${sessionScope.employee.permissions > 0}">

            <div id="del_btn_div"
                 style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
                 onmouseover="login_btn_div_over('del_btn_div')" onmouseout="login_btn_div_out('del_btn_div')"
                 onclick="del_employee(${sessionScope.page.lists.size()})" onmousedown="login_btn_down('del_btn_div')"
                 onmouseup="login_btn_up('del_btn_div')">
                <img src="../images/index/del.png"
                     style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
                <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong
                        style="color: #FFFFFF;">删除</strong></div>
            </div>
        </c:if>
        <%--修改按钮--%>
        <div id="update_btn_div"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('update_btn_div')" onmouseout="login_btn_div_out('update_btn_div')"
             onclick="update_employee(${sessionScope.page.lists.size()})" onmousedown="login_btn_down('update_btn_div')"
             onmouseup="login_btn_up('update_btn_div')">
            <img src="../images/index/change.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong
                    style="color: #FFFFFF;">修改</strong>
            </div>
        </div>
        <%--修改员工信息面板--%>
        <div id="update_employee_panel"
             style="display:none;width: 1000px;height: 600px;background-color: #ffffff;position: absolute;z-index: 3;top: 40%;left: 35%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="width: 150px;margin-top: 5px;margin-left: 20px;float: left;"><h2>修改员工信息</h2></div>
            <div style="float: right;margin-right: 20px;margin-top: 25px;cursor: pointer;"
                 onclick="close_updateEmployee_panel('update_employee_panel')" title="关闭窗口">
                <img id="close_img2" src="../images/index/close1.png" style="width: 16px;height: 16px;"></div>
            <hr width="960">
            <%--员工信息--%>
            <div style="height: 240px;">
                <form id="update_employee_form" action="">
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
                        <select id="update_marriage_input" name="marriage"
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
                        <select id="update_ifOnDuty_input" name="ifOnDuty"
                                style="height: 35px;width: 250px;margin-left: 10px;" disabled="disabled">
                            <option value="0">在职</option>
                            <option value="1">离职</option>
                        </select>
                    </div>
                    <div style="width: 550px;height: 45px;margin-top: 5px;">
                        <span style="margin-left: -200px;font-family: 黑体;font-size: 20px;">权限等级:</span>
                        <select id="update_permissions_input" name="permissions"
                                style="width: 250px; height: 35px;margin-left: 10px;" disabled="disabled">
                            <option value="0">（0）员工</option>
                            <option value="1">（1）超级管理员</option>
                            <option value="2">（2）经理</option>
                        </select>
                        <span style="margin-left: 20px;font-family: 黑体;font-size: 20px;">所属部门:</span>
                        <select id="update_department_input" name="departmentName"
                                style="width: 250px; height: 35px;margin-left: 10px;">
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
                <div id="update_determine_btn_div"
                     style="float: right;width: 100px;height: 40px;margin-right:230px;margin-top:-5px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('update_determine_btn_div')"
                     onmouseout="login_btn_div_out('update_determine_btn_div')"
                     onclick="determine_update_employee('update_employee_form')"
                     onmousedown="login_btn_down('update_determine_btn_div')"
                     onmouseup="login_btn_up('update_determine_btn_div')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
