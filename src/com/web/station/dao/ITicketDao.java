package com.web.station.dao;

import com.web.station.Vo.TicketQueryVo;
import com.web.station.entity.Ticket;

import java.util.List;

public interface ITicketDao {

    /**
     * 查询车票列表
     * @return
     */
    List<Ticket> getTicketList(TicketQueryVo ticketQueryVo);

    /**
     * 通过ticketId查询车票信息
     * @param ticketId
     * @return
     */
    Ticket selectTicketById(Integer ticketId);

    /**
     * 更新车票信息
     * @param ticket
     * @return
     */
    int updateTicket(Ticket ticket);
}
