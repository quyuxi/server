package com.resthome.dao;

import com.resthome.model.LogUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-06-19.
 */
@Repository
public interface UserDao
{
    //add
    boolean addUser(LogUser user);

    //update
    boolean updateUser(LogUser user);

    //del by  name
    boolean delUser(String userName);

    //select by name
    LogUser selectByName(String name);

    //分页查询
    //List<LogUser> selectList(@Param("index") Integer index, @Param("pageCount") Integer pageCount);

    //查询所有用户信息
    List<LogUser> selectAllUser();

    //修改密码
    boolean updatePasswd(LogUser user);
}
