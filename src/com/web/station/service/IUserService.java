package com.web.station.service;

import com.web.station.common.ServerResponse;
import com.web.station.entity.User;

public interface IUserService {
    /**
     * 发送手机验证码
     *
     * @param param 验证码
     * @param phone 手机号
     * @return
     */
    ServerResponse sendMsgCode(String param, String phone);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    ServerResponse register(User user);

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    ServerResponse login(String userName, String password);

    /**
     * 校验用户名和邮箱
     * @param str
     * @param type
     * @return
     */
    ServerResponse checkValid(String str, String type);
}
