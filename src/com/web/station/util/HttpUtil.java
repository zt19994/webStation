package com.web.station.util;

import com.web.station.common.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Http工具请求类
 */
public class HttpUtil {
    public static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 创建公共参数,包括 accountSid, timestamp, sig, respDataType
     * @return
     */
    public static String createCommonParam(){
        String param = null;
        //时间戳格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //生成当前时间
        Date date = new Date();
        //生成sdf的时间格式
        String timestamp = sdf.format(date);

        //签名 MD5(ACCOUNT SID + AUTH TOKEN + timestamp)
        String sig = DigestUtils.md5Hex(Config.ACCOUNT_SID + Config.AUTH_TOKEN + timestamp);

        //根据API文档,拼接公共参数
        //&timestamp=20150821100312&sig=a14f6bfd43ue44c9b019du57f4e2ee4r&respDataType=JSON
        param = "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + Config.RESP_DATA_TYPE;
        return param;
    }


    public static String post(String url, String body){
        logger.info("HttpUtil_url: " + url);
        logger.info("HttpUtil_body: " + body);
        String result = "";
        try {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            //转换url
            URL realUrl = new URL(url);
            //连接
            URLConnection conn = realUrl.openConnection();

            //设置连接参数
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //超时设置
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);

            //提交数据
            OutputStream outputStream = conn.getOutputStream();
            out = new OutputStreamWriter(outputStream, "utf-8");
            out.write(body);
            out.flush();

            //读取返回数据
            InputStream inputStream = conn.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, "utf-8");
            in = new BufferedReader(reader);

            String line = "";
            boolean firstLine = true; // 读第一行不加换行符
            while ((line = in.readLine()) != null){
                if (firstLine){
                    firstLine = false;
                }else {
                    result += System.lineSeparator();
                }
                result +=line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
