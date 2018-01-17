package com.web.station.service;

import com.web.station.common.ServerResponse;

public interface IUserService {

    /**
     * 发送手机验证码
     * @param param 验证码
     * @param phone 手机号
     * @return
     */
    ServerResponse sendMsgCode(String param, String phone);
}
