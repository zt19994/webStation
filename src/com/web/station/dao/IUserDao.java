package com.web.station.dao;

import com.web.station.entity.User;
import org.apache.ibatis.annotations.Param;

public interface IUserDao {

    /**
     * 注册用户
     * @param user
     */
    int register(User user);

    /**
     * 查询用户是否已经注册
     * @param str
     * @return
     */
    int checkUsername(String str);

    /**
     * 查询邮箱是否已经注册
     * @param str
     * @return
     */
    int checkEmail(String str);

    /**
     * 查询验证码,返回用户
     * @param validateCode
     * @return
     */
    User selectValidateCode(String validateCode);

    /**
     * 激活操作
     * @param user
     * @return
     */
    int activate(User user);

    /**
     * 检查登录
     * @param userName
     * @param password
     * @return
     */
    User checkLogin(@Param("userName") String userName, @Param("password") String password);
}
