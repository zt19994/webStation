package com.web.station.test;

import com.web.station.util.SendSMSUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendSMSUtilTest {
    public static Logger logger = LoggerFactory.getLogger(SendSMSUtilTest.class);
    /**
    * 测试发送短信验证码
    */
    @Test
    public void testSendSMS(){
        String param="123456";
        String phone = "17736817302";
        SendSMSUtil.execute(param, phone);
        logger.info("执行完毕");
    }
}
