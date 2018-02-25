package com.resthome.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


/**
 * Created by 一缕仙缘 on 2017-06-13.
 * @Description : mybatis配置
 */
@Configuration
@MapperScan("com.resthome.dao")
public class DataBaseConfiguration
{
    @Value("${spring.datasource.dbcp2.driver-class-name}")
    private   String DRIVER;
    @Value("${spring.datasource.dbcp2.url}")
    private   String URL;
    @Value("${spring.datasource.dbcp2.username}")
    private   String USER_NAME;
    @Value("${spring.datasource.dbcp2.password}")
    private   String PASSWORD;
    @Value("${spring.datasource.dbcp2.initial-size}")
    private   int INITIAL_SIZE;
    @Value("${spring.datasource.dbcp2.max-total}")
    private   int MAX_SIZE;
    @Value("${spring.datasource.dbcp2.max-idle}")
    private   int MAX_IDLE;
    @Value("${spring.datasource.dbcp2.max-total}")
    private   int MIN_IDLE;
    @Value("${spring.datasource.dbcp2.max-wait-millis}")
    private   long MAX_WAIT;


    /**
     * @Description 配置数据源
     */
    @Bean
    public BasicDataSource getDataSource()
    {
        BasicDataSource dataSource =  new BasicDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setInitialSize(INITIAL_SIZE);
        dataSource.setMaxActive(MAX_SIZE);
        dataSource.setMaxIdle(MAX_IDLE);
        dataSource.setMinIdle(MIN_IDLE);
        dataSource.setMaxWait(MAX_WAIT);

        return dataSource;
    }

    /**
     * @Description 配置sqlsessionFactory
     * @param dataSource
     * @return
     */
    @Bean
    public SqlSessionFactoryBean getSqlSession(BasicDataSource dataSource) throws Exception
    {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        sqlSessionFactoryBean.setDataSource(dataSource);
        return  sqlSessionFactoryBean;
    }

    /**
     * @Description 事务管理
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(BasicDataSource dataSource)
    {
        return  new DataSourceTransactionManager(dataSource);
    }
}
