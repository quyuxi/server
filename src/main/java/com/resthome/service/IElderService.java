package com.resthome.service;

import com.resthome.model.ElderInfor;
import com.resthome.model.RingData;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-06-20.
 */
public interface IElderService
{
    //add
    String addElderMan(ElderInfor elderMan);
    //update
    String  updateElderMan(ElderInfor elderMan);

    String  delElderMan(ElderInfor eldman);

    String selectLastId();

    //查询老人信息byid
    ElderInfor selectByid(String id);

    //查询所有老人
    List<ElderInfor> selectAll();

    //查询老人最新的位置信息
    //查询最新的手环信息
    RingData selectLastInfo(String tableName);

    //查询指定时间段的手环信息
    List<RingData> selectSpecifyDateInfo(String tableName, String start, String end);
    //查询所有手环信息
    List<RingData> selectAllRingData(String tableName);
}
