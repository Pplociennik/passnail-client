package com.passnail.connect.service;

import com.passnail.data.model.entity.LocalUserEntity;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 01:09
 * Project: passnail-client
 */
public interface LocalUserServiceIf {

    Boolean localLoginExists(String aLogin);

    void registerNewLocalUserName(LocalUserEntity aLocalEntity);
}
