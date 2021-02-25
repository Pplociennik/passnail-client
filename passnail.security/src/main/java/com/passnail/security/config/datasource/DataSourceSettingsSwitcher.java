package com.passnail.security.config.datasource;

import com.passnail.common.config.RoutingDataSource;
import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.model.entity.LocalUserEntity;
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
public class DataSourceSettingsSwitcher {

    @Autowired
    private AbstractRoutingDataSource routingDataSource;

    public void applySettings(DataSourceSettings dataSourceSettings) {

        if (routingDataSource instanceof RoutingDataSource) {

            DataSource dataSource = DataSourceBuilder
                    .create()
                    .username(dataSourceSettings.getUserName())
                    .password(dataSourceSettings.getPassword())
                    .url(dataSourceSettings.JDBConnectionURL())
                    .driverClassName(dataSourceSettings.driverClassName())
                    .build();

            RoutingDataSource rds = (RoutingDataSource) routingDataSource;

            rds.addDataSource(RoutingDataSource.NEW, dataSource);
            rds.setKey(RoutingDataSource.NEW);

            updateDDL(dataSourceSettings);
        }
    }

    private void updateDDL(DataSourceSettings dataSourceSettings) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySetting("hibernate.connection.url", dataSourceSettings.JDBConnectionURL())
                .applySetting("hibernate.connection.username", dataSourceSettings.getUserName())
                .applySetting("hibernate.connection.password", dataSourceSettings.getPassword())
                .applySetting("hibernate.connection.driver_class", dataSourceSettings.driverClassName())
                .applySetting("hibernate.dialect", dataSourceSettings.dialect())
                .applySetting("show.sql", "false")
                .applySetting("hibernate.ddl-auto", dataSourceSettings.getDdlAuto())
                .build();


        MetadataSources metadataSources = new MetadataSources(registry);
        metadataSources.addAnnotatedClass(UserEntity.class);
        metadataSources.addAnnotatedClass(CredentialsEntity.class);
        metadataSources.addAnnotatedClass(LocalUserEntity.class);
        Metadata metadata = metadataSources.buildMetadata();

        SchemaUpdate schemaUpdate = new SchemaUpdate();
        schemaUpdate.setFormat(true);
        schemaUpdate.execute(EnumSet.of(TargetType.DATABASE), metadata);

    }

    public void switchToTestDatabase() {
        DataSourceSettings ds = new DataSourceSettingsImpl();
        ds.setTestProperties();
        applySettings(ds);
    }

    public void switchToDefaultAuthDatabase() {
        DataSourceSettings ds = new DataSourceSettingsImpl();
        ds.setDefaultAuthDbProperties();
        applySettings(ds);
    }
}
