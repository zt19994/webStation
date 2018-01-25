package com.web.station.controller;

import com.web.station.Vo.TicketQueryVo;
import com.web.station.common.Const;
import com.web.station.common.ServerResponse;
import com.web.station.entity.User;
import com.web.station.service.ITicketService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ticket/")
public class TicketController {
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

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
     * 跳转到购票页面
     *
     * @return
     */
    @RequestMapping("buy_ticket")
    public ModelAndView toBuyTicket(String ticketId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("buyTicket");
        modelAndView.addObject("ticketId", ticketId);
        return modelAndView;
    }

    /**
     * 跳转到支付页面
     * @param orderNum
     * @return
     */
    @RequestMapping("pay_page")
    public ModelAndView toPayPage(String orderNum) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payPage");
        modelAndView.addObject("orderNum", orderNum);
        return modelAndView;
    }

    /**
     * 获取车票列表数据
     *
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public ServerResponse getTicketList(HttpSession session, TicketQueryVo ticketQueryVo) {
        //1.校验用户是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("未登录，请登录");
        }
        return ticketService.getTicketList(ticketQueryVo);
    }

    /**
     * 获取车票详情
     * @param ticketId
     * @return
     */
    @RequestMapping("ticket_detail")
    @ResponseBody
    public ServerResponse getTicketDetail(String ticketId){
        return ticketService.getTicketDetail(ticketId);
    }

    /**
     * 锁定车票
     * @param userId
     * @param ticketId
     * @param ticketNum
     * @return
     */
    @RequestMapping("lock_ticket")
    @ResponseBody
    public ServerResponse lockTicket(HttpSession session, String userId, String ticketId, String ticketNum){
        //1.校验用户是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("未登录，请登录");
        }
        return ticketService.lockTicket(userId, ticketId, ticketNum);
    }


    /**
     * 支付信息
     * @param orderNum
     * @return
     */
    @RequestMapping("pay_ticket")
    @ResponseBody
    public ServerResponse payTicket(HttpSession session, String orderNum){
        //1.校验用户是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("未登录，请登录");
        }
        return ticketService.buyTicket(orderNum);
    }

    @RequestMapping("to_export.xls")
    @ResponseBody
    public String toExport(HttpServletResponse response, String pFileName) throws UnsupportedEncodingException {

        ServerResponse serverResponse = ticketService.downloadFile(response);
        return serverResponse.getMsg();
    }
}
