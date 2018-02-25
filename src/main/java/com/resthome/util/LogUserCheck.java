package com.resthome.util;

import com.resthome.model.LogUser;
import org.springframework.util.StringUtils;

/**
 * Created by 一缕仙缘 on 2017-07-18.
 */
public class LogUserCheck
{
    //检查是否每个属性都有值
    /*
       private String userName;      //用户名          本地数据库有该列——有

    private String userPassword;  //用户密码        本地数据库有该列——有

    private int isAdmin = -1;             //用户权限               本地数据库没有有该列——无

    private String number;    //用户工号              本地数据库没有有该列——无

    private String realName;  //用户姓名              本地数据库没有有该列——无

    private String sex;       //用户性别              本地数据库没有有该列——无

    private String idCard;   //用户身份证号          本地数据库没有有该列——无

    private String birthday; //用户生日              本地数据库没有有该列——无

    private String superior;  //添加该用户的管理员    本地数据库没有有该列——无
     */

    public static boolean checkElderHasEmptyAttribute(LogUser user)
    {
        //这里先不用判断权限
        if (StringUtils.isEmpty(user.getUserName())||StringUtils.isEmpty(user.getUserPassword())
                ||StringUtils.isEmpty(user.getNumber())||StringUtils.isEmpty(user.getRealName())
                ||StringUtils.isEmpty(user.getSex())||StringUtils.isEmpty(user.getIdCard())
                ||StringUtils.isEmpty(user.getBirthday())||StringUtils.isEmpty(user.getSuperior())
                )
        {
            return true;
        }

        return false;
    }

}
