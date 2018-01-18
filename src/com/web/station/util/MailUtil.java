package com.web.station.util;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;


public class MailUtil {
    //设置服务器主机名
    private final static String HOST = "smtp.qq.com";
    //邮件协议信息
    private final static String PROTOCOL = "smtp";
    //发送人邮件地址
    private final static String SEND_ADDRESS = "191610942@qq.com";
    //授权码
    private final static String AUTHORIZATION_CODE = "geraspfawyeqcaei";


    /**
     * 发送邮件
     *
     * @param title          邮件标题
     * @param context           邮件内容
     * @param receiveAddress 收件人地址
     * @throws GeneralSecurityException
     * @throws MessagingException
     */
    public static void send(String title, String context, String receiveAddress) throws GeneralSecurityException, MessagingException {
        //可以加载一个配置文件
        Properties props = new Properties();
        //开启debug调试
        props.setProperty("mail.debug", "true");
        //发送服务器进行身份验证,使用smtp：简单邮件传输协议
        props.setProperty("mail.smtp.auth", "true");
        //存储发送邮件服务器信息
        props.setProperty("mail.host", HOST);
        //邮件发送协议
        props.setProperty("mail.transport.protocol", PROTOCOL);

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        //根据属性新建一个邮件会话
        Session session = Session.getInstance(props);
        //由邮件会话新建一个消息对象
        MimeMessage msg = new MimeMessage(session);
        //配置消息对象
        msg.setSubject(title);
        msg.setContent(context, "text/html; charset=utf-8");
        msg.setSentDate(new Date());
        //设置发件人地址
        msg.setFrom(new InternetAddress(SEND_ADDRESS));

        //发送邮件
        Transport transport = session.getTransport(PROTOCOL);
        //连接
        transport.connect(HOST, SEND_ADDRESS, AUTHORIZATION_CODE);
        //接收人地址
        Address[] addresses = {new InternetAddress(receiveAddress)};
        transport.sendMessage(msg, addresses);
        transport.close();
    }
}
