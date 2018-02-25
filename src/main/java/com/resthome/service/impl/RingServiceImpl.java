package com.resthome.service.impl;

import com.resthome.dao.RingDao;
import com.resthome.model.RingData;
import com.resthome.service.IRingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 一缕仙缘 on 2017-08-13.
 */
@Service
public class RingServiceImpl implements IRingService
{
    private static Logger LOGGER = LoggerFactory.getLogger(RingServiceImpl.class);

    @Autowired
    RingDao ringDao;

    @Async
    @Override
    public void addRingData(RingData ringData)
    {
        LOGGER.info("----begin add ringdata--");
        try
        {
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
            LOGGER.info("当前时间"+sdf.format(new Date()));
            LOGGER.debug("the ringdata: "+ringData);
            ringDao.addRingData(ringData);
        }catch (Exception e)
        {
            LOGGER.error("fail to add ringdata ,the error msg:"+e.getMessage(),e);
        }
    }
}
