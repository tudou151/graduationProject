<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="../data/before.js"></script>
<script src="../data/cultivate.js"></script>
<%--培训信息展示--%>
<div id="cultivate_info_div" style="width: 100%;height: 84%;">
    <div style="width: 100%;height: 30px;">
    </div>
    <%--查询--%>
    <div style="height: 60px;background-color: #F2F2F2;text-align: left;">
        <form id="selectCultivate_byemployeeName_input" action="" style="display: inline-block">
            <input  style=" border-radius: 10px; outline: none; margin-left: 20px; border: 1px solid #BCBCBC; width: 300px; height: 40px;margin-top: 10px;" type="text" name="employeeName" placeholder="请输入姓名">
        </form>
        <div id = "f_div_id_28" style="height: 31px; width: 80px; display: inline-block; margin-left: 20px; background-color: #30B8BE; border-radius: 10px; text-align: center; padding:4px;" onmouseover="login_btn_div_over('f_div_id_28')" onmouseout="login_btn_div_out('f_div_id_28')" onclick="selectCultivate_byemployeename('selectCultivate_byemployeeName_input')" onmousedown="login_btn_down('f_div_id_28')" onmouseup="login_btn_up('f_div_id_28')">
            <span style="display: inline-block; font-size: 20px;color:white; text-align: center">查询</span>
        </div>
    </div>
    <div style="width: 100%;height: 10px;"> </div>
    <table rules="rows" border="0px" width="100%">
        <tr bgcolor="#30B8BE">
            <th><span style="cursor: pointer;"
                      onclick="cultivate_select_all(${requestScope.cultivatePage.lists.size()})">全选</span></th>
            <th>员工姓名</th>
            <th>培训时间</th>
            <th>培训学时</th>
            <th>培训内容</th>
            <th>培训地点</th>
            <th>培训成绩</th>
        </tr>

        <c:if test="${requestScope.cultivatePage==null || requestScope.cultivatePage.allPageNum==0}">
            <tr>
                <td colspan="8" style="color: #CDCDCD;" align="center">
                    <h3>当前还没有培训信息</h3>
                </td>
            </tr>
        </c:if>
        <c:if test="${requestScope.cultivatePage!=null && requestScope.cultivatePage.allPageNum>0}">
            <%int i = 1; %>
            <c:forEach var="cultivate" items="${requestScope.cultivatePage.lists }">
                <tr style="border-bottom: 2px solid #C8C8C8">
                    <td align="center"><input id="b_checkbox_<%=i%><%i++;%>" type="checkbox" name="ids"
                                              value="${cultivate.id}"/></td>
                    <td align="center">${cultivate.employeeNumber}</td>
                    <td align="center">${cultivate.happenTime}</td>
                    <td align="center">${cultivate.howLong}</td>
                    <td align="center">${cultivate.content}</td>
                    <td align="center">${cultivate.address}</td>
                    <td align="center">${cultivate.score}</td>
            </c:forEach>
        </c:if>
    </table>
</div>
<hr>


