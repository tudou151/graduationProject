<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="../data/before.js"></script>
<script src="../data/title.js"></script>
<%--职称信息展示--%>
<div id="title_info_div" style="width: 100%;height: 84%;">
    <div style="width: 100%;height: 30px;">
    </div>
    <%--查询--%>
    <div style="height: 60px;background-color: #F2F2F2;text-align: left;">
        <form id="selectTitle_byemployeeName_input" action="" style="display: inline-block">
            <input  style=" border-radius: 10px; outline: none; margin-left: 20px; border: 1px solid #BCBCBC; width: 300px; height: 40px;margin-top: 10px;" type="text" name="employeeName" placeholder="请输入姓名">
        </form>
        <div id = "t_div_id_28" style="height: 31px; width: 80px; display: inline-block; margin-left: 20px; background-color: #30B8BE; border-radius: 10px; text-align: center; padding:4px;" onmouseover="login_btn_div_over('t_div_id_28')" onmouseout="login_btn_div_out('t_div_id_28')" onclick="selectTitle_byemployeename('selectTitle_byemployeeName_input')" onmousedown="login_btn_down('t_div_id_28')" onmouseup="login_btn_up('t_div_id_28')">
            <span style="display: inline-block; font-size: 20px;color:white; text-align: center">查询</span>
        </div>
    </div>
    <div style="width: 100%;height: 10px;"> </div>
    <table rules="rows" border="0px"  width="100%">
        <tr bgcolor="#30B8BE">
            <th><span style="cursor: pointer;" onclick="title_select_all(${requestScope.titlePage.lists.size()})">全选</span></th>
            <th>员工姓名</th>
            <th>职称编号</th>
            <th>职称名称</th>
            <th>发证机构</th>
            <th>发证时间</th>
        </tr>

        <c:if test="${requestScope.titlePage==null || requestScope.titlePage.allPageNum==0}">
            <tr>
                <td colspan="8" style="color: #CDCDCD;" align="center">
                    <h3>当前还没有职称</h3>
                </td>
            </tr>
        </c:if>
        <c:if test="${requestScope.titlePage!=null && requestScope.titlePage.allPageNum>0}">
            <%int i=1; %>
            <c:forEach var="title" items="${requestScope.titlePage.lists }">
                <tr  style="border-bottom: 2px solid #C8C8C8">
                    <td align="center"><input id="b_checkbox_<%=i%><%i++;%>" type="checkbox" name="ids" value="${title.id}"/></td>
                    <td align="center">${title.employeeNumber}</td>
                    <td align="center">${title.titleNumber}</td>
                    <td align="center">${title.titleName}</td>
                    <td align="center">${title.organization}</td>
                    <td align="center">${title.happenTime}</td>
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
        <span>共</span><span>${requestScope.titlePage.allPageNum }</span><span>页</span>&nbsp;<span>总共${requestScope.titlePage.totalCount }条数据</span>
        <a href="#" onclick="title_upPage('t_div_id_26',${requestScope.titlePage.currentPage })">上一页</a>
        <input id="t_div_id_26" type="number" name="pageNum" max="10" min="1" value="${requestScope.titlePage.currentPage }" style="width: 50px;"/>
        <a href="#" onclick="title_downPage('t_div_id_26',${requestScope.titlePage.currentPage })">下一页</a>
        <a href="#" onclick="title_kipPage('t_div_id_26',${requestScope.titlePage.allPageNum},${requestScope.titlePage.currentPage })">跳转</a>
    </div>
    <%--右侧数据操作--%>
    <div style="float: right;height: 100%;width: 500px;">
        <%--增加按钮--%>
        <div id="t_div_id_21" style="float: right;width: 100px;height: 34px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;" onmouseover="login_btn_div_over('t_div_id_21')" onmouseout="login_btn_div_out('t_div_id_21')" onclick="add_title('t_div_id_22')" onmousedown="login_btn_down('t_div_id_21')" onmouseup="login_btn_up('t_div_id_21')">
            <img src="../images/index/add.png" style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">增加</strong></div>
        </div>
        <div id="t_div_id_22" style="display:none;width: 400px;height: 440px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;" align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">新增职称</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;" onclick="close_title_content_panel('t_div_id_22')" title="关闭窗口">
                <img id="close_img1" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
            <%--<br/>--%>
            <%--职称信息--%>
            <div style="height: 350px;" align="center">
                <form id="add_title_form" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>员工姓名:</span>&nbsp;&nbsp;
                            <input type="text" name="employeeNumber" style="height: 35px;width: 250px;" />
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>职称编号:</span>&nbsp;&nbsp;
                            <input type="text" name="titleNumber" style="height: 35px;width: 250px;" />
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>职称名称:</span>&nbsp;&nbsp;
                            <input type="text" name="titleName" style="height: 35px;width: 250px;" />
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>发证机构:</span>&nbsp;&nbsp;
                            <input type="text" name="organization" style="height: 35px;width: 250px;" />
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>发证时间:</span>&nbsp;&nbsp;
                            <input type="date" name="happenTime" style="height: 35px;width: 250px;" />
                        </div>
                    </div>
                </form>
                <%--确定新增按钮--%>
                <div id="determine_addBtn_div" style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:-150px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;" align="center" onmouseover="login_btn_div_over('determine_addBtn_div')" onmouseout="login_btn_div_out('determine_addBtn_div')" onclick="add_title_true('add_title_form')" onmousedown="login_btn_down('determine_addBtn_div')" onmouseup="login_btn_up('determine_addBtn_div')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong></div>
                </div>
            </div>
        </div>

        <%--删除按钮--%>
        <div id="t_div_id_23" style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;" onmouseover="login_btn_div_over('t_div_id_23')" onmouseout="login_btn_div_out('t_div_id_23')" onclick="del_title('${requestScope.titlePage.lists.size()}')" onmousedown="login_btn_down('t_div_id_23')" onmouseup="login_btn_up('t_div_id_23')">
            <img src="../images/index/del.png" style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px"><div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong style="color: #FFFFFF;">删除</strong></div>
        </div>
        <%--修改按钮--%>
        <div id="t_div_id_24" style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;" onmouseover="login_btn_div_over('t_div_id_24')" onmouseout="login_btn_div_out('t_div_id_24')" onclick="modify_title_btn(${requestScope.titlePage.lists.size()})" onmousedown="login_btn_down('t_div_id_24')" onmouseup="login_btn_up('t_div_id_24')">
            <img src="../images/index/change.png" style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">修改</strong>
            </div>
        </div>
        <%--修改职称信息面板--%>
        <div id="t_div_id_25" style="display:none;width: 400px;height: 440px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;" align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">修改职称</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;" onclick="close_title_content_panel('t_div_id_25')" title="关闭窗口">
                <img id="" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
            <%--<br/>--%>
            <%--职称信息--%>
            <div style="height: 350px;" align="center">
                <form id="t_div_id_43" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-top: 60px;margin-left: 21px;">
                            <span>员工姓名:</span>&nbsp;&nbsp;
                            <input type="text" name="employeeNumber" style="height: 35px;width: 250px;" disabled="disabled"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>职称编号:</span>&nbsp;&nbsp;
                            <input type="text" name="titleNumber" style="height: 35px;width: 250px;" />
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>职称名称:</span>&nbsp;&nbsp;
                            <input type="text" name="titleName" style="height: 35px;width: 250px;" />
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>发证机构:</span>&nbsp;&nbsp;
                            <input type="text" name="organization" style="height: 35px;width: 250px;" />
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>发证时间:</span>&nbsp;&nbsp;
                            <input type="date" name="happenTime" style="height: 35px;width: 250px;" />
                        </div>
                    </div>
                </form>

                <%--确定修改按钮--%>
                <div id="t_div_id_27" style="float: right;width: 100px;height: 40px;margin-right:39px;margin-top:-150px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;" align="center" onmouseover="login_btn_div_over('t_div_id_27')" onmouseout="login_btn_div_out('t_div_id_27')" onclick="update_title_true('t_div_id_43')" onmousedown="login_btn_down('t_div_id_27')" onmouseup="login_btn_up('t_div_id_27')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
