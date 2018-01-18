package com.web.station.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ticket/")
public class TicketController {

    @RequestMapping("to_ticket")
    public ModelAndView toTicketList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticketList");
        return modelAndView;
    }
}
