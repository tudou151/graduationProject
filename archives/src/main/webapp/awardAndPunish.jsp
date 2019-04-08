<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="../data/before.js"></script>
<script src="../data/awardAndPunish.js"></script>
<%--奖惩信息信息展示--%>
<div id="awardAndPunish_info_div" style="width: 100%;height: 84%;">
    <div style="width: 100%;height: 30px;">
    </div>
    <%--查询--%>
    <div style="height: 60px;background-color: #F2F2F2;text-align: left;">
        <form id="selectAwardAndPunish_byemployeeName_input" action="" style="display: inline-block">
            <input style=" border-radius: 10px; outline: none; margin-left: 20px; border: 1px solid #BCBCBC; width: 300px; height: 40px;margin-top: 10px;"
                   type="text" name="employeeName" placeholder="请输入姓名">
        </form>
        <div id="c_div_id_28"
             style="height: 31px; width: 80px; display: inline-block; margin-left: 20px; background-color: #30B8BE; border-radius: 10px; text-align: center; padding:4px;"
             onmouseover="login_btn_div_over('c_div_id_28')" onmouseout="login_btn_div_out('c_div_id_28')"
             onclick="select_awardAndPunish_byemployeename('selectAwardAndPunish_byemployeeName_input')"
             onmousedown="login_btn_down('c_div_id_28')" onmouseup="login_btn_up('c_div_id_28')">
            <span style="display: inline-block; font-size: 20px;color:white; text-align: center">查询</span>
        </div>
    </div>
    <div style="width: 100%;height: 10px;"></div>
    <table rules="rows" border="0px" width="100%">
        <tr bgcolor="#30B8BE">
            <th><span style="cursor: pointer;"
                      onclick="awardAndPunish_select_all(${requestScope.awardAndPunishPage.lists.size()})">全选</span>
            </th>
            <th>员工姓名</th>
            <th>奖惩类型</th>
            <th>奖惩单位</th>
            <th>奖惩原因</th>
            <th>奖惩时间</th>
        </tr>

        <c:if test="${requestScope.awardAndPunishPage==null || requestScope.awardAndPunishPage.allPageNum==0}">
            <tr>
                <td colspan="8" style="color: #CDCDCD;" align="center">
                    <h3>当前还没有奖惩信息</h3>
                </td>
            </tr>
        </c:if>
        <c:if test="${requestScope.awardAndPunishPage!=null && requestScope.awardAndPunishPage.allPageNum>0}">
            <%int i = 1; %>
            <c:forEach var="awardAndPunish" items="${requestScope.awardAndPunishPage.lists }">
                <tr style="border-bottom: 2px solid #C8C8C8">
                    <td align="center"><input id="b_checkbox_<%=i%><%i++;%>" type="checkbox" name="ids"
                                              value="${awardAndPunish.id}"/></td>
                    <td align="center">${awardAndPunish.employeeNumber}</td>
                    <td align="center">${awardAndPunish.genre}</td>
                    <td align="center">${awardAndPunish.company}</td>
                    <td align="center">${awardAndPunish.reason}</td>
                    <td align="center">${awardAndPunish.happenTime}</td>
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
        <span>共</span><span>${requestScope.awardAndPunishPage.allPageNum }</span><span>页</span>&nbsp;<span>总共${requestScope.awardAndPunishPage.totalCount }条数据</span>
        <a href="#"
           onclick="awardAndPunish_upPage('c_div_id_26',${requestScope.awardAndPunishPage.currentPage })">上一页</a>
        <input id="c_div_id_26" type="number" name="pageNum" max="10" min="1"
               value="${requestScope.awardAndPunishPage.currentPage }" style="width: 50px;"/>
        <a href="#"
           onclick="awardAndPunish_downPage('c_div_id_26',${requestScope.awardAndPunishPage.currentPage })">下一页</a>
        <a href="#"
           onclick="awardAndPunish_kipPage('c_div_id_26',${requestScope.awardAndPunishPage.allPageNum},${requestScope.awardAndPunishPage.currentPage })">跳转</a>
    </div>
    <%--右侧数据操作--%>
    <div style="float: right;height: 100%;width: 500px;">
        <%--增加按钮--%>
        <c:if test="${sessionScope.employee.permissions > 1}"></c:if>

            <div id="c_div_id_21"
                 style="float: right;width: 100px;height: 34px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                 onmouseover="login_btn_div_over('c_div_id_21')" onmouseout="login_btn_div_out('c_div_id_21')"
                 onclick="add_awardAndPunish('c_div_id_22')" onmousedown="login_btn_down('c_div_id_21')"
                 onmouseup="login_btn_up('c_div_id_21')">
                <img src="../images/index/add.png"
                     style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
                <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                    <strong style="color: #FFFFFF;">增加</strong></div>
            </div>

        <div id="c_div_id_22"
             style="display: none; width: 400px; height: 420px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 180px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">新增奖惩信息</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_awardAndPunish_content_panel('c_div_id_22')" title="关闭窗口">
                <img id="close_img1" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
            <%--<br/>--%>
            <%--奖惩信息信息--%>
            <div style="height: 370px;" align="center">
                <form id="add_awardAndPunish_form" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>员工姓名:</span>&nbsp;&nbsp;
                            <input type="text" name="employeeNumber" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>奖惩类型:</span>&nbsp;&nbsp;
                            <input type="text" name="genre" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>奖惩单位:</span>&nbsp;&nbsp;
                            <input type="text" name="company" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>奖惩原因:</span>&nbsp;&nbsp;
                            <input type="text" name="reason" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>奖惩时间:</span>&nbsp;&nbsp;
                            <input type="date" name="happenTime" style="height: 35px;width: 250px;"/>
                        </div>
                    </div>
                </form>
                <%--确定新增按钮--%>
                <div id="determine_addBtn_div"
                     style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:-150px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('determine_addBtn_div')"
                     onmouseout="login_btn_div_out('determine_addBtn_div')"
                     onclick="add_awardAndPunish_true('add_awardAndPunish_form')"
                     onmousedown="login_btn_down('determine_addBtn_div')"
                     onmouseup="login_btn_up('determine_addBtn_div')">
                    <div style="margin-top: 9px;width: 40px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>

        <%--删除按钮--%>
        <div id="c_div_id_23"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('c_div_id_23')" onmouseout="login_btn_div_out('c_div_id_23')"
             onclick="del_awardAndPunish('${requestScope.awardAndPunishPage.lists.size()}')"
             onmousedown="login_btn_down('c_div_id_23')" onmouseup="login_btn_up('c_div_id_23')">
            <img src="../images/index/del.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong
                    style="color: #FFFFFF;">删除</strong></div>
        </div>
        <%--修改按钮--%>
        <div id="c_div_id_24"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('c_div_id_24')" onmouseout="login_btn_div_out('c_div_id_24')"
             onclick="modify_awardAndPunish_btn(${requestScope.awardAndPunishPage.lists.size()})"
             onmousedown="login_btn_down('c_div_id_24')" onmouseup="login_btn_up('c_div_id_24')">
            <img src="../images/index/change.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">修改</strong>
            </div>
        </div>
        <%--修改奖惩信息信息面板--%>
        <div id="c_div_id_25"
             style="display:none;width: 400px;height: 420px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 180px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">修改奖惩信息</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_awardAndPunish_content_panel('c_div_id_25')" title="关闭窗口">
                <img id="" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
            <%--<br/>--%>
            <%--奖惩信息信息--%>
            <div style="height: 370px;" align="center">
                <form id="c_div_id_43" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>员工姓名:</span>&nbsp;&nbsp;
                            <input type="text" name="employeeNumber" style="height: 35px;width: 250px;"
                                   disabled="disabled"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>奖惩类型:</span>&nbsp;&nbsp;
                            <input type="text" name="genre" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>奖惩单位:</span>&nbsp;&nbsp;
                            <input type="text" name="company" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>奖惩原因:</span>&nbsp;&nbsp;
                            <input type="text" name="reason" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>奖惩时间:</span>&nbsp;&nbsp;
                            <input type="date" name="happenTime" style="height: 35px;width: 250px;"/>
                        </div>
                    </div>
                </form>

                <%--确定修改按钮--%>
                <div id="c_div_id_27"
                     style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:-150px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('c_div_id_27')"
                     onmouseout="login_btn_div_out('c_div_id_27')" onclick="update_awardAndPunish_true('c_div_id_43')"
                     onmousedown="login_btn_down('c_div_id_27')" onmouseup="login_btn_up('c_div_id_27')">
                    <div style="margin-top: 9px;width: 40px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
