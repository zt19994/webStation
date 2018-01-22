package com.web.station.dao;

import com.web.station.entity.TicketOrder;

public interface ITicketOrderDao {

    /**
     * 添加订单
     * @param ticketOrder
     * @return
     */
    int addTicketOrder(TicketOrder ticketOrder);

    /**
     *通过orderNum查询订单信息
     * @param orderNum
     * @return
     */
    TicketOrder selectByOrderNum(String orderNum);


    /**
     * 更新车票信息
     * @param ticketOrder
     * @return
     */
    int updateOrder(TicketOrder ticketOrder);
}
