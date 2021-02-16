package com.passnail.connect.service;

import com.passnail.data.model.entity.UserEntity;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 21:50
 * Project: passnail-client
 */
public interface UserServiceIf {

    UserEntity findByLogin(String aLogin);

    UserEntity findByEmail(String aEmail);

    void registerNewLocalUser(UserEntity aEntity);
}
