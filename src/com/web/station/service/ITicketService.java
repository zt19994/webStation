package com.web.station.service;

import com.github.pagehelper.PageInfo;
import com.web.station.common.ServerResponse;

public interface ITicketService {

    /**
     * 返回车票列表，分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServerResponse<PageInfo> getTicketList(int pageNum, int pageSize);
}
