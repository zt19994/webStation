package com.web.station.test;

import com.web.station.common.Config;

public class EmailContextTest {

    public static void main(String[] args) {
        String context = "<p>欢迎您注册，请点击连接完成激活: </p><a href='" +
                Config.BASE_BODY + 123 + "'>" +
                Config.BASE_BODY + 123 + "</a>";

        System.out.println(context);
    }
}
