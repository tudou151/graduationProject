<%--
  Created by IntelliJ IDEA.
  User: YangGang
  Date: 2018/10/30
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="GBK"%>
<html>
<head>
    <title>登陆页面</title>
    <script>
        window.onload=function(){
            function reloadImage() {
            document.getElementById('identity').src = 'image?ts=' + new Date().getTime();
        } }
    </script>
</head>
<body>
<center>
    <%
        // 乱码解决
        request.setCharacterEncoding("GBK");
    %>
    <h1>
        登陆程序
    </h1>
    <hr>
    <%=request.getAttribute("info") != null ? request.getAttribute("info") : ""%>
    <form action="index" method="post">
        用户ID：
        <input type="text" name="mid">
        <br>
        密  码：
        <input type="password" name="password">
        <br>
        验证码：
        <input type="text" name="code"  maxlength="5" size="5">
        <img src="image" id="identity" onclick="reloadImage()" title="看不清，点击换一张">
        <br>
        <input type="submit" value="登陆">
        <input type="reset" value="重置">
    </form>
</center>
</body>
</html>
