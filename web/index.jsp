<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/16
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
  <head>
    <title>导航页面</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
  </head>
  <body>
  <div>
      <h1 align="center">导航页面</h1>
  </div>
  <div align="center">
      <button onclick="toRegister()">注册页面</button>
      <button onclick="toLogin()">登录页面</button>
  </div>
  </body>
  <script type="text/javascript">
    function toRegister() {
        window.location.href='/user/to_register';
    }

    function toLogin() {
        window.location.href='/user/to_login';
    }
  </script>
</html>
