<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="../data/before.js"></script>
<script src="../data/department.js"></script>
<%--部门信息展示--%>
<div id="department_info_div" style="width: 100%;height: 84%;">
    <div style="width: 100%;height: 30px;">
    </div>
    <%-- 查询--%>
    <div style="height: 60px;background-color: #F2F2F2;text-align: left;">
        <form id="selectDepartment_byName_input" action="" style="display: inline-block">
            <input style=" border-radius: 10px; outline: none; margin-left: 20px; border: 1px solid #BCBCBC; width: 300px; height: 40px;margin-top: 10px;"
                   type="text" name="departmentName" placeholder="请输入部门名称">
        </form>
        <div id="d_div_id_28"
             style="height: 31px; width: 80px; display: inline-block; margin-left: 20px; background-color: #30B8BE; border-radius: 10px; text-align: center; padding:4px;"
             onmouseover="login_btn_div_over('d_div_id_28')" onmouseout="login_btn_div_out('d_div_id_28')"
             onclick="selectDepartment_byDepartmentName('selectDepartment_byName_input')"
             onmousedown="login_btn_down('d_div_id_28')" onmouseup="login_btn_up('d_div_id_28')">
            <span style="display: inline-block; font-size: 20px;color:white; text-align: center">查询</span>
        </div>
    </div>
    <div style="width: 100%;height: 10px;"></div>
    <table rules="rows" border="0px" width="100%">
        <tr bgcolor="#30B8BE">
            <th><span style="cursor: pointer;"
                      onclick="department_select_all(${requestScope.departmentPage.lists.size()})">全选</span></th>
            <th>部门编号</th>
            <th>部门名称</th>
            <th>部门主管</th>
            <th>部门地址</th>
            <th>部门电话</th>
            <th>部门传真</th>
        </tr>

        <c:if test="${requestScope.departmentPage==null || requestScope.departmentPage.allPageNum==0}">
            <tr>
                <td colspan="8" style="color: #CDCDCD;" align="center">
                    <h3>当前还没有部门</h3>
                </td>
            </tr>
        </c:if>
        <c:if test="${requestScope.departmentPage!=null && requestScope.departmentPage.allPageNum>0}">
            <%int i = 1; %>
            <c:forEach var="department" items="${requestScope.departmentPage.lists }">
                <tr style="border-bottom: 2px solid #C8C8C8">
                    <td align="center"><input id="b_checkbox_<%=i%><%i++;%>" type="checkbox" name="ids"
                                              value="${department.id}"/></td>
                    <td align="center">${department.departmentNumber}</td>
                    <td align="center">${department.departmentName}</td>
                    <td align="center">${department.departmentHead}</td>
                    <td align="center">${department.departmentAddress}</td>
                    <td align="center">${department.departmentTel}</td>
                    <td align="center">${department.departmentFax}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<hr>


<%--底部操作栏--%>
<div style="height: 34px;width: 100%;margin-bottom: 0px;margin-top:0px;">
<%--左侧页数操作--%>
<div style="float: left;height: 100%;">
    <span>共</span><span>${requestScope.departmentPage.allPageNum }</span><span>页</span>&nbsp;<span>总共${requestScope.departmentPage.totalCount }条数据</span>
    <a href="#" onclick="department_upPage('d_div_id_26',${requestScope.departmentPage.currentPage })">上一页</a>
    <input id="d_div_id_26" type="number" name="pageNum" max="10" min="1"
           value="${requestScope.departmentPage.currentPage }" style="width: 50px;"/>
    <a href="#" onclick="department_downPage('d_div_id_26',${requestScope.departmentPage.currentPage })">下一页</a>
    <a href="#"
       onclick="department_kipPage('d_div_id_26',${requestScope.departmentPage.allPageNum},${requestScope.departmentPage.currentPage })">跳转</a>
</div>
<%--右侧数据操作--%>
<c:if test="${sessionScope.employee.permissions > 0}">

    <div style="float: right;height: 100%;width: 500px;">
            <%--增加按钮--%>
        <div id="d_div_id_21"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('d_div_id_21')" onmouseout="login_btn_div_out('d_div_id_21')"
             onclick="add_department('d_div_id_22')" onmousedown="login_btn_down('d_div_id_21')"
             onmouseup="login_btn_up('d_div_id_21')">
            <img src="../images/index/add.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">增加</strong></div>
        </div>
        <div id="d_div_id_22"
             style="display:none;width: 400px;height: 500px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">新增部门</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_department_content_panel('d_div_id_22')" title="关闭窗口">
                <img id="close_img1" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
                <%--<br/>--%>
                <%--部门信息--%>
            <div style="height: 450px;" align="center">
                <form id="add_department_form" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>部门编号:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentNumber" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>部门名称:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentName" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>部门主管:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentHead" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>部门地址:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentAddress" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>部门电话:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentTel" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>部门传真:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentFax" style="height: 35px;width: 250px;"/>
                        </div>
                    </div>
                </form>
                    <%--确定新增按钮--%>
                <div id="determine_addBtn_div"
                     style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:-80px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('determine_addBtn_div')"
                     onmouseout="login_btn_div_out('determine_addBtn_div')"
                     onclick="add_department_true('add_department_form')"
                     onmousedown="login_btn_down('determine_addBtn_div')"
                     onmouseup="login_btn_up('determine_addBtn_div')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>

            <%--删除按钮--%>
        <div id="d_div_id_23"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('d_div_id_23')" onmouseout="login_btn_div_out('d_div_id_23')"
             onclick="del_department('${requestScope.departmentPage.lists.size()}')"
             onmousedown="login_btn_down('d_div_id_23')" onmouseup="login_btn_up('d_div_id_23')">
            <img src="../images/index/del.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong
                    style="color: #FFFFFF;">删除</strong></div>
        </div>
            <%--修改按钮--%>
        <div id="d_div_id_24"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('d_div_id_24')" onmouseout="login_btn_div_out('d_div_id_24')"
             onclick="modify_department_btn(${requestScope.departmentPage.lists.size()})"
             onmousedown="login_btn_down('d_div_id_24')" onmouseup="login_btn_up('d_div_id_24')">
            <img src="../images/index/change.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">修改</strong>
            </div>
        </div>
            <%--修改部门信息面板--%>
        <div id="d_div_id_25"
             style="display:none;width: 400px;height: 500px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">修改部门</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_department_content_panel('d_div_id_25')" title="关闭窗口">
                <img id="" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
                <%--<br/>--%>
                <%--部门信息--%>
            <div style="height: 450px;" align="center">
                <form id="d_div_id_43" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>部门编号:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentNumber" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>部门名称:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentName" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>部门主管:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentHead" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>部门地址:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentAddress" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>部门电话:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentTel" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>部门传真:</span>&nbsp;&nbsp;
                            <input type="text" name="departmentFax" style="height: 35px;width: 250px;"/>
                        </div>
                    </div>
                </form>

                    <%--确定修改按钮--%>
                <div id="d_div_id_27"
                     style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:-90px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('d_div_id_27')"
                     onmouseout="login_btn_div_out('d_div_id_27')" onclick="update_department_true('d_div_id_43')"
                     onmousedown="login_btn_down('d_div_id_27')" onmouseup="login_btn_up('d_div_id_27')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</c:if>
</div>
