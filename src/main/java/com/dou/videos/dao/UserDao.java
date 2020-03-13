package com.dou.videos.dao;

import com.dou.videos.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * User接口
 */
@Mapper
@Repository
public interface UserDao {

    //注册
    int insert(User user);

    //登录
    User login(@Param("uname")String uname,@Param("password")String password);
}
