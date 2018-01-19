package com.web.station.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.station.common.ServerResponse;
import com.web.station.dao.ITicketDao;
import com.web.station.entity.Ticket;
import com.web.station.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<Ticket> ticketList = ticketDao.getTicketList();
        //3.pageHelper的收尾
        PageInfo pageResult = new PageInfo(ticketList);
        pageResult.setList(ticketList);
        return ServerResponse.createBySuccess(pageResult);
    }
}
