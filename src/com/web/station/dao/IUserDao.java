package com.web.station.dao;

import com.web.station.entity.User;

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
}
