package com.web.station.service.impl;

import com.web.station.common.Config;
import com.web.station.common.Const;
import com.web.station.common.ServerResponse;
import com.web.station.dao.IUserDao;
import com.web.station.entity.User;
import com.web.station.service.IUserService;
import com.web.station.util.MD5Util;
import com.web.station.util.MailUtil;
import com.web.station.util.SendSMSUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

@Service
public class UserServiceImpl implements IUserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUserDao userDao;


    @Override
    public ServerResponse sendMsgCode(String param, String phone) {
        //1.调用发送短信验证码的工具类
        if (StringUtils.isBlank(phone)) {
            return ServerResponse.createByErrorMessage("未输入手机号码");
        }
        //SendSMSUtil.execute(param, phone);

        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse register(User user) {
        //1.校验用户名是否已经注册
        ServerResponse response = checkValid(user.getUserName(), Const.USERNAME);
        if (!response.isSuccess()) {
            //false,不成功，即已经注册，直接返回response
            return response;
        }
        //2.校验邮箱是否已经注册
        response = checkValid(user.getEmail(), Const.EMAIL);
        if (!response.isSuccess()) {
            //false,不成功，即已经注册，直接返回response
            return response;
        }
        //3.密码加密，MD5加密，调用工具类
        String encode = MD5Util.encode(user.getPassword());
        logger.info("encodePassword: " + encode);
        user.setPassword(encode);
        //4.设置状态: 0 未激活  1 激活
        user.setStatus(0);

        //5.设置邮件传递参数,加密,用MD5加密,为user封装ValidateCode属性
        String emailValidateCode = null;
        try {
            emailValidateCode = MD5Util.encode(user.getUserName()+user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setValidateCode(emailValidateCode);
        //6.进行注册
        int resultCount = userDao.register(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }

        logger.info("emailValidateCode: " + emailValidateCode);

        //7.发送认证邮件
        String context = "<p>欢迎您注册，请点击连接完成激活: </p><a href='" +
                Config.BASE_BODY + emailValidateCode + "'>" +
                Config.BASE_BODY + emailValidateCode + "</a>";

        try {
            MailUtil.send(Config.TITLE, context, user.getEmail());
        } catch (GeneralSecurityException | MessagingException e) {
            e.printStackTrace();
        }

        return ServerResponse.createBySuccessMessage("注册成功,请激活账号");
    }

    @Override
    public ServerResponse login(String userName, String password) {
        return null;
    }

    /**
     * 校验成功表示没有注册，返回的status是0,对应的isSuccess为true
     *
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse checkValid(String str, String type) {
        //1.首先判断str不为空
        if (StringUtils.isNotBlank(type)) {
            if (Const.USERNAME.equals(type)) {
                //1.校验注册时用户名是否存在
                int resultCount = userDao.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                //2.检查邮箱是否存在
                int resultCount = userDao.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    @Override
    public ServerResponse activate(String validateCode) {
        //1.判断validateCode是否为空
        if (StringUtils.isBlank(validateCode)){
            return ServerResponse.createByErrorMessage("验证码为空");
        }
        //2.在数据库查询是否有验证码
        User user = userDao.selectValidateCode(validateCode);
        if (user==null){
            return ServerResponse.createByErrorMessage("激活失败，验证码错误");
        }
        //3.验证码正确，进行激活
        user.setStatus(1);
        user.setValidateCode(null);
        int resultCount = userDao.activate(user);
        if (resultCount==0){
            return ServerResponse.createByErrorMessage("激活失败");
        }
        return ServerResponse.createBySuccessMessage("激活成功");
    }
}
