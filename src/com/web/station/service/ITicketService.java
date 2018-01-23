package com.web.station.service;

import com.github.pagehelper.PageInfo;
import com.web.station.Vo.TicketQueryVo;
import com.web.station.common.ServerResponse;

public interface ITicketService {

    /**
     * 返回车票列表，分页
     * @param ticketQueryVo
     * @return
     */
    ServerResponse<PageInfo> getTicketList(TicketQueryVo ticketQueryVo);

    /**
     * 通过ticketId获取车票详情
     * @param ticketId
     * @return
     */
    ServerResponse getTicketDetail(String ticketId);

    /**
     * 锁定车票
     * @param userId
     * @param ticketId
     * @param ticketNum
     * @return
     */
    ServerResponse lockTicket(String userId, String ticketId, String ticketNum);

    /**
     * 买票
     * @param orderNum
     * @return
     */
    ServerResponse buyTicket(String orderNum);
}
