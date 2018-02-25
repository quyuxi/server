package com.resthome.dao;



import com.resthome.model.RingData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-06-19.
 */
@Repository
public interface RingDao
{
  //add
    boolean addRingData(RingData data);

    //查询最新的信息
    RingData selectLastInfo(@Param("tableName") String tableName);

    //创建表
  void  createTable(@Param("tableName") String tableName);

  //删除表
  void delRing(@Param("tableName") String tableName);

  /*查询指定时间段的手环信息*/
  List<RingData> selectSpecifyDateInfo(@Param("tableName") String tableName, @Param("start") String start, @Param("end") String end);

  //查询所有手环信息
  List<RingData> selectAllRingData(@Param("tableName") String tableName);


}
