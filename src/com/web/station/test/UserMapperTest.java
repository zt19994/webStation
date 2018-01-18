package com.web.station.test;

import com.web.station.dao.IUserDao;
import com.web.station.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class UserMapperTest {

    @Autowired
    private IUserDao userDao;

    /**
    * 测试添加用户
    */
    @Test
    public void test(){
        User user = new User();
        user.setUserName("peter");
        user.setPassword("123");
        user.setPhone("17777777777");
        user.setStatus(1);
        user.setEmail("199999999@163.com");
        int resultCount = userDao.register(user);
        System.out.println(resultCount);
    }
}
