package com.web.station.mqService;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class ConsumerService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQQueue activeMQQueue;
    /**
     * 接收消息
     */
    public TextMessage receive() {
        TextMessage tm = (TextMessage) jmsTemplate.receive(activeMQQueue);
        try {
            System.out.println("从队列" + activeMQQueue.toString() + "收到了消息：\t"
                    + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }
    /**
     * 接收消息
     */
    public TextMessage receive(String queueName) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(queueName);
        TextMessage tm = (TextMessage) jmsTemplate.receive(activeMQQueue);
        try {
            System.out.println("从队列" + activeMQQueue.toString() + "收到了消息：\t"
                    + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }

}
