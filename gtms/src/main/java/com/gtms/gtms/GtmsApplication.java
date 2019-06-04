package com.gtms.gtms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@ImportResource(locations = {"classpath:spring-application.xml"})
public class GtmsApplication {
    private static final Logger LOG = LoggerFactory.getLogger(GtmsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GtmsApplication.class, args);
        LOG.info("==================启动成功==================");
    }

}
