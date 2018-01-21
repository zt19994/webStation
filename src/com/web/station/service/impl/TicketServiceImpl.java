package com.web.station.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.station.common.ServerResponse;
import com.web.station.dao.ITicketDao;
import com.web.station.entity.Ticket;
import com.web.station.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {

    @Autowired
    private ITicketDao ticketDao;

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
        PageInfo pageResult = new PageInfo(lists);
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
}
