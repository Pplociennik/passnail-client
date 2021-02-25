package com.passnail.security.config.datasource;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:23
 * Project: passnail-client
 */
public interface DataSourceSettings {

    int getPort();

    String getServer();

    String getSelectedDataBaseName();

    String getPassword();

    String getUserName();

    String dbmsType();

    String JDBConnectionURL();

    String driverClassName();

    String dialect();

    Object getDdlAuto();

    void setJDBConnectionUrlForUsername(String username, String baseDir);

    void setUserName(String userName);

    void setPassword(String password);

    void setDdlAuto(String ddlAuto);

    void setTestProperties();

    void setDefaultAuthDbProperties();
}
