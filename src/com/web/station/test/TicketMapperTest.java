package com.web.station.test;

import com.web.station.dao.ITicketDao;
import com.web.station.entity.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class TicketMapperTest {

    @Autowired
    private ITicketDao ticketDao;


    /**
    * 测试查询车票列表
    */
    @Test
    public void testGetTicketList(){
        List<Ticket> ticketList = ticketDao.getTicketList();
        for (Ticket ticket : ticketList) {
            System.out.println(ticket);
        }
    }
}
