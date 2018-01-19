package com.web.station.controller;

import com.web.station.common.ServerResponse;
import com.web.station.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ticket/")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    /**
     * 跳转到车票页面
     *
     * @return
     */
    @RequestMapping("to_ticket")
    public ModelAndView toTicketList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticketList");
        return modelAndView;
    }


    /**
     * 获取车票列表数据
     *
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ServerResponse getTicketList(int pageNum, int pageSize) {
        return ticketService.getTicketList(pageNum, pageSize);
    }
}
