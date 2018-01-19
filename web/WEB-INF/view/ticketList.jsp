<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/18
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>车票列表</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<div>
    <h1 align="center">车票列表</h1>
</div>
<div align="center">
    <span>欢迎您,${currentUser.userName}</span>
    <button onclick="logout()">注销</button>
</div>
<div align="center">
    车票详情
</div>
</body>
<script type="text/javascript">
    function logout() {
        var params = {
        };
        var url = 'http://localhost:8080/user/logout';
        jQuery.ajax({
            type:'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data : params,
            dataType: 'json',
            success: function (data) {
                var status = data.status;
                if (status!=0){
                    alert(status);
                }else {
                    window.location.href='http://localhost:8080/user/to_login';
                }
            },
            error: function(data){
                alert("发送失败");
            }
        });
    }
</script>
</html>
