package com.web.station.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.day_28.station.cxfservice.ITicketCXFService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring_cxfClient.xml")
public class TestStationClient {

    @Autowired
    private ITicketCXFService ticketCXFService;

    /**
     * 测试client
     */
    @Test
    public void testClient() {

        //调用方法
        //userId,ticketId,ticketNum
        String userId = "4";
        String ticketId = "5";
        String ticketNum = "1";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("ticketId", ticketId);
        jsonObject.put("ticketNum", ticketNum);
        String jsonString = jsonObject.toJSONString();
        String lockTicket = ticketCXFService.lockTicket(jsonString);

        System.out.println(lockTicket);
        /*{"code":"0000","data":"e60c96f0-f5f3-4269-84a7-8852f7f168de","msg":"锁票成功","success":true}*/
        //String lockDate = "{\"code\":\"0000\",\"data\":\"e60c96f0-f5f3-4269-84a7-8852f7f168de\",\"msg\":\"锁票成功\",\"success\":true}";
        JSONObject parseObject = JSON.parseObject(lockTicket);
        String orderNum = parseObject.getString("data");
        System.out.println(orderNum);

        jsonObject.put("orderNum", orderNum);
        String s = jsonObject.toJSONString();
        System.out.println(s);

        String buyTicket = ticketCXFService.buyTicket(s);

        System.out.println(buyTicket);

        System.out.println("执行完毕");

        /*
        {"code":"0000","data":"842d1bb7-c54d-4716-a02f-a42a498fcf2d","msg":"锁票成功","success":true}
        842d1bb7-c54d-4716-a02f-a42a498fcf2d
        {"ticketNum":"1","orderNum":"842d1bb7-c54d-4716-a02f-a42a498fcf2d","userId":"4","ticketId":"5"}
        {"code":"0000","msg":"购票成功","success":true}
        */


    }
}
