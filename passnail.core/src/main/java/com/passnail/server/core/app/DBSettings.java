package com.passnail.server.core.app;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:23
 * Project: passnail-client
 */
public interface DBSettings {

    int getPort();

    String getServer();

    String getSelectedDataBaseName();

    String getPassword();

    String getUserName();

    String dbmsType();

    String JDBConnectionURL();

    String driverClassName();

    String dialect();
}
