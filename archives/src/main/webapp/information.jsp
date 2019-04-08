<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="../data/before.js"></script>
<script src="../data/experience.js"></script>
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    .tr {
        color: #12B3BA;
        border: 1px;
        height: 60px;
    }

    .td {
        color: black;
        border: 2px solid #000000;
        width: 80px;
        text-align: center;
    }

    .thOne {
        color: black;
        border: 2px solid #000000;
        width: 80px;
        text-align: left;
        font-size: 20px;
    }

    .thSecond {
        color: black;
        border: 2px solid #000000;
        width: 80px;
        text-align: right;
        font-size: 20px;
    }

</style>
<%--信息展示--%>
<div id="" style="width: 100%;height: 84%;">
    <div style="width: 100%;height: 30px;"></div>
    <div id="employee_info" style="width: 100%">
        <table>
            <tr class="tr">
                <th class="thOne" colspan="9">&nbsp;&nbsp;基本信息</th>
                <th class="thSecond">修改&nbsp;&nbsp;</th>
            </tr>
            <tr class="tr">
                <td class="td">姓名</td>
                <td class="td">${sessionScope.employee.employeeName}</td>
                <td class="td">性别</td>
                <td class="td">${sessionScope.employee.sex}</td>
                <td class="td">电话</td>
                <td class="td">${sessionScope.employee.tel}</td>
                <td class="td">身份证号码</td>
                <td class="td">${sessionScope.employee.idCard}</td>
                <td class="td">创建人</td>
                <td class="td">${sessionScope.employee.founder}</td>
            </tr>
            <tr class="tr">
                <th class="thOne" colspan="9">&nbsp;&nbsp;证书信息</th>
                <th class="thSecond">修改&nbsp;&nbsp;</th>
            </tr>

<%--
            <%int i=1; %>
--%>
            <c:forEach var="certificate" items="${requestScope.certificatePage.lists }">
                <tr class="tr">
<%--
                    <td align="center">${certificate.id}</td>
--%>
                    <td class="td">证书编号</td>
                    <td class="td">${certificate.certificateNumber}</td>
                    <td class="td">证书名称</td>
                    <td class="td">${certificate.name}</td>
                    <td class="td">发证机构</td>
                    <td class="td">${certificate.organization}</td>
                    <td class="td">发证时间</td>
                    <td class="td">${certificate.happenTime}</td>
                    <td class="td">创建人</td>
                    <td class="td">${sessionScope.employee.founder}</td>
                </tr>
            </c:forEach>

        </table>

    </div>
</div>
