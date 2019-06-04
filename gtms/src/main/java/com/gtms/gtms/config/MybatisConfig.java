package com.gtms.gtms.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author: 84644
 * @Date: 2019/4/2 20:12
 * @Description:
 **/
@Configuration
public class MybatisConfig {
    @Bean
    @PostConstruct
    public PaginationInterceptor pageinationInterceptor() {
        PaginationInterceptor pageinationInterceptor = new PaginationInterceptor();
        pageinationInterceptor.setDialectType("mysql");
        return pageinationInterceptor;
    }

    @Bean
    @PostConstruct
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setIdType(IdType.AUTO.getKey());
        globalConfiguration.setDbType("mysql");
        globalConfiguration.setDbColumnUnderline(true);
        return globalConfiguration;
    }
}
