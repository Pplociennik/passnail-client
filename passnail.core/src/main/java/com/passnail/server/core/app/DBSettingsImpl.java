package com.passnail.server.core.app;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:28
 * Project: passnail-client
 */
public class DBSettingsImpl implements DBSettings {


    private String JDBConnectionUrl = "jdbc:h2:file:D:\\DEV_PROJECTS\\passnail-client\\passnail-client\\pszemek_bazaDanych";
    private String userName = "sa";
    private String password = "sa";
    private String driverClassName = "org.h2.Driver";
    private String dialect = "org.hibernate.dialect.H2Dialect";
//        <value.spring.jpa.hibernate.ddl-auto>create</value.spring.jpa.hibernate.ddl-auto>


    @Override
    public int getPort() {
        return 0;
    }

    @Override
    public String getServer() {
        return null;
    }

    @Override
    public String getSelectedDataBaseName() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String dbmsType() {
        return null;
    }

    @Override
    public String JDBConnectionURL() {
        return JDBConnectionUrl;
    }

    @Override
    public String driverClassName() {
        return driverClassName;
    }

    @Override
    public String dialect() {
        return dialect;
    }
}
