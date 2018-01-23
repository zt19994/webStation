<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/23
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>账户设置</title>
</head>
<body>
<div>
    <h1 align="center">账户设置</h1>
</div>
<div align="center">
    <span>欢迎您,${currentUser.userName}</span>
    <br/>
    <table align="center" border="1" cellspacing="1" cellpadding="1" width="350">
        <tr>
            <input id="userId" type="hidden" value="${currentUser.id}">
        </tr>
        <tr>
            <td align="center">登录用户名</td>
            <td align="center">${currentUser.userName}</td>
            <td align="center">
                <button>修改</button>
            </td>
        </tr>
        <tr>
            <td align="center">电话号码</td>
            <td align="center">${currentUser.phone}</td>
            <td align="center">
                <button>修改</button>
            </td>
        </tr>
        <tr>
            <td align="center">注册邮箱</td>
            <td align="center">${currentUser.email}</td>
            <td align="center">
                <button>修改</button>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
