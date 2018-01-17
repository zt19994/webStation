package com.web.station.service.impl;

import com.web.station.common.ServerResponse;
import com.web.station.service.IUserService;
import com.web.station.util.SendSMSUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{


    @Override
    public ServerResponse sendMsgCode(String param, String phone) {
        //1.调用发送短信验证码的工具类
        if (StringUtils.isBlank(phone)){
            return ServerResponse.createByErrorMessage("未输入手机号码");
        }
        SendSMSUtil.execute(param, phone);

        return ServerResponse.createBySuccess();
    }
}
