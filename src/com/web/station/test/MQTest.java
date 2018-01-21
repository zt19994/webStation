package com.web.station.test;

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
        producerService.sendMessage("你好世界2","stationQueues");
        System.out.println("------测试完成-------");
    }
    @Test
    public void testReceive() throws JMSException {
        TextMessage stationQueues = consumerService.receive("stationQueues");
        String text = stationQueues.getText();

        System.out.println("------测试完成-------"+text);
    }

}
