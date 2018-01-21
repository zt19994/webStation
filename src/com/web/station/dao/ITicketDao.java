package com.web.station.dao;

import com.web.station.entity.Ticket;

import java.util.List;

public interface ITicketDao {

    /**
     * 查询车票列表
     * @return
     */
    List<Ticket> getTicketList();

    /**
     * 通过ticketId查询车票信息
     * @param ticketId
     * @return
     */
    Ticket selectTicketById(Integer ticketId);
}
