package com.passnail.server.core.app;

import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.UserEntity;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.hibernate.tool.schema.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.EnumSet;

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


        MetadataSources metadataSources = new MetadataSources(registry);
        metadataSources.addAnnotatedClass(UserEntity.class);
        metadataSources.addAnnotatedClass(CredentialsEntity.class);
        Metadata metadata = metadataSources.buildMetadata();

        SchemaUpdate schemaUpdate = new SchemaUpdate();
        schemaUpdate.setFormat(true);
        schemaUpdate.execute(EnumSet.of(TargetType.DATABASE), metadata);
    }
}
