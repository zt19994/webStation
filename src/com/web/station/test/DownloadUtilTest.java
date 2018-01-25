package com.web.station.test;

import com.web.station.Vo.TicketQueryVo;
import com.web.station.dao.ITicketDao;
import com.web.station.entity.Ticket;
import com.web.station.util.DownloadUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class DownloadUtilTest {

    @Autowired
    private ITicketDao ticketDao;

    /**
    * 测试转化为string数组
    */
    @Test
    public void testGetValueRow() throws Exception {
        TicketQueryVo ticketQueryVo = new TicketQueryVo();
        List<Ticket> ticketList = ticketDao.getTicketList(ticketQueryVo);
        for (Ticket ticket : ticketList) {
            String[] valueRow = DownloadUtil.getValueRow(ticket);
            System.out.println(valueRow);

        }
    }
}
