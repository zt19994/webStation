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
    <script type="text/javascript" src="/my97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div>
    <h1 align="center">车票列表</h1>
</div>
<div align="center">
    <span>欢迎您,${currentUser.userName}</span>
    <button onclick="toSet()">设置</button>
    <button onclick="logout()">注销</button>
    <button onclick="toExport()">导出</button>
</div>
<br/>
<div align="center">
    <table>
        <tr>
            <td>起 始 站:<input id="startStation" type="text" value=""> 到</td>
            <td>终 点 站:<input id="stopStation" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                开始时间:<input id="minTime" type="text" />
                <img onclick="WdatePicker({el:'minTime'})" src="/my97DatePicker/skin/datePicker.gif" width="20" height="22" align="absmiddle">
            </td>
            <td>
                结束时间:<input id="maxTime" type="text"/>
                <img onclick="WdatePicker({el:'maxTime'})" src="/my97DatePicker/skin/datePicker.gif" width="20" height="22" align="absmiddle">
            </td>
            <td>
                <button onclick="loadData()">查询</button>
            </td>
        </tr>
    </table>


    <table id="ticketList" align="center" border="1" cellspacing="1" cellpadding="1" width="750">
        <tr>
            <td align="center">编号</td>
            <td align="center">始发站</td>
            <td align="center">终点站</td>
            <td align="center">发车时间</td>
            <td align="center">价格</td>
            <td align="center">余票数</td>
            <td align="center">操作</td>
        </tr>
    </table>
    <button onclick="firstPage()">首页</button>
    <button onclick="prePage()">上一页</button>
    <button onclick="nextPage()">下一页</button>
    <button onclick="lastPage()">末页</button>
    跳转到<input id="jumpPage" type="text"  size="1" value="1">
    <button onclick="jumpPage()">跳转</button>
    第<span id="pageNum"></span>/<span id="pages"></span>页，
    每页条数<span id="pageSize"></span>条，
    总共<span id="total"></span>条
</div>
</body>
<script type="text/javascript">
    function toExport() {
        alert("导出");
        window.location.href='/ticket/to_export.xls';
    }

    function toSet() {
        window.location.href='/user/to_set';
    }

    function logout() {
        var params = {
        };
        var url = '/user/logout';
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
                    window.location.href='/user/to_login';
                }
            },
            error: function(data){
                alert("发送失败");
            }
        });
    }

    function firstPage() {
        var firstPage = 1;
        var pageSize = $("#pageSize").html();
        loadData(firstPage, pageSize);
    }

    function prePage() {
        var pageNum = $("#pageNum").html();
        var pageSize = $("#pageSize").html();
        var currentPage = pageNum - 1;
        if (currentPage < 1) {
            currentPage = 1;
            return currentPage;
        }
        loadData(currentPage, pageSize)
    }

    function nextPage() {
        var pages = $("#pages").html();
        var pageNum = $("#pageNum").html();
        var pageSize = $("#pageSize").html();
        var currentPage = parseInt(pageNum) + 1;
        if (currentPage>pages){
            currentPage=pages;
            return currentPage;
        }
        loadData(currentPage, pageSize)
    }

    function lastPage() {
        var pages = $("#pages").html();
        var pageSize = $("#pageSize").html();
        loadData(pages, pageSize)
    }

    function jumpPage() {
        var pages = $("#pages").html();
        var currentPage = $("#jumpPage").val();
        var pageSize = $("#pageSize").html();
        if (currentPage<=1){
            currentPage = 1;
            loadData(currentPage, pageSize)
        }else if (currentPage>pages){
            loadData(pages, pageSize)
        }else {
            loadData(currentPage, pageSize)
        }
    }

    function loadData(pageNum, pageSize) {
        //获取搜索数据
        var startStation = $("#startStation").val();
        var stopStation = $("#stopStation").val();
        var minTime = $("#minTime").val();
        var maxTime = $("#maxTime").val();
        var params = {
            startStation:startStation,
            stopStation:stopStation,
            minTime:minTime,
            maxTime:maxTime,
            pageNum: pageNum,
            pageSize:pageSize
        };
        var url = '/ticket/list';
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
                    '<td>操作</td>' +
                    '</tr>';

                var pageInfo = data.data;
                var pageNum = pageInfo.pageNum;
                var pageSize = pageInfo.pageSize;
                var total = pageInfo.total;
                var pages = pageInfo.pages;

                var list = pageInfo.list;

                for (var i = 0; i < list.length; i++) {
                    var ticket = list[i];
                    var id = ticket.id;
                    var startStation = ticket.startStation;
                    var stopStation = ticket.stopStation;
                    var departureTime = ticket.departureTime;
                    var price = ticket.price;
                    var ticketNum = ticket.ticketNum;

                    html = html +
                        '<tr>' +
                        '<td>' + id + '</td>' +
                        '<td>' + startStation + '</td>' +
                        '<td>' + stopStation + '</td>' +
                        '<td>' + departureTime + '</td>' +
                        '<td>' + price + '</td>' +
                        '<td>' + ticketNum + '</td>' +
                        '<td align="center">' +
                        '<button onclick="buyTicket(' + id + ')">购买</button>' +
                        '</td>' +
                        '</tr>';
                }
                $("#ticketList").html(html);
                //注入分页对象的值
                $("#pageNum").html(pageNum);
                $("#pageSize").html(pageSize);
                $("#total").html(total);
                $("#pages").html(pages);
            },
            error: function (data) {
                /*alert("失败啦");*/
            }
        });
    }

    function buyTicket(id) {
        window.location.href='/ticket/buy_ticket?ticketId=' + id;
    }

    loadData(1, 5)
</script>
</html>
