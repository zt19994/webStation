package com.web.station.mqService;

import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Service
public class ConsumerService {
    private static Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQQueue activeMQQueue;

    /**
     * 接收消息
     *
     * @return
     */
    public TextMessage receive() {
        TextMessage tm = (TextMessage) jmsTemplate.receive(activeMQQueue);
        try {
            logger.info("从队列" + activeMQQueue.toString() + "收到了消息：" + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }

    /**
     * 接收消息
     *
     * @param queueName
     * @return
     */
    public TextMessage receive(String queueName) {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(queueName);
        TextMessage tm = (TextMessage) jmsTemplate.receive(activeMQQueue);
        try {
            logger.info("从队列" + activeMQQueue.toString() + "收到了消息：" + tm.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return tm;
    }

}