<%--底部操作栏--%>
<div style="height: 34px;width: 100%;margin-bottom: 0px;margin-top:0px;">
    <%--左侧页数操作--%>
    <div style="float: left;height: 100%;">
        <span>共</span><span>${requestScope.cultivatePage.allPageNum }</span><span>页</span>&nbsp;<span>总共${requestScope.cultivatePage.totalCount }条数据</span>
        <a href="#" onclick="cultivate_upPage('f_div_id_26',${requestScope.cultivatePage.currentPage })">上一页</a>
        <input id="f_div_id_26" type="number" name="pageNum" max="10" min="1"
               value="${requestScope.cultivatePage.currentPage }" style="width: 50px;"/>
        <a href="#" onclick="cultivate_downPage('f_div_id_26',${requestScope.cultivatePage.currentPage })">下一页</a>
        <a href="#"
           onclick="cultivate_kipPage('f_div_id_26',${requestScope.cultivatePage.allPageNum},${requestScope.cultivatePage.currentPage })">跳转</a>
    </div>
    <%--右侧数据操作--%>
    <div style="float: right;height: 100%;width: 500px;">
        <%--增加按钮--%>
        <div id="f_div_id_21"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('f_div_id_21')" onmouseout="login_btn_div_out('f_div_id_21')"
             onclick="add_cultivate('f_div_id_22')" onmousedown="login_btn_down('f_div_id_21')"
             onmouseup="login_btn_up('f_div_id_21')">
            <img src="../images/index/add.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">增加</strong></div>
        </div>
        <div id="f_div_id_22"
             style="display:none;width: 400px;height: 500px;background-color: #ffffff;position: absolute;z-index: 3;top: 40%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">新增培训</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_cultivate_content_panel('f_div_id_22')" title="关闭窗口">
                <img id="close_img1" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
            <%--<br/>--%>
            <%--培训信息--%>
            <div style="height: 550px;" align="center">
                <form id="add_cultivate_form" action="">
                    <div style="height: 560px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>员工姓名:</span>&nbsp;&nbsp;
                            <input type="text" name="employeeNumber" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训时间:</span>&nbsp;&nbsp;
                            <input type="date" name="happenTime" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训学时:</span>&nbsp;&nbsp;
                            <input type="text" name="howLong" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>培训内容:</span>&nbsp;&nbsp;
                            <input type="text" name="content" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训地点:</span>&nbsp;&nbsp;
                            <input type="text" name="address" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训成绩:</span>&nbsp;&nbsp;
                            <input type="text" name="score" style="height: 35px;width: 250px;"/>
                        </div>

                    </div>
                </form>
                <%--确定新增按钮--%>
                <div id="determine_addBtn_div"
                     style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:-220px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('determine_addBtn_div')"
                     onmouseout="login_btn_div_out('determine_addBtn_div')"
                     onclick="add_cultivate_true('add_cultivate_form')"
                     onmousedown="login_btn_down('determine_addBtn_div')"
                     onmouseup="login_btn_up('determine_addBtn_div')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>

        <%--删除按钮--%>
        <div id="f_div_id_23"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('f_div_id_23')" onmouseout="login_btn_div_out('f_div_id_23')"
             onclick="del_cultivate('${requestScope.cultivatePage.lists.size()}')"
             onmousedown="login_btn_down('f_div_id_23')" onmouseup="login_btn_up('f_div_id_23')">
            <img src="../images/index/del.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong
                    style="color: #FFFFFF;">删除</strong></div>
        </div>
        <%--修改按钮--%>
        <div id="f_div_id_24"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('f_div_id_24')" onmouseout="login_btn_div_out('f_div_id_24')"
             onclick="modify_cultivate_btn(${requestScope.cultivatePage.lists.size()})"
             onmousedown="login_btn_down('f_div_id_24')" onmouseup="login_btn_up('f_div_id_24')">
            <img src="../images/index/change.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">修改</strong>
            </div>
        </div>
        <%--修改培训信息面板--%>
        <div id="f_div_id_25"
             style="display:none;width: 400px;height: 500px;background-color: #ffffff;position: absolute;z-index: 3;top: 40%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">修改培训</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_cultivate_content_panel('f_div_id_25')" title="关闭窗口">
                <img id="" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
            <%--<br/>--%>
            <%--培训信息--%>
            <div style="height: 550px;" align="center">
                <form id="f_div_id_43" action="">
                    <div style="height: 560px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>员工姓名:</span>&nbsp;&nbsp;
                            <input type="text" name="employeeNumber" style="height: 35px;width: 250px;" disabled="disabled"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训时间:</span>&nbsp;&nbsp;
                            <input type="date" name="happenTime" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训学时:</span>&nbsp;&nbsp;
                            <input type="text" name="howLong" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>培训内容:</span>&nbsp;&nbsp;
                            <input type="text" name="content" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训地点:</span>&nbsp;&nbsp;
                            <input type="text" name="address" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>培训成绩:</span>&nbsp;&nbsp;
                            <input type="text" name="score" style="height: 35px;width: 250px;"/>
                        </div>
                </form>

                <%--确定修改按钮--%>
                <div id="f_div_id_27"
                     style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:10px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('f_div_id_27')"
                     onmouseout="login_btn_div_out('f_div_id_27')" onclick="update_cultivate_true('f_div_id_43')"
                     onmousedown="login_btn_down('f_div_id_27')" onmouseup="login_btn_up('f_div_id_27')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
