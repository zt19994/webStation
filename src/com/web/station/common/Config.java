package com.web.station.common;

/**
 * 配置文件
 */
public class Config {
    /**
     * 请求地址的前半部分,不变的
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822";

    /**
     * 开发者账号ID
     */
    public static final String ACCOUNT_SID = "e3e7ff6afb77460fbe6e14e045ac70bb";

    /**
     * 开发者的token,注册后自动生成
     */
    public static final String AUTH_TOKEN = "1ea291f0184440c2b6471ad17fe707ab";

    /**
     * 响应数据类型，JSON 或 XML
     */
    public static final String RESP_DATA_TYPE = "json";

    /**
     * 执行的操作
     *  //https://api.miaodiyun.com/20150822/industrySMS/sendSMS
     */
    public static final String OPERATION = "/industrySMS/sendSMS";

    /**
     * 模板的id号
     */
    public static final String TEMPLATE_ID = "106266266";
}
