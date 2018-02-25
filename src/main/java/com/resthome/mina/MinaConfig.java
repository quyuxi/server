package com.resthome.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;


import org.apache.mina.filter.logging.LoggingFilter;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 一缕仙缘 on 2017-07-03.
 */
@Configuration
public class MinaConfig {

    @Value("${mina.port}")
    private  int POTR;

    @Value("${mina.Delimiter}")
    private  String  Delimiter;

    @Autowired
    ServerHandler serverHandler;

    private static Logger logger = LoggerFactory.getLogger(MinaConfig.class);

    @Bean
    public LoggingFilter loggingFilter()
    {
        return new LoggingFilter();
    }

    @Bean
    public InetSocketAddress inetSocketAddress()
    {
        return new InetSocketAddress("localhost",POTR);
    }



    @Bean
    public IoAcceptor ioAcceptor() throws Exception
    {

        IoAcceptor acceptor=new NioSocketAcceptor();
        acceptor.getFilterChain().addLast( "logger", loggingFilter() );
        acceptor.getFilterChain().addLast( "codec",
                new ProtocolCodecFilter(
                        new TextLineCodecFactory
                                (Charset.forName("UTF-8"),Delimiter,Delimiter)));
        acceptor.setHandler(serverHandler);


        acceptor.getSessionConfig().setReadBufferSize( 100 );
        acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );

        logger.info("SocketServer bind inetSocketAddress :"+inetSocketAddress().getHostName()+inetSocketAddress().getPort());
        acceptor.bind(inetSocketAddress());
        System.out.println("服务器在端口："+"已经启动");

        logger.info("SocketServer running.....");
        return acceptor;
    }

}