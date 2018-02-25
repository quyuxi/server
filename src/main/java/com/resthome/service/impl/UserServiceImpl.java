package com.resthome.service.impl;

import com.resthome.dao.UserDao;
import com.resthome.model.LogUser;
import com.resthome.service.IUserService;
import com.resthome.util.DDLStatus;
import com.resthome.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-07-16.
 */
@Service
public class UserServiceImpl implements IUserService
{
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    /*登录 */
    @Override
    public String login(LogUser user)
    {
        LOGGER.info("-------------@ Begin to login------------------------------");
        try
        {
            LogUser logUser = userDao.selectByName(user.getUserName());
            if (logUser==null){return LoginUtil.NOPERMISSION;}
            if (logUser!=null && user.getUserPassword().equals(logUser.getUserPassword()))
            {
                LOGGER.info("-------------@ Login success------------------------------");
                if (logUser.getIsAdmin()==1)
                {
                    return LoginUtil.ADMINISTRATOR;
                }return LoginUtil.USERPERMIT;
            }

        }catch (Exception t)
        {
            LOGGER.error("-------------@Fail to login  "+t.getMessage(),t);
        }
        return LoginUtil.WRONGPASSWORD;
    }

    /*注册*/
    @Override
    public String register(LogUser user)
    {
        LOGGER.info("-------------@Begin to register -------------------------------");
        try
        {
            //先判断用户是否存在
            List<LogUser> users = userDao.selectAllUser();
            for (LogUser u:users)
            {
                if (user.getUserName().equals(u.getUserName()))
                {
                    return DDLStatus.REPEATNAME;
                }

            }

            if ( userDao.addUser(user))
            {
                LOGGER.info("-------------@Register success-------------------------------");
                return DDLStatus.ADD_SUCCESS;
            }

        }catch (Exception e)
        {
            LOGGER.error("-------------@Register failed "+e.getMessage(),e);
        }
        return DDLStatus.ADD_FAILD;
    }

    /*修改密码*/
    @Override
    public String updatePasswd(LogUser user)
    {
        LOGGER.info("--------------@Begin to update paasword-------- ----");
        try
        {
            if (userDao.updatePasswd(user))
            {
                LOGGER.info("--------------@Update paasword success-------- ----");
                return DDLStatus.UPDATE_SUCCESS;
            }

        }catch (Exception e)
        {
            LOGGER.error("--------------@Update password failed "+e.getMessage(),e);
        }

        return DDLStatus.UPDATE_FAILD;
    }

    /*根据姓名查询用户*/
    @Override
    public LogUser selectByUserName(String userName)
    {
        LOGGER.info("--------------@Begin to  select user-------- ----");
        LogUser user=null;
        try
        {
             user = userDao.selectByName(userName);
             if (user==null)
             {
                 LOGGER.error("--------------@Selected user not exist-----------------");
                 return null;
             }
            LOGGER.info("@Select user success , and the user is:"+user);

        }catch (Exception e)
        {
            LOGGER.error("--------------@Select loginUser by userName ="+userName+" failed,"+e.getMessage(),e);
        }
        return user;
    }

    /*查询所有用户*/
    @Override
    public List<LogUser> selectAll()
    {
        LOGGER.info("--------------@Begin to  select userList-------- ----");
        List<LogUser> list=null;
        try
        {
            list = userDao.selectAllUser();
            if (list==null||list.size()==0)
            {
                return null;
            }
            LOGGER.info("select all  users list = "+list);
        }catch (Exception e)
        {
            LOGGER.error("--------------@Fail to  select userList--------",e);

        }
        return list;
    }

    @Override
    public String delUser(String userName)
    {
        LOGGER.info("---------@Begin to delete user ----");
        String result = DDLStatus.DEL_FAILD;
        try
        {
            if (userDao.delUser(userName))
            {
                result=DDLStatus.DEL_SUCCESS;
                LOGGER.info("----------@Delete success ,the userName="+userName);
            }

        }catch (Exception e)
        {
            LOGGER.error("delete user faild ",e);
        }


        return result;
    }

    @Override
    public String updateUser(LogUser user)
    {
        LOGGER.info("----update user begin----");
        String result = DDLStatus.UPDATE_FAILD;
        try
        {
            boolean b = userDao.updateUser(user);
            if (b)
            {
                result=DDLStatus.UPDATE_SUCCESS;
                LOGGER.info("update success ,the user:"+user);
            }

        }catch (Exception e)
        {
            LOGGER.error("update faild,the error message:"+e.getMessage(),e);
        }
        return result;
    }
}
