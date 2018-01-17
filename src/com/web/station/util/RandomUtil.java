package com.web.station.util;

/**
 * 生成6位随机数的工具类
 */
public class RandomUtil {

    public static String getMsgCode(){
        int random = (int)((Math.random() * 9 + 1) * 100000);
        return String.valueOf(random);
    }

}
