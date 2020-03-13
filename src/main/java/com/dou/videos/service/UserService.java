package com.dou.videos.service;

import com.dou.videos.dao.UserDao;
import com.dou.videos.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User类的service层
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    //注册
    public int insert(User user){
        return userDao.insert(user);
    }

    //登录
    public User login(String uname,String password){
        return userDao.login(uname, password);
    }

}
