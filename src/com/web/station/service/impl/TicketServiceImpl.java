package com.web.station.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.day_28.station.cxfservice.ITicketCXFService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.station.common.ServerResponse;
import com.web.station.dao.ITicketDao;
import com.web.station.dao.ITicketOrderDao;
import com.web.station.entity.Ticket;
import com.web.station.entity.TicketOrder;
import com.web.station.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketDao ticketDao;

    @Autowired
    private ITicketCXFService ticketCXFService;

    @Autowired
    private ITicketOrderDao ticketOrderDao;

    @Override
    public ServerResponse<PageInfo> getTicketList(int pageNum, int pageSize) {
        //pageHelper的使用方法，三步
        //1.startPage,记录一个开始
        PageHelper.startPage(pageNum, pageSize);
        //2.填充自己的sql查询逻辑
        List<Ticket> lists = new ArrayList<>();
        List<Ticket> ticketList = ticketDao.getTicketList();
        for (Ticket ticket : ticketList) {
            String departureTime = ticket.getDepartureTime();
            String substring = departureTime.substring(0, 19);
            ticket.setDepartureTime(substring);
            lists.add(ticket);
        }
        //3.pageHelper的收尾
        PageInfo pageResult = new PageInfo(ticketList);
        pageResult.setList(lists);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse getTicketDetail(String ticketId) {
        Ticket ticket = ticketDao.selectTicketById(Integer.valueOf(ticketId));
        String departureTime = ticket.getDepartureTime();
        String substring = departureTime.substring(0, 19);
        ticket.setDepartureTime(substring);
        return ServerResponse.createBySuccess("查询成功", ticket);
    }

    @Override
    public ServerResponse lockTicket(String userId, String ticketId, String ticketNum) {
        //1.封装json数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId",userId);
        jsonObject.put("ticketId",ticketId);
        jsonObject.put("ticketNum",ticketNum);
        String jsonString = jsonObject.toJSONString();

        //2.调用车站发布的锁票接口
        String lockTicket = ticketCXFService.lockTicket(jsonString);
        JSONObject parseObject = JSON.parseObject(lockTicket);
        String code = parseObject.getString("code");
        if (!"0000".equals(code)){
            //锁票失败
            return ServerResponse.createByErrorMessage("购票失败，余票不足");
        }
        String orderNum = parseObject.getString("data");

        //3.锁定自己的车票
        //a.通过ticketId查询车票信息
        Ticket ticket = ticketDao.selectTicketById(Integer.valueOf(ticketId));
        Integer num = ticket.getTicketNum();
        if (num<Integer.valueOf(ticketNum)){
            return ServerResponse.createByErrorMessage("余票不足");
        }
        num = num - Integer.valueOf(ticketNum);
        //b.更新车票信息
        ticket.setTicketNum(num);
        int i = ticketDao.updateTicket(ticket);
        if (i==0){
            return ServerResponse.createByErrorMessage("更新车票失败");
        }
        //4.生成订单, ticketId, userId, ticketNum购买数量, orderNum订单编号
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setTicketId(Integer.valueOf(ticketId));
        ticketOrder.setUserId(Integer.valueOf(userId));
        //购买张数
        ticketOrder.setNum(Integer.valueOf(ticketNum));
        //状态0为未支付
        ticketOrder.setState(0);
        ticketOrder.setOrderNum(orderNum);

        int addTicketOrder = ticketOrderDao.addTicketOrder(ticketOrder);
        if (addTicketOrder==0){
            return ServerResponse.createByErrorMessage("生成订单失败");
        }

        //5.锁票成功，传递orderNum
        return  ServerResponse.createBySuccess("锁票成功", orderNum);
    }

    @Override
    public ServerResponse buyTicket(String orderNum) {
        //1.将orderNum转化为json格式
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNum",orderNum);
        String jsonString = jsonObject.toString();

        //2.调用车站发布的买票接口
        String buyTicket = ticketCXFService.buyTicket(jsonString);
        JSONObject parseObject = JSON.parseObject(buyTicket);
        String code = parseObject.getString("code");
        if (!"0000".equals(code)){
            //买票失败
            return ServerResponse.createByErrorMessage("买票失败");
        }

        //3.修改自己的订单状态
        TicketOrder ticketOrder = ticketOrderDao.selectByOrderNum(orderNum);
        ticketOrder.setState(1);
        int i = ticketOrderDao.updateOrder(ticketOrder);
        if (i==0){
            return ServerResponse.createByErrorMessage("购买失败");
        }
        return ServerResponse.createBySuccess("购票成功");
    }


}
