package com.resthome.service;

import com.resthome.model.LogUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-07-16.
 */
public interface IUserService
{
    //登录
    String login(LogUser user);

    //註冊
    String register(LogUser user);

    //修改密码
    String updatePasswd(LogUser user);

    //查询指定用户
    LogUser selectByUserName(String userName);

    //查询所有用户
    List<LogUser> selectAll();

    //删除用户
    String delUser(String userName);

    //修改用户信息
    String updateUser(LogUser user);


}
