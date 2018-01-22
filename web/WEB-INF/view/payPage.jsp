<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/21
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>支付页面</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<div>
    <h1 align="center">支付页面</h1>
</div>
<div align="center">
    <input id="orderNum" type="hidden" value="${orderNum}">
    <button onclick="payTicket()">支付成功</button>
</div>
</body>
<script type="text/javascript">
    function payTicket() {
        alert("支付成功");
        var orderNum = $("#orderNum").val();
        var params = {
            orderNum:orderNum
        };
        var url = '/ticket/pay_ticket';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',

            success: function (data) {
                var msg = data.msg;
                var status = data.status;
                if (status!=0){
                    alert(msg+status);
                }else {
                    //完成购票，到车票列表
                    window.location.href='/ticket/to_ticket';
                }
            },
            error: function (data) {
                alert("失败啦");
            }
        });
    }
</script>
</html>
