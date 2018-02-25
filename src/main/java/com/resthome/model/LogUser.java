package com.resthome.model;

/**
 * Created by 一缕仙缘 on 2017-06-19.
 */
public class LogUser
{
    /**
     * 用户
     */
    private String userName;      //用户名          本地数据库有该列——有

    private String userPassword;  //用户密码        本地数据库有该列——有

    private int isAdmin = -1;             //用户权限               本地数据库没有有该列——无

    private String number;    //用户工号              本地数据库没有有该列——无

    private String realName;  //用户姓名              本地数据库没有有该列——无

    private String sex;       //用户性别              本地数据库没有有该列——无

    private String idCard;   //用户身份证号          本地数据库没有有该列——无

    private String birthday; //用户生日              本地数据库没有有该列——无

    private String superior;  //添加该用户的管理员    本地数据库没有有该列——无

    @Override
    public String toString() {
        return "LogUser{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", isAdmin=" + isAdmin +
                ", number='" + number + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", idCard='" + idCard + '\'' +
                ", birthday='" + birthday + '\'' +
                ", superior='" + superior + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior;
    }
}
