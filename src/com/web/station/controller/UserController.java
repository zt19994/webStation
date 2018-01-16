package com.web.station.controller;

import com.web.station.common.ServerResponse;
import com.web.station.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user/")
public class UserController {

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
}
