<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/16
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录页面</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<div>
    <h1 align="center">登&nbsp;录</h1>
</div>
<div align="center">
    <table>
        <tr>
            <td>用 户 名:</td>
            <td>
                <input id="userName" type="text" placeholder="请输入用户名" value=""/>
            </td>
        </tr>
        <tr>
            <td>密 &nbsp; 码:</td>
            <td>
                <input id="password" type="password" placeholder="请输入密码" value=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="login()">登录</button>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="toRegister()">还未注册,立即注册</button>
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">
    function login() {
        alert("登录");
    }

    function toRegister() {
        window.location.href='http://localhost:8080/user/to_register';
    }
</script>
</html>
