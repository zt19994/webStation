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
            <td>验 证 码:</td>
            <td>
                <input id="validateCode" type="text" placeholder="请输入验证码" value="">
            </td>
            <td>
                <img id="validateCodeImg" title="点击更换" onclick="changeCode()" src="http://localhost:8080/user/get_img_code"/>
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
        var userName = $("#userName").val();
        var password = $("#password").val();
        var validateCode = $("#validateCode").val();
        if (userName==''|| password==''){
            alert("用户名或密码不能为空");
            return false;
        }
        if (validateCode==''){
            alert("验证码不能为空");
            return false;
        }
        var params = {
            userName:userName,
            password:password,
            validateCode:validateCode
        };
        var url = 'http://localhost:8080/user/login';
        jQuery.ajax({
            type:'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data : params,
            dataType: 'json',
            success: function (data) {
                alert("发送成功");
            },
            error: function(data){
                alert("发送失败");
            }
        });
    }

    function changeCode() {
        var validateCodeImg =document.getElementById("validateCodeImg");
        validateCodeImg.setAttribute('src', 'http://localhost:8080/user/get_img_code.' + Math.random())
    }

    function toRegister() {
        window.location.href='http://localhost:8080/user/to_register';
    }
</script>
</html>
