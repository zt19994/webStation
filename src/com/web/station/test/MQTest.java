package com.web.station.test;

import com.alibaba.fastjson.JSONObject;
import com.web.station.common.Config;
import com.web.station.mqService.ConsumerService;
import com.web.station.mqService.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-activeMq.xml")
public class MQTest {
    @Autowired
    private ProducerService producerService;
    @Autowired
    private ConsumerService consumerService;


    @Test
    public void testSend(){
        JSONObject jsonObject = new JSONObject();
        String orderNum = "e60c96f0-f5f3-4269-84a7-8852f7f168de";
        jsonObject.put("orderNum",orderNum);
        jsonObject.put("typeNo", Config.MQ_UPDATE_TICKET_STATE);
        String jsonString = jsonObject.toString();
        producerService.sendMessage(jsonString,"updateTicketState");
       /* producerService.sendMessage("你好世界22222","stationQueues");
        producerService.sendMessage("你好世界33333","stationQueues");
        producerService.sendMessage("你好世界44444","stationQueues");*/

        System.out.println("------测试完成-------");
    }
    @Test
    public void testReceive() throws JMSException {
        TextMessage stationQueues = consumerService.receive("updateTicketState");
        String text = stationQueues.getText();

        System.out.println("------测试完成-------"+text);
    }

}
