package com.resthome.util;

import com.resthome.model.ElderInfor;
import org.springframework.util.StringUtils;

/**
 * Created by 一缕仙缘 on 2017-07-18.
 */
public class ElderInfoCheck
{
    //检查是否每个属性都有值
    /*
    private  String elderID;
    private  String  elderName;
    private  String elderBirthday;
    private  String  elderSex;
    private  String  elderChild;
    private  String  elderChildNumber;
    private  String  elderArea;
     */
    public static boolean checkElderHasEmptyAttribute(ElderInfor elderInfor)
    {
        if (StringUtils.isEmpty(elderInfor.getElderID())||StringUtils.isEmpty(elderInfor.getElderName())
                ||StringUtils.isEmpty(elderInfor.getElderBirthday())||StringUtils.isEmpty(elderInfor.getElderSex())
                ||StringUtils.isEmpty(elderInfor.getElderChild())||StringUtils.isEmpty(elderInfor.getElderChildNumber())
                ||StringUtils.isEmpty(elderInfor.getElderArea()))
        {return true;}
        return false;
    }
}
