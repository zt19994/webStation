package com.web.station.util;

import java.util.Objects;

public class ToEmailUtil {

    public static String toEmail(String mail){
        mail = mail.toLowerCase();
        if (Objects.equals(mail, "163.com")) {
            return "mail.163.com";
        } else if (Objects.equals(mail, "vip.163.com")) {
            return "vip.163.com";
        } else if (Objects.equals(mail, "126.com")) {
            return "mail.126.com";
        } else if (Objects.equals(mail, "qq.com") || Objects.equals(mail, "vip.qq.com") || Objects.equals(mail, "foxmail.com")) {
            return "mail.qq.com";
        } else if (Objects.equals(mail, "gmail.com")) {
            return "mail.google.com";
        } else if (Objects.equals(mail, "sohu.com")) {
            return "mail.sohu.com";
        } else if (Objects.equals(mail, "tom.com")) {
            return "mail.tom.com";
        } else if (Objects.equals(mail, "vip.sina.com")) {
            return "vip.sina.com";
        } else if (Objects.equals(mail, "sina.com.cn") || Objects.equals(mail, "sina.com")) {
            return "mail.sina.com.cn";
        } else if (Objects.equals(mail, "tom.com")) {
            return "mail.tom.com";
        } else if (Objects.equals(mail, "yahoo.com.cn") || Objects.equals(mail, "yahoo.cn")) {
            return "mail.cn.yahoo.com";
        } else if (Objects.equals(mail, "tom.com")) {
            return "mail.tom.com";
        } else if (Objects.equals(mail, "yeah.net")) {
            return "www.yeah.net";
        } else if (Objects.equals(mail, "21cn.com")) {
            return "mail.21cn.com";
        } else if (Objects.equals(mail, "hotmail.com")) {
            return "www.hotmail.com";
        } else if (Objects.equals(mail, "sogou.com")) {
            return "mail.sogou.com";
        } else if (Objects.equals(mail, "188.com")) {
            return "www.188.com";
        } else if (Objects.equals(mail, "139.com")) {
            return "mail.10086.cn";
        } else if (Objects.equals(mail, "189.cn")) {
            return "webmail15.189.cn/webmail";
        } else if (Objects.equals(mail, "wo.com.cn")) {
            return "mail.wo.com.cn/smsmail";
        } else if (Objects.equals(mail, "139.com")) {
            return "mail.10086.cn";
        } else {
            return "";
        }
    }


    public static void main(String[] args) {
        String email = toEmail("qq.com");
        System.out.println(email);
    }
}
