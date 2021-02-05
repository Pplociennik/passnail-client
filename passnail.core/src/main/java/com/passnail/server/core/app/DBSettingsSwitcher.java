package com.passnail.server.core.app;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:22
 * Project: passnail-client
 */
@Component
public class DBSettingsSwitcher {

    @Autowired
    private AbstractRoutingDataSource routingDataSource;

    public void applySettings(DBSettings dbSettings) {

        if (routingDataSource instanceof RoutingDataSource) {
            // by default Spring uses DataSource from apache tomcat

            DataSource dataSource = DataSourceBuilder
                    .create()
                    .username(dbSettings.getUserName())
                    .password(dbSettings.getPassword())
                    .url(dbSettings.JDBConnectionURL())
                    .driverClassName(dbSettings.driverClassName())
                    .build();

            RoutingDataSource rds = (RoutingDataSource) routingDataSource;

            rds.addDataSource(RoutingDataSource.NEW, dataSource);
            rds.setKey(RoutingDataSource.NEW);

            updateDDL(dbSettings);
        }
    }

    private void updateDDL(DBSettings dbSettings) {

        /** worked on hibernate 5*/
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.url", dbSettings.JDBConnectionURL())
                .applySetting("hibernate.connection.username", dbSettings.getUserName())
                .applySetting("hibernate.connection.password", dbSettings.getPassword())
                .applySetting("hibernate.connection.driver_class", dbSettings.driverClassName())
                .applySetting("hibernate.dialect", dbSettings.dialect())
                .applySetting("show.sql", "false")
                .build();

//        Metadata metadata = new MetadataSources()
//                .addPackage("specify_here_your_package_with_entities")
//                .getMetadataBuilder(registry)
//                .build();
//
//        new SchemaUpdate().execute(false, true);
    }
}
