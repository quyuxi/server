package com.resthome.dao;

import com.resthome.model.ReceiveData;
import org.springframework.stereotype.Repository;

/**
 * Created by 一缕仙缘 on 2017-08-27.
 */
@Repository
public interface ReceiveDataDao
{
    /*增加*/
   boolean insertData(ReceiveData data);
}
