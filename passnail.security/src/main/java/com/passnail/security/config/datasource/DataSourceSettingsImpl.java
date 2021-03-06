package com.passnail.security.config.datasource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static com.passnail.security.SecurityConstants.*;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:28
 * Project: passnail-client
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DataSourceSettingsImpl implements DataSourceSettings {


    private String JDBConnectionUrl;
    private String userName;
    private String password;
    private String driverClassName = "org.h2.Driver";
    private String dialect = "org.hibernate.dialect.H2Dialect";
    private String ddlAuto = "create";


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

    @Override
    public Object getDdlAuto() {
        return ddlAuto;
    }

    public void setJDBConnectionUrlForUsername(String username, String baseDir) {
        StringBuilder url = new StringBuilder();
        url.append("jdbc:h2:file:").append(baseDir == null ? "./data/" : baseDir).append(username).append("_CREDDB");
        this.JDBConnectionUrl = url.toString();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDdlAuto(String ddlAuto) {
        this.ddlAuto = ddlAuto;
    }

    @Override
    public void setTestProperties() {
        this.JDBConnectionUrl = DB_TEST_URL;
        this.userName = DB_TEST_USERNAME;
        this.password = DB_TEST_PASSWORD;
        this.ddlAuto = DB_TEST_DDL;
    }

    @Override
    public void setDefaultAuthDbProperties() {
        this.JDBConnectionUrl = DB_DEFAULT_AUTH_URL;
        this.userName = DB_DEFAULT_AUTH_USERNAME;
        this.password = DB_DEFAULT_AUTH_PASSWORD;
        this.ddlAuto = DB_DEFAULT_AUTH_DDL;
    }
}
