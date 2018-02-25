package com.resthome.model;

import java.util.Date;

/**
 * Created by 一缕仙缘 on 2017-08-27.
 */
public class ReceiveData
{
    /**
     * 手环数据
     */

    /**
     * 功能号：01普通信息 02危险信号 03 主动求救
     * 响应：01不需要响应 02需要 03需要
     */

    private String  functionNum;
    private String  IMSI;//IMSI      15位唯一标示身份
    private Date date;//时间
    private String lng;//经度
    private String lat;//纬度
    private String heartRate;//心率
    private String  kinestate;//运动状态 01静态 02行走 03跌倒
    private String  sleepStatus;//睡眠状态 01浅 02中 03深
    private String  electricity;//电量  0-100
    private String  status;//手环状态  1 ok 0 error

    public String getFunctionNum() {
        return functionNum;
    }

    public void setFunctionNum(String functionNum) {
        this.functionNum = functionNum;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getKinestate() {
        return kinestate;
    }

    public void setKinestate(String kinestate) {
        this.kinestate = kinestate;
    }

    public String getSleepStatus() {
        return sleepStatus;
    }

    public void setSleepStatus(String sleepStatus) {
        this.sleepStatus = sleepStatus;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
