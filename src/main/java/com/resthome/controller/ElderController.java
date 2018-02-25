package com.resthome.controller;

import com.resthome.annotation.SerializedField;
import com.resthome.dao.RingDao;
import com.resthome.model.ElderInfor;
import com.resthome.model.RingData;
import com.resthome.service.IElderService;
import com.resthome.util.DDLStatus;
import com.resthome.util.ElderInfoCheck;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 一缕仙缘 on 2017-07-18.
 * @Description : 老人相关接口
 */
@RestController
@RequestMapping("/resthome")
public class ElderController
{

    @Autowired
    IElderService elderService;

    @Autowired
    RingDao ringDao;

    private static Logger LOGGER = LoggerFactory.getLogger(ElderController.class);

    /**
     * @Description : 查询最新老人id
     * @return 最新的老人id
     */
    @SerializedField
    @RequestMapping(value = "/findLastId",method = RequestMethod.GET)
    public Object getLastId()
    {
        String id = elderService.selectLastId();
        if (StringUtils.isEmpty(id))
        {
            return "KW170001";//如果数据库无数据，返回KW170001
        }
        int i=0;
        try
        {
            String newid = id.replaceAll("^[a-zA-Z]+", "");
             i = Integer.valueOf(newid) + 1;

        }catch (Exception e)
        {
            LOGGER.error("find last id  failed,the message:"+e.getMessage());
        }
        return "KW"+i;
    }


    /**
     * @Description 增加老人
     * @param elderMan
     * @return
     */
    @SerializedField
    @RequestMapping(value = "/addElder",method = RequestMethod.POST,consumes = "application/json")
    public String addMan(@RequestBody ElderInfor elderMan)
    {
        //检查老人信息是否有空值
        if (ElderInfoCheck.checkElderHasEmptyAttribute(elderMan))
        {
            LOGGER.error("elderinfo has empty attribute ,the elderinfo is  :"+elderMan);
            return null;
        }
        if (elderMan.getElderBirthday().contains("/"))
        {
            String s = elderMan.getElderBirthday().replaceAll("/", "-");
            elderMan.setElderBirthday(s);
        }
        return elderService.addElderMan(elderMan);
    }



    /**
     * @Description 删除老人
     * @param elder
     * @return
     */
    @SerializedField
    @RequestMapping(value = "/delElder",method =RequestMethod.POST,consumes = "application/json")
    public String delMan(@RequestBody ElderInfor elder)
    {
        if (StringUtils.isEmpty(elder.getElderID()))
        {
            LOGGER.error("the elder="+elder+" id is null");
            return DDLStatus.DEL_FAILD;
        }
        return elderService.delElderMan(elder);
    }


    /**
     * @Description 更新老人信息
     * @param elder
     * @return
     */
    @SerializedField
    @RequestMapping(value = "/updateElder",method = RequestMethod.POST,consumes = "application/json")
    public String updateMan(@RequestBody ElderInfor elder)
    {
        if (ElderInfoCheck.checkElderHasEmptyAttribute(elder))
        {
            LOGGER.error("the elder :"+elder+"is error");
            return null;
        }
        if (elder.getElderBirthday().contains("/"))
        {
            String s = elder.getElderBirthday().replaceAll("/", "-");
            elder.setElderBirthday(s);
        }
        return elderService.updateElderMan(elder);
    }

    //4.根据id查询老人信息
    @SerializedField
    @RequestMapping(value = "/queryElder/{elderID}",method = RequestMethod.GET)
    public Object selectByID (@PathVariable("elderID") String elderID)
    {
        if (StringUtils.isEmpty(elderID))
        {
            LOGGER.error("the deleted elderinfo id is null");
            return null;
        }
        ElderInfor elderInfor = elderService.selectByid(elderID);
        if (elderInfor==null)
        {
            return null;
        }
        return JSONObject.fromObject(elderInfor);
    }

    //5.查询所有老人
    @SerializedField
    @RequestMapping(value = "/queryAllElders",method = RequestMethod.GET)
    public Object selectAll()
    {
        List<ElderInfor> list = elderService.selectAll();
        if (list==null)
        {
            return null;
        }
        return JSONArray.fromObject(list);
    }

    //6.查询老人最新的位置信息
    @SerializedField
    @RequestMapping(value = "/lastAddress/{elderID}",method = RequestMethod.GET)
    public Object findLastRingData(@PathVariable("elderID") String elderID)
    {
        if (StringUtils.isEmpty(elderID))
        {
            LOGGER.error("the id is null");
            return null;
        }
        RingData ringData = elderService.selectLastInfo(elderID);
        if (ringData==null)
        {
            return null;
        }
        return JSONObject.fromObject(ringData);
    }

    //7.查询指定时间段的手环信息
    @SerializedField
    @RequestMapping(value = "/selectSpecifyDateInfo/{tableName}/{start}/{end}",method = RequestMethod.GET)
    public Object selectSpecifyDateInfo(@PathVariable("tableName") String tableName,
                                                @PathVariable("start") String start,
                                                @PathVariable("end") String end)
    {
        if (StringUtils.isEmpty(tableName)|| StringUtils.isEmpty(start)|| StringUtils.isEmpty(end))
        {
            return null;
        }
        List<RingData> ringDataList = elderService.selectSpecifyDateInfo(tableName, start, end);
        if (ringDataList==null)
        {
            return null;
        }
        return JSONArray.fromObject(ringDataList);
    }


}
