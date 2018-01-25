package com.web.station.controller;

import com.web.station.common.Const;
import com.web.station.common.ServerResponse;
import com.web.station.entity.User;
import com.web.station.service.IUserService;
import com.web.station.util.RandomUtil;
import com.web.station.util.ToEmailUtil;
import com.web.station.util.ValidateCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user/")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
     * 跳转到激活页面
     * @return
     */
    @RequestMapping("to_activate")
    public ModelAndView toActivate(String mail){
        logger.info("mail: " + mail);
        ModelAndView modelAndView = new ModelAndView();
        //调用方法比较mail
        String emailUrl = ToEmailUtil.toEmail(mail);
        modelAndView.addObject(Const.EMAIL_URL,emailUrl);
        modelAndView.setViewName("activate");
        return modelAndView;
    }

    /**
     * 跳转到设置页面
     * @return
     */
    @RequestMapping("to_set")
    public ModelAndView toSet(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setPage");
        return modelAndView;
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public ServerResponse register(HttpSession session, User user, String phoneValidateCode){
        String code = (String) session.getAttribute(Const.REGISTER_PHONE_VALIDATE_CODE);
        //判断验证码不为空
        if (StringUtils.isBlank(phoneValidateCode)){
            return ServerResponse.createByErrorMessage("验证码为空");
        }
        if (!phoneValidateCode.equals(code)){
            ////验证码不相等
            return ServerResponse.createByErrorMessage("验证码错误");
        }
        return userService.register(user);
    }

    /**
     * 登录
     * @param session
     * @param userName
     * @param password
     * @param validateCode
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public ServerResponse login(HttpSession session, String userName, String password, String validateCode){
        String code = (String) session.getAttribute(Const.LOGIN_VALIDATE_CODE);
        logger.info("validateCodeLogin: " +  validateCode);
        //忽略验证码的大小写
        if (!code.equals(validateCode.toUpperCase())){
            //验证码不相等
            return ServerResponse.createByErrorMessage("验证码错误");
        }
        // 调用service方法，返回用户是否登录成功
        ServerResponse response = userService.login(userName, password);
        if (response.isSuccess()){
            //登录成功，将用户信息放入session
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 注销操作
     * @param session
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
    public ServerResponse logout(HttpSession session){
        //删除用户session信息
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
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
        session.setAttribute(Const.REGISTER_PHONE_VALIDATE_CODE, msgCode);
        //3.调用业务方法发送验证码
        return userService.sendMsgCode(msgCode, phone);
    }


    /**
     * 获取图片验证码
     * @param response
     * @param session
     */
    @RequestMapping("get_img_code")
    public void getValidateCodeImg(HttpServletResponse response, HttpSession session){
        //1.设置响应格式
        response.setContentType("image/jpeg");
        //2.禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //3.生成验证码，调用工具类
        ValidateCodeUtil validateCode = new ValidateCodeUtil(100,25,4,5);
        //4.把验证码放入session
        session.setAttribute(Const.LOGIN_VALIDATE_CODE, validateCode.getCode());
        logger.info("validateCode: "  + validateCode.getCode());
        //5.向页面输出图片
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 激活账号
     * @param validateCode
     * @return
     */
    @RequestMapping("activate")
    public ModelAndView activate(String validateCode){
        ServerResponse resultCount = userService.activate(validateCode);
        ModelAndView modelAndView = new ModelAndView();
        if (!resultCount.isSuccess()){
            //激活失败，返回注册页面
            modelAndView.setViewName("register");
            return modelAndView;
        }
        //到注册页面
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
