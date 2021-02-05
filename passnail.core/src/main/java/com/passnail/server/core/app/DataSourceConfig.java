package com.passnail.server.core.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:20
 * Project: passnail-client
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Qualifier("default")
    @ConfigurationProperties(prefix = "spring.datasource")
    protected DataSource defaultDataSource(){
        return DataSourceBuilder
                .create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:file:./bazaDanych")
                .username("sa")
                .password("sa")
                .build();
    }

    @Bean
    @Primary
    @Scope("singleton")
    public AbstractRoutingDataSource routingDataSource(@Lazy @Autowired @Qualifier("default") DataSource defaultDataSource){
        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.addDataSource(RoutingDataSource.DEFAULT,defaultDataSource);
        routingDataSource.setDefaultTargetDataSource(defaultDataSource);
        return routingDataSource;
    }
}
