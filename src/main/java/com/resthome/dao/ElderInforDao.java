package com.resthome.dao;

import com.resthome.model.ElderInfor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-06-19.
 */
@Repository
public interface ElderInforDao
{
    //查询最新的id值  返回id+1给客户端
    String selectLastId();

    //add
    boolean addElder(ElderInfor elder);

    //update
    boolean updateElder(ElderInfor elder);

    //del by id
    boolean delElder(String elderid);

    //select by id
    ElderInfor selectById(String elderId);

    //分页查询
   // List<ElderInfor> selectList(@Param("index") Integer index, @Param("pageCount") Integer pageCount);

    //查询所有人
    List<ElderInfor> selectAll();


}
