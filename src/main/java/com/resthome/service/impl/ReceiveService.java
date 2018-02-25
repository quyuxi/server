package com.resthome.service.impl;

import com.resthome.dao.ReceiveDataDao;
import com.resthome.model.ReceiveData;
import com.resthome.service.IReceiveServiceImpl;
import com.resthome.util.BaiduApiUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;


/**
 * Created by 一缕仙缘 on 2017-08-27.
 */
@Service
public class ReceiveService implements IReceiveServiceImpl {

    @Autowired
    ReceiveDataDao dataDao;


    private static String AK="GIkM6XoypdBs4FPM1a2TyhlyuZk8OaAN";


    private static Logger logger = LoggerFactory.getLogger(ReceiveService.class);


    private static final  String  PREFIX_URL="http://api.map.baidu.com/geoconv/v1/?";


    @Async
    @Override
    public void insertData(String[] msg)
    {
        if (msg==null){return ;}
        //01;123456789012345;2017-08-27 00:00:00;108.954858;34.266493;88;01;01;100;1#
        ReceiveData data =new ReceiveData();
        try
        {
            data.setFunctionNum(msg[0].trim());
            data.setIMSI(msg[1].trim());

            //date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            data.setDate(dateFormat.parse(msg[2].trim()));

            data.setHeartRate(msg[5].trim());
            data.setKinestate(msg[6].trim());
            data.setSleepStatus(msg[7].trim());
            data.setElectricity(msg[8].trim());
            data.setStatus(msg[9].trim());

            //设置经纬度
            String  lng=msg[3].trim();
            String  lat=msg[4].trim();

            //调用百度api
            transform(lng,lat);

            data.setLng(lng);
            data.setLat(lat);
            //insert
            if (! dataDao.insertData(data))
            {
                logger.error("insert data error");
            }

        }catch (Exception e)
        {
            logger.error("insert ringdata error",e);
        }
    }

    private  static  void transform(String gps_lng, String gps_lat)
    {
        String coords=gps_lng+","+gps_lat;
        String result = BaiduApiUtil.connectURL(PREFIX_URL + "coords=" + coords + "&from=1&to=5&output=json&ak=" + AK);
        try
        {
            JSONObject trasformat_result = JSONObject.fromObject(result);
            if (!"0".equals(trasformat_result.getString("status")))
            {
                logger.error("transform baidu coordinate error, message="+result);
                return;
            }
            JSONArray jsonArray = trasformat_result.getJSONArray("result");
            if (jsonArray.size()!=1){return;}
            gps_lng=jsonArray.getJSONObject(0).getString("x");
            gps_lat=jsonArray.getJSONObject(0).getString("y");

        }catch (Exception e)
        {
            logger.error("transform baidu coordinate error",e);
        }
    }
/*
    public static void main(String[] args)
    {
        String lng="106.6519570767";
        String lat="26.6245856997";
        transform(lng,lat);
        System.out.println(lng);
        System.out.println(lat);
    }*/
}
