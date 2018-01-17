package com.web.station.controller;

import com.web.station.common.Const;
import com.web.station.common.ServerResponse;
import com.web.station.entity.User;
import com.web.station.service.IUserService;
import com.web.station.util.RandomUtil;
import com.web.station.util.SendSMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {
    public static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("to_register")
    public ModelAndView toRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("to_login")
    public ModelAndView toLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public ServerResponse register(User user){

        return null;
    }


    /**
     * 获取短信验证码
     * @return
     */
    @RequestMapping("get_msg_code")
    @ResponseBody
    public ServerResponse getMsgCode(HttpSession session, String phone){
        //1.生成验证码
        String msgCode = RandomUtil.getMsgCode();
        logger.info("msgCode: " + msgCode);
        //2.把验证码保存到session
        session.setAttribute(Const.PHONE_MSG_CODE, msgCode);
        //3.调用业务方法发送验证码
        return userService.sendMsgCode(msgCode, phone);
    }
}
