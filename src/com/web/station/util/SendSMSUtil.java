package com.web.station.util;

import com.web.station.common.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送短信验证码的工具类
 */
public class SendSMSUtil {
    public static Logger logger = LoggerFactory.getLogger(SendSMSUtil.class);

    /**
     * 验证码通知短信，执行
     * @param param
     * @param phoneNum
     */
    public static void execute(String param, String phoneNum){
        //拼接url语句
        String url = Config.BASE_URL + Config.OPERATION;

        //  accountSid=a14f6bfd43ce44c9b019de57f4e2de4b&smsContent=【秒嘀科技】您的验证码是345678，30分钟输入有效。
        //  &to=13896543210&timestamp=20150821100312&sig=a14f6bfd43ue44c9b019du57f4e2ee4r&respDataType=JSON
        String body = "accountSid=" + Config.ACCOUNT_SID +
                "&templateid=" + Config.TEMPLATE_ID + "&param=" + param + "&to=" + phoneNum + HttpUtil.createCommonParam();

        //提交请求
        String result = HttpUtil.post(url, body);
        logger.info("result: " + result);
    }
}
