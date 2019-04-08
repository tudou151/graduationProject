<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="../data/before.js"></script>
<script src="../data/bulletin.js"></script>
<%--公告信息展示--%>
<div id="bulletin_info_div" style="width: 100%;height: 84%;">
    <div style="width: 100%;height: 30px;">
    </div>
    <%-- 查询--%>
    <div style="height: 60px;background-color: #F2F2F2;text-align: left;">
        <form id="selectBulletin_byTitle_input" action="" style="display: inline-block">
            <input style=" border-radius: 10px; outline: none; margin-left: 20px; border: 1px solid #BCBCBC; width: 300px; height: 40px;margin-top: 10px;"
                   type="text" name="bulletintitle" placeholder="请输入公告标题">
        </form>
        <div id="a_div_id_99"
             style="height: 31px; width: 80px; display: inline-block; margin-left: 20px; background-color: #30B8BE; border-radius: 10px; text-align: center; padding:4px;"
             onmouseover="login_btn_div_over('a_div_id_99')" onmouseout="login_btn_div_out('a_div_id_99')"
             onclick="selectbulletin_byBulletinTitle('selectBulletin_byTitle_input')"
             onmousedown="login_btn_down('a_div_id_99')" onmouseup="login_btn_up('a_div_id_99')">
            <span style="display: inline-block; font-size: 20px;color:white; text-align: center">查询</span>
        </div>
    </div>
    <div style="width: 100%;height: 10px;"></div>
    <table rules="rows" border="0px" width="100%">
        <tr bgcolor="#30B8BE">
            <th><span style="cursor: pointer;" onclick="bulletin_select_all(${requestScope.bulletinPage.lists.size()})">全选</span>
            </th>
            <th>标题</th>
            <th>状态</th>
            <th>创建人</th>
            <th>创建时间</th>
            <th>修改人</th>
            <th>修改时间</th>
            <th>内容</th>
        </tr>
        <c:if test="${requestScope.bulletinPage==null || requestScope.bulletinPage.allPageNum==0}">
            <tr>
                <td colspan="8" style="color: #CDCDCD;" align="center">
                    <h3>当前还没有公告</h3>
                </td>
            </tr>
        </c:if>
        <c:if test="${requestScope.bulletinPage!=null && requestScope.bulletinPage.allPageNum>0}">
            <%int i = 1; %>
            <c:forEach var="bulletin" items="${requestScope.bulletinPage.lists }">
                <tr style="border-bottom: 2px solid #ccc">
                    <td align="center"><input id="b_checkbox_<%=i%><%i++;%>" type="checkbox" name="ids"
                                              value="${bulletin.id}"/></td>
                    <td align="center">${bulletin.title}</td>
                    <td align="center">${bulletin.state}</td>
                    <td align="center">${bulletin.founder}</td>
                    <td align="center"><fmt:formatDate value="${bulletin.createTime}"
                                                       pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center">${bulletin.modifier}</td>
                    <td align="center"><fmt:formatDate value="${bulletin.modifyTime}"
                                                       pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center">
                        <a href="#" onclick="look_bulletin_content('a_div_id_28','${bulletin.title}','${bulletin.content}','<fmt:formatDate value="${bulletin.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/>','<fmt:formatDate value="${bulletin.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>')">查看详情</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<hr>
<%--查看公告详细内容--%>
<div id="a_div_id_28"
     style="border-radius: 10px;display: none;width: 385px;height: 450px;padding: 20px;background-color: #FFFFFF;overflow-x:hidden;overflow-y:auto;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -192.5px;margin-top: -225px;"
     align="center">
    <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: -20px;">
        <h2 style="margin-top: 15px;">公告详情</h2>
    </div>
    <div style="float: right;margin-right: 20px;margin-top: 0px;cursor: pointer;"
         onclick="close_bulletin_content_panel('a_div_id_28')" title="关闭窗口"><img id="close_img2"
                                                                                 src="../images/index/close1.png"
                                                                                 style="width: 16px;height: 16px;">
    </div>
    <br/>
    <hr style="float: left;margin-left: 0px;width: 380px">
    <h4>生效日期</h4>
    <p id="a_div_id_41"></p>
    <h4>失效日期</h4>
    <p id="a_div_id_42"></p>
    <h4>标题</h4>
    <p id="a_div_id_29"></p>
    <h4>内容</h4>
    <p id="a_div_id_30"></p>
</div>
<%--底部操作栏--%>
<div style="height: 34px;width: 100%;margin-bottom: 0px;margin-top:0px;">
<%--左侧页数操作--%>
<div style="float: left;height: 100%;">
    <span>共</span><span>${requestScope.bulletinPage.allPageNum }</span><span>页</span>&nbsp;<span>总共${requestScope.bulletinPage.totalCount }条数据</span>
    <a href="#" onclick="bulletin_upPage('a_div_id_26',${requestScope.bulletinPage.currentPage })">上一页</a>
    <input id="a_div_id_26" type="number" name="pageNum" max="10" min="1"
           value="${requestScope.bulletinPage.currentPage }" style="width: 50px;"/>
    <a href="#" onclick="bulletin_downPage('a_div_id_26',${requestScope.bulletinPage.currentPage })">下一页</a>
    <a href="#"
       onclick="bulletin_kipPage('a_div_id_26',${requestScope.bulletinPage.allPageNum},${requestScope.bulletinPage.currentPage })">跳转</a>
</div>
<%--右侧数据操作--%>
<c:if test="${sessionScope.employee.permissions > 0}">

    <div style="float: right;height: 100%;width: 500px;">
            <%--增加按钮--%>
        <div id="a_div_id_21"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('a_div_id_21')" onmouseout="login_btn_div_out('a_div_id_21')"
             onclick="add_bulletin('a_div_id_22')" onmousedown="login_btn_down('a_div_id_21')"
             onmouseup="login_btn_up('a_div_id_21')">
            <img src="../images/index/add.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">增加</strong>
            </div>
        </div>
        <div id="a_div_id_22"
             style="display:none;width: 400px;height: 500px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">新增公告</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_bulletin_content_panel('a_div_id_22')" title="关闭窗口">
                <img id="close_img1" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
                <%--<br/>--%>
                <%--公告信息--%>
            <div style="height: 450px;" align="center">
                <form id="add_bulletin_form" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-left: 21px;">
                            <span>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:</span>&nbsp;&nbsp;
                            <input type="text" name="title" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 15px;margin-left: 21px;">
                            <span>生效时间:</span>&nbsp;&nbsp;
                            <input type="datetime-local" name="startDate" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-left: 21px;">
                            <span>失效时间:</span>&nbsp;&nbsp;
                            <input type="datetime-local" name="endDate" style="height: 35px;width: 250px;"/>
                        </div>
                        <span style="float: left;margin-left: 21px;">公告内容:</span><br/><br/>
                        <textarea name="content" rows="5" cols="46"
                                  style="resize: none;margin-left: 21px;height: 100px;width: 342px;"></textarea>
                    </div>
                </form>
                    <%--确定新增按钮--%>
                <div id="determine_addBtn_div"
                     style="float: right;width: 100px;height: 34px;margin-right:39px;margin-top:-60px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('determine_addBtn_div')"
                     onmouseout="login_btn_div_out('determine_addBtn_div')"
                     onclick="add_bulletin_true('add_bulletin_form')"
                     onmousedown="login_btn_down('determine_addBtn_div')"
                     onmouseup="login_btn_up('determine_addBtn_div')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>

            <%--删除按钮--%>
        <div id="a_div_id_23"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('a_div_id_23')" onmouseout="login_btn_div_out('a_div_id_23')"
             onclick="del_bulletin('${requestScope.bulletinPage.lists.size()}')"
             onmousedown="login_btn_down('a_div_id_23')" onmouseup="login_btn_up('a_div_id_23')">
            <img src="../images/index/del.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;"><strong
                    style="color: #FFFFFF;">删除</strong></div>
        </div>
            <%--修改按钮--%>
        <div id="a_div_id_24"
             style="float: right;width: 100px;height: 34px;background-color: #30B8BE;margin-right: 25px;cursor: pointer;border-radius: 5px;"
             onmouseover="login_btn_div_over('a_div_id_24')" onmouseout="login_btn_div_out('a_div_id_24')"
             onclick="modify_bulletin_btn(${requestScope.bulletinPage.lists.size()})"
             onmousedown="login_btn_down('a_div_id_24')" onmouseup="login_btn_up('a_div_id_24')">
            <img src="../images/index/change.png"
                 style="width: 16px;height: 16px;margin-top: 9px;float: left;margin-left: 20px">
            <div style="margin-left: 6px;margin-top: 9px;width: 34px;height: 16px;float: left;">
                <strong style="color: #FFFFFF;">修改</strong>
            </div>
        </div>
            <%--修改公告信息面板--%>
        <div id="a_div_id_25"
             style="display:none;width: 400px;height: 500px;background-color: #ffffff;position: absolute;z-index: 3;top: 50%;left: 50%;margin-left: -200px;margin-top: -235px;border-radius: 10px;"
             align="center">
            <div style="float: left;margin-left: 5px;width: 122px;height: 55px;margin-top: 5px;">
                <h2 style="margin-top: 15px;">修改公告</h2>
            </div>
            <div style="float: right;margin-right: 40px;margin-top: 25px;cursor: pointer;"
                 onclick="close_bulletin_content_panel('a_div_id_25')" title="关闭窗口">
                <img id="" src="../images/index/close1.png" style="margin-top: 5px;width: 16px;height: 16px;">
            </div>
            <br/>
            <hr style="float: left;margin-left: 20px;width:360px">
                <%--<br/>--%>
                <%--公告信息--%>
            <div style="height: 450px;" align="center">
                <form id="a_div_id_43" action="">
                    <div style="height: 416px;margin-top: 40px" align="left">
                        <div style="height: 44px;width: 360px;margin-left: 21px;">
                            <span>标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题:</span>&nbsp;&nbsp;
                            <input type="text" name="title" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-top: 10px;margin-left: 21px;">
                            <span>生效时间:</span>&nbsp;&nbsp;
                            <input type="datetime-local" name="startDate" style="height: 35px;width: 250px;"/>
                        </div>
                        <div style="height: 44px;width: 360px;margin-left: 21px;">
                            <span>失效时间:</span>&nbsp;&nbsp;
                                <%--<input  type="date" name="endDate" style="height: 35px;width: 250px;" />--%>
                            <input type="datetime-local" name="endDate" style="height: 35px;width: 250px;"/>
                        </div>
                        <span style="float: left;margin-left: 21px;">公告内容:</span><br/><br/>
                        <textarea name="content" rows="5" cols="46"
                                  style="resize: none;margin-left: 21px;height: 100px;width: 342px;"></textarea>
                    </div>
                </form>

                    <%--确定修改按钮--%>
                <div id="a_div_id_27"
                     style="float: right;width: 100px;height: 34px;margin-right:39px;margin-top:-60px;background-color: #30B8BE;cursor: pointer;border-radius: 5px;"
                     align="center" onmouseover="login_btn_div_over('a_div_id_27')"
                     onmouseout="login_btn_div_out('a_div_id_27')" onclick="update_bulletin_true('a_div_id_43')"
                     onmousedown="login_btn_down('a_div_id_27')" onmouseup="login_btn_up('a_div_id_27')">
                    <div style="margin-top: 9px;width: 34px;height: 16px;"><strong style="color: #FFFFFF;">确定</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</c:if>
</div>
