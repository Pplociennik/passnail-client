package com.passnail.connect.service;

/**
 * A service for synchronizing data with the online server.
 * <p>
 * Created by: Pszemko at wtorek, 23.02.2021 00:23
 * Project: passnail-client
 */
public interface SynchronizationServiceIf {

    void synchronize(String aUserName);
}
