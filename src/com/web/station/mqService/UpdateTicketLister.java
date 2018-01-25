package com.web.station.mqService;

   import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.station.common.Config;
import com.web.station.common.ServerResponse;
import com.web.station.service.impl.TicketServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class UpdateTicketLister implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(UpdateTicketLister.class);

    @Autowired
    private TicketServiceImpl ticketService;

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String messageText = textMessage.getText();
                logger.info("接受队列消息，内容是：" + messageText);
                JSONObject jsonObject = JSON.parseObject(messageText);
                String typeNo = jsonObject.getString("typeNo");
                logger.info("typeNo: " + typeNo);
                String orderNum = jsonObject.getString("orderNum");
                logger.info("orderNum: " + orderNum);

                if (Config.MQ_UPDATE_TICKET_STATE.equals(typeNo)){
                    //如果typeNo等于updateTicketState，执行更新车票状态
                    ServerResponse response = ticketService.updateTicketState(orderNum);
                    logger.info("MQ_response: " + JSON.toJSONString(response));
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
