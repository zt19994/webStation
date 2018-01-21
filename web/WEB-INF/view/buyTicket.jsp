<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/1/21
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>购票页面</title>
    <script type="text/javascript" src="/static/jquery-2.1.3.min.js"></script>
</head>
<body>
<div>
    <h1 align="center">购票页面</h1>
</div>
<div>
    <h2 align="center">车票信息</h2>
    <table id="ticketDetail" align="center" border="1" cellspacing="1" cellpadding="1" width="750">
        <tr>
            <td align="center">编号</td>
            <td align="center">始发站</td>
            <td align="center">终点站</td>
            <td align="center">发车时间</td>
            <td align="center">价格</td>
            <td align="center">余票数</td>
        </tr>
        <tr>
            <td id="ticketId">${ticketId}</td>
        </tr>
    </table>
    <h2 align="center">用户信息</h2>
    <table align="center" border="1" cellspacing="1" cellpadding="1" width="750">
        <tr>
            <td align="center">用户名</td>
            <td align="center">电话号码</td>
            <td align="center">邮箱</td>
            <td align="center">购票数量</td>
        </tr>
        <tr>
            <td align="center">${currentUser.userName}</td>
            <td align="center">${currentUser.phone}</td>
            <td align="center">${currentUser.email}</td>
            <td align="center">
                <input id="num" type="text" value="">
            </td>
        </tr>
    </table>
    <div align="center">
        <button onclick="">确认支付</button>
    </div>
</div>
</body>
<script type="text/javascript">
    function loadData() {
        var ticketId = $("#ticketId").html();
        var params = {
            ticketId:ticketId
        };
        var url = 'http://localhost:8080/ticket/ticket_detail';
        jQuery.ajax({
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            url: url,
            data: params,
            dataType: 'json',

            success: function (data) {

                var html =
                    '<tr>' +
                    '<td>编号</td>' +
                    '<td>起始站</td>' +
                    '<td>终点站</td>' +
                    '<td>发车时间</td>' +
                    '<td>票价</td>' +
                    '<td>余票</td>' +
                    '</tr>';

                var ticket = data.data;
                var id = ticket.id;
                var startStation = ticket.startStation;

                var stopStation = ticket.stopStation;
                var departureTime = ticket.departureTime;
                var price = ticket.price;
                var ticketNum = ticket.ticketNum;

                html = html +
                    '<tr>' +
                    '<td align="center">' + id + '</td>' +
                    '<td align="center">' + startStation + '</td>' +
                    '<td align="center">' + stopStation + '</td>' +
                    '<td align="center">' + departureTime + '</td>' +
                    '<td align="center">' + price + '</td>' +
                    '<td align="center">' + ticketNum + '</td>' +
                    '</tr>';

                $("#ticketDetail").html(html);

            },
            error: function (data) {
                /*alert("失败啦");*/
            }
        });
    }

    loadData();
</script>
</html>
