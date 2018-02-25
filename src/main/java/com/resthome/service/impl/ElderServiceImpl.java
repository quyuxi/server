package com.resthome.service.impl;

import com.resthome.dao.ElderInforDao;
import com.resthome.dao.RingDao;
import com.resthome.model.ElderInfor;
import com.resthome.model.RingData;
import com.resthome.service.IElderService;
import com.resthome.util.DDLStatus;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-07-16.
 */
@Service
public class ElderServiceImpl implements IElderService
{
    @Autowired
    ElderInforDao edao;

    @Autowired
    RingDao ringDao;

    private static Logger LOGGER = LoggerFactory.getLogger(ElderServiceImpl.class);

    @Override
    public String addElderMan(ElderInfor elderMan)
    {
        LOGGER.info("-------@Begin add elder --------------");
        try
        {
            boolean b= edao.addElder(elderMan);
            if (b)
            {
                //还要创建对于的手环对应的手环表
                ringDao.createTable(elderMan.getElderID());
                LOGGER.info("--------@Add elderMan  and Ringdata  success");
                return DDLStatus.ADD_SUCCESS;
            }

        }catch (Throwable t)
        {
            LOGGER.error("---@Add elderMan  and ringdao table failed ,"+t.getMessage(),t);
        }

        return DDLStatus.ADD_FAILD;
    }

    @Override
    public String updateElderMan(ElderInfor elderMan)
    {
        LOGGER.info("------@Begin updateElderMan-----------");
        try
        {
            boolean b = edao.updateElder(elderMan);
            if (b)
            {
                LOGGER.info("---------@Update elder success-----");
                return DDLStatus.UPDATE_SUCCESS;
            }

        }catch (Exception e)
        {
            LOGGER.error("uodate elder failed "+e.getMessage());
        }

        return DDLStatus.UPDATE_FAILD;
    }

    @Override
    public String delElderMan(ElderInfor eldman)
    {
        LOGGER.info("--------@Begin delete eldinfo = "+eldman+"-------------");
        try
        {
            boolean b = edao.delElder(eldman.getElderID());
            if (b)
            {
                ringDao.delRing(eldman.getElderID());
                LOGGER.info("-----------@Delete elder  and Ringdao table success -------------");
                return DDLStatus.DEL_SUCCESS;
            }

        }catch (Exception e)
        {
            LOGGER.error("delete elder and  ring table failed ,and messgae="+e.getMessage());
        }
        return DDLStatus.DEL_FAILD;
    }

    @Transactional(readOnly = true)
    @Override
    public String selectLastId()
    {
        String id ="";
        try
        {
            id = edao.selectLastId();
            if (StringUtils.isEmpty(id))
            {
                LOGGER.info("select last elder id="+id);
                return null;
            }
        }catch (Exception e)
        {
            LOGGER.error("select last elder id failed ,and the message="+e.getMessage());
        }
        return id;
    }

    @Override
    public ElderInfor selectByid(String id)
    {
        ElderInfor elderInfor=null;
        LOGGER.info("-------@Begin select elder by id---------");
        LOGGER.info("ID="+id);
        try
        {
            elderInfor = edao.selectById(id);
            if (ObjectUtils.isEmpty(elderInfor))
            {
                LOGGER.error("the selected elder is null");
                return null;
            }return elderInfor;

        }catch (Exception e)
        {LOGGER.error("select elder failed and  message = "+e.getMessage());}
        return elderInfor;
    }

    @Override
    public List<ElderInfor> selectAll()
    {
        LOGGER.info("select  all elders");
        List<ElderInfor> list = null;
        try
        {
            list = edao.selectAll();
            if (list==null && list.size()==0)
            {
                LOGGER.error("select all elders  is null ");
                return null;
            }
        }catch (Exception e)
        {
            LOGGER.error("select all elders failed ,and message = "+e.getMessage());
        }
        return list;
    }

    @Override
    public RingData selectLastInfo(String tableName)
    {
        LOGGER.info("begin find last ring data ");
        RingData ringData=null;
        try
        {
            ringData = ringDao.selectLastInfo(tableName);
            if (ringData==null)
            {
                LOGGER.error("select last RingData null");
                return null;
            }
            LOGGER.info("the elder = "+tableName+" last ring data is :"+ringData.toString());

        }catch (Exception e)
        {
            LOGGER.error("find elder="+tableName+" last ring data failed,the message:"+e.getMessage());
        }

        return ringData;
    }

    @Override
    public List<RingData> selectSpecifyDateInfo(String tableName, String start, String end)
    {
        LOGGER.info("begin select specified time ring data ");
        List<RingData> list=null;
        try
        {
            if (StringUtils.isEmpty(tableName)|| StringUtils.isEmpty(start) || StringUtils.isEmpty(end))
            {
                return null;
            }
            list=ringDao.selectSpecifyDateInfo(tableName, start, end);
            if (list==null && list.size()==0)
            {
                return null;
            }
            LOGGER.info("select specified time ring data :"+list);

        }catch (Exception e)
        {
            LOGGER.error("select specified time ring data  failed, the message:"+e.getMessage());
        }
        return list;
    }

    @Override
    public List<RingData> selectAllRingData(String tableName)
    {
        return null;
    }
}
