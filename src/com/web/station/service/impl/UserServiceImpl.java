package com.web.station.service.impl;

import com.web.station.common.Config;
import com.web.station.common.Const;
import com.web.station.common.ServerResponse;
import com.web.station.dao.IUserDao;
import com.web.station.entity.User;
import com.web.station.service.IUserService;
import com.web.station.util.MD5Util;
import com.web.station.util.MailUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

@Service
public class UserServiceImpl implements IUserService {
    public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        //3.MD5加密，调用工具类
        String encode = MD5Util.encode(user.getUserName());
        logger.info("encodePassword: " + encode);
        user.setPassword(encode);
        //4.设置状态: 0 未激活  1 激活
        user.setStatus(0);

        //5.进行注册
        int resultCount = userDao.register(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        //设置邮件传递参数,加密,用邮箱加密
        String emailValidateCode = null;
        try {
            emailValidateCode = MD5Util.encode(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("emailValidateCode: " + emailValidateCode);

        //发送认证邮件
        try {
            MailUtil.send(Config.TITLE, Config.BASE_BODY + emailValidateCode +
                    "&email=" + user.getEmail() + "&user=" + user.getUserName(), user.getEmail());
        } catch (GeneralSecurityException | MessagingException e) {
            e.printStackTrace();
        }

        return ServerResponse.createBySuccessMessage("注册成功");
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
}
