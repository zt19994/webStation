<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/16
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<div>
    <h1 align="center">注&nbsp;册</h1>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</div>
<div>
    <table align="center">
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
            <td>确认密码:</td>
            <td>
                <input id="ConfirmPassword" type="password" placeholder="确认密码" value=""/>
            </td>
        </tr>
        <tr>
            <td>电话号码:</td>
            <td>
                <input id="phone" type="text" placeholder="请输入电话号码" value=""/>
            </td>
            <td>
                <button onclick="sendCode()">发送验证码</button>
            </td>
        </tr>
        <tr>
            <td>验 证 码:</td>
            <td>
                <input id="validateCode" type="text" placeholder="请输入验证码" width="20" value=""/>
            </td>
        </tr>
        <tr>
            <td> &nbsp;email :</td>
            <td>
                <input id="email" type="text" placeholder="请输入email" value=""/>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="register()">注册</button>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button onclick="toLogin()">已有账号,立即登录</button>
            </td>
        </tr>
    </table>
</div>
</body>
<script type="text/javascript">
    function sendCode() {
        var phone = $("#phone").val();
        if (!(/^1[34578]\d{9}$/.test(phone))) {
            alert("手机号输入有误");
            return false;
        }
        var params = {
            phone: phone
        };
        var url = 'http://localhost:8080/user/get_msg_code';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',
            success: function (data) {
                var status = data.status;
                var msg = data.msg;
                if (status == 0) {
                    alert("发送成功");
                } else if (status == 1) {
                    alert(msg);
                }
                setTimeout(2000);
            },
            error: function (data) {
                alert("发送失败");
                setTimeout(2000);
            }
        });
    }

    function register() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var ConfirmPassword = $("#ConfirmPassword").val();
        var phone = $("#phone").val();
        var validateCode = $("#validateCode").val();
        var email = $("#email").val();

        if (userName == '' || password == '' || ConfirmPassword == '') {
            alert("用户名或密码不能为空");
            return false;
        }
        if (password != ConfirmPassword) {
            alert("两次密码不相等");
            return false;
        }
        if (!(/^1[34578]\d{9}$/.test(phone))) {
            alert("手机号填写错误");
            return false;
        }
        if (validateCode == '') {
            alert("验证码不能为空");
            return false;
        }
        var params = {
            userName: userName,
            password: password,
            phone: phone,
            email: email,
            validateCode: validateCode
        };
        var url = 'http://localhost:8080/user/register';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',
            success: function (data) {
                var status = data.status;
                var msg = data.msg;
                if (status != 0) {
                    alert(msg + status);
                } else {
                    alert(msg);
                }

            },
            error: function (data) {
                alert("发送失败");
            }
        });
    }

    function toLogin() {
        window.location.href = 'http://localhost:8080/user/to_login';
    }
</script>
</html>
