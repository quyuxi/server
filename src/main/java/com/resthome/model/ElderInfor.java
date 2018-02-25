package com.resthome.model;

/**
 * Created by 一缕仙缘 on 2017-06-18.
 */
public class ElderInfor
{
    /*[DataMember(Order = 0, IsRequired = true)]
    public string elderID { get; set; }          //编号
        [DataMember(Order = 1, IsRequired = true)]
    public string elderName { get; set; }        //姓名
        [DataMember(Order = 2, IsRequired = true)]
    public int elderAge { get; set; }            //年龄
        [DataMember(Order = 3, IsRequired = true)]
    public string elderSex { get; set; }         //性别
        [DataMember(Order = 4, IsRequired = true)]
    public string elderChilde { get; set; }      //监护人
        [DataMember(Order = 5, IsRequired = true)]
    public string elderChildNumber { get; set; } //监护人联系方式
        [DataMember(Order = 6, IsRequired = true)]
    public string elderArea { get; set; }        //安全活动范围*/

    private  String elderID;
    private  String  elderName;
    private  String elderBirthday;
    private  String  elderSex;
    private  String  elderChild;
    private  String  elderChildNumber;
    private  String  elderArea;

    public ElderInfor()
    {
    }

    public ElderInfor(String elderID, String elderName, String elderBirthday, String elderSex, String elderChild, String elderChildNumber, String elderArea)
    {
        this.elderID = elderID;
        this.elderName = elderName;
        this.elderBirthday = elderBirthday;
        this.elderSex = elderSex;
        this.elderChild = elderChild;
        this.elderChildNumber = elderChildNumber;
        this.elderArea = elderArea;
    }

    @Override
    public String toString()
    {
        return "ElderInfor{" +
                "elderID='" + elderID + '\'' +
                ", elderName='" + elderName + '\'' +
                ", elderBirthday=" + elderBirthday +
                ", elderSex='" + elderSex + '\'' +
                ", elderChild='" + elderChild + '\'' +
                ", elderChildNumber='" + elderChildNumber + '\'' +
                ", elderArea='" + elderArea + '\'' +
                '}';
    }

    public String getElderID() {
        return elderID;
    }

    public void setElderID(String elderID) {
        this.elderID = elderID;
    }

    public String getElderName() {
        return elderName;
    }

    public void setElderName(String elderName) {
        this.elderName = elderName;
    }

    public String getElderBirthday() {
        return elderBirthday;
    }

    public void setElderBirthday(String elderBirthday) {
        this.elderBirthday = elderBirthday;
    }

    public String getElderSex() {
        return elderSex;
    }

    public void setElderSex(String elderSex) {
        this.elderSex = elderSex;
    }

    public String getElderChild() {
        return elderChild;
    }

    public void setElderChild(String elderChild) {
        this.elderChild = elderChild;
    }

    public String getElderChildNumber() {
        return elderChildNumber;
    }

    public void setElderChildNumber(String elderChildNumber) {
        this.elderChildNumber = elderChildNumber;
    }

    public String getElderArea() {
        return elderArea;
    }

    public void setElderArea(String elderArea) {
        this.elderArea = elderArea;
    }
}
