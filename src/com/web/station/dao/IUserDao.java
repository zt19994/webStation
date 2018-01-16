package com.web.station.dao;

import com.web.station.entity.User;

public interface IUserDao {

    /**
     * 注册用户
     * @param user
     */
    int register(User user);
}
