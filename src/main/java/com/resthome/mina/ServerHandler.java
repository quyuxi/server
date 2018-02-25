package com.resthome.mina;


import com.resthome.dao.ReceiveDataDao;
import com.resthome.model.ReceiveData;
import com.resthome.service.IReceiveServiceImpl;
import com.resthome.service.IRingService;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 一缕仙缘 on 2017-07-03.
 */
@Component
public class  ServerHandler implements IoHandler
{
    @Autowired
    IReceiveServiceImpl receiveService;

    private static final String NORMAL="01";
    private static final String DANGER="02";
    private static final String HELP="03";
    private static final String Delimiter=";";
    private static final String RECEIVED="1";

    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception
    {
        logger.error("exception"+throwable.getMessage(),throwable);

    }

    @Override
    public void messageReceived(IoSession ioSession, Object o) throws Exception {
      try
      {
          logger.info("the received msg: "+o.toString());

          String msg=o.toString();
          //01;123456789012345;2017-08-27 00:00:00;108.954858;34.266493;88;01;01;100;1#
          //02;123456789012345;2017-08-27 00:00:00;108.954858;34.266493;100;01;01;100;1#
          //03;123456789012345;2017-08-27 00:00:00;108.954858;34.266493;88;01;01;90;1#
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          if (msg!=null)
          {
              //1。将得到的字符串安装；分割
              String[] data = msg.trim().split(";");
              if (data.length!=10)
              {
                  logger.error("data form is error");
                  return;
              }
              //2.整理数据
              ReceiveData receiveData=null;
              //2.1正常
              if (NORMAL.equals(data[0].trim()))
              {
                  logger.info("Receive functionNum is "+data[0]);
                  //存储数据 不需要响应
                  receiveService.insertData(data);

              }
              //2.2危险
              else if (DANGER.equals(data[0].trim()))
              {
                  logger.info("Receive functionNum is "+data[0]);
                  receiveService.insertData(data);
                  //响应

                  //            功能号  身份标识    时间    已查收
                  String respose=DANGER+Delimiter+data[1]+Delimiter+dateFormat.format(new Date())+Delimiter+RECEIVED+"#";
                  ioSession.write(respose);

              }
              //2.3求救
              else if (HELP.equals(data[0].trim()))
              {
                  logger.info("Receive functionNum is "+data[0]);
                  receiveService.insertData(data);
                  String respose=HELP+Delimiter+data[1]+Delimiter+dateFormat.format(new Date())+Delimiter+RECEIVED+"#";
                  ioSession.write(respose.getBytes());
              }
              else
                  {
                      logger.error("Error receive data funtionNum is "+data[0]);
                      return;
                  }

          }

      }catch (Exception e)
      {
          logger.error("fail to do mina service,the error msg: "+e.getMessage(),e);

      }
       ioSession.close(false);
    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {

    }


}
