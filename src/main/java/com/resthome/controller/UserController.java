package com.resthome.controller;

import com.resthome.annotation.SerializedField;
import com.resthome.model.LogUser;
import com.resthome.service.IUserService;
import com.resthome.util.DDLStatus;
import com.resthome.util.LogUserCheck;
import com.resthome.util.LoginUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-07-17.
 */
@RestController
@RequestMapping("/resthome")
public class UserController
{

    @Autowired
    IUserService userService;
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    //1.登录
    /*登录返回值不需要加密*/
    @SerializedField
    @RequestMapping(value = "/login",consumes = "application/json",method = RequestMethod.POST)
    public String login(@RequestBody LogUser user)
    {
        //1.1检查数据
        if (ObjectUtils.isEmpty(user)
                ||StringUtils.isEmpty(user.getUserPassword())
                ||StringUtils.isEmpty(user.getUserName()))
        {
            LOGGER.error("------------@Login user is empty--------------------------");
            return LoginUtil.NOPERMISSION;
        }
        //1.2执行登录
        return userService.login(user);
    }

    //2.注册
    /*注册返回值也不需要加密*/
    @SerializedField
    @RequestMapping(value = "/register",consumes = "application/json",method = RequestMethod.POST)
    public String register(@RequestBody LogUser user)
    {
        //2.1检查参数，如果存在控制返回false
        if (ObjectUtils.isEmpty(user)||LogUserCheck.checkElderHasEmptyAttribute(user))
        {
            LOGGER.error("--------------@The register user has null attribute--------------------------------");
            return DDLStatus.ADD_FAILD;
        }
        //2.2替换生日中/为-
        if(user.getBirthday().contains("/"))
        {
            String  birthday = user.getBirthday().replaceAll("/", "-");
            user.setBirthday(birthday);
        }
        return userService.register(user);
    }

    //3.修改个人密码
     /*注册返回值也不需要加密*/
    @SerializedField
    @RequestMapping(value = "/modify",consumes = "application/json",method = RequestMethod.POST)
    public String modifyPassword(@RequestBody LogUser user)
    {
        if (ObjectUtils.isEmpty(user)||StringUtils.isEmpty(user.getUserPassword()))
        {
            LOGGER.error("--------------@The user is empty or passwd is null------------");
            return DDLStatus.UPDATE_FAILD;
        }
        return userService.updatePasswd(user);
    }

    //4.查询指定用户
    @SerializedField//返回數據加密
    @RequestMapping(value = "/find/{userName}",method = RequestMethod.GET)
    public Object findUser(@PathVariable String userName)
    {
        if (StringUtils.isEmpty(userName))
        {
            return null;
        }
        LogUser logUser = userService.selectByUserName(userName);
        if (logUser==null)
        {
            return null;
        }
        return JSONObject.fromObject(logUser);
    }

    //5.查询所有用户
    @SerializedField
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public JSONArray findAllList()
    {
        List<LogUser> logUsers = userService.selectAll();
        if (logUsers==null)
        {
            return null;
        }
        return JSONArray.fromObject(logUsers);
    }

    //6.删除用户
    @SerializedField
    @RequestMapping(value = "/delUser/{userName}",method = RequestMethod.GET)
    public  String del(@PathVariable("userName") String userName)
    {
        if (StringUtils.isEmpty(userName))
        {
            LOGGER.error("the userName is null");
            return null;
        }
        return userService.delUser(userName);
    }

    //7.修改用户信息
    @SerializedField
    @RequestMapping(value = "/modifyUser",method = RequestMethod.POST,consumes = "application/json")
    public  String update(@RequestBody LogUser user)
    {
        LOGGER.info("@The update user is:"+JSONObject.fromObject(user));
        //检查参数，如果存在控制返回false
        if (ObjectUtils.isEmpty(user)||LogUserCheck.checkElderHasEmptyAttribute(user))
        {
            LOGGER.error("--------------@The update user has null attribute--------------------------------");
            return DDLStatus.ADD_FAILD;
        }
        return userService.updateUser(user);

    }

}
