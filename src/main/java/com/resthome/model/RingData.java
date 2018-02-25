package com.resthome.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 一缕仙缘 on 2017-06-19.
 */
public class RingData
{
    /**
     * 手环信息
     */
    private  String curID;//手环id  =表名
    private   String  datetime;//日期  主键
    private  String lng;//经度
    private String lat;//维度
    private String heartRate;//心率

    public RingData() {
    }

    public RingData(String curID, String datetime, String lng, String lat, String heartRate) {
        this.curID = curID;
        this.datetime = datetime;
        this.lng = lng;
        this.lat = lat;
        this.heartRate = heartRate;
    }

    @Override
    public String toString() {
        return "RingData{" +
                "curID=" + curID +
                ", datetime=" + datetime +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", heartRate='" + heartRate + '\'' +
                '}';
    }

    public String getCurID() {
        return curID;
    }

    public void setCurID(String curID) {
        this.curID = curID;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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


}
