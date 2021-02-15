package com.passnail.connect.service;

import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.UserDto;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 21:50
 * Project: passnail-client
 */
public interface UserServiceIf {

    void createNewUser(UserDto aUserDto);

    UserEntity findByLogin(final String login);
}
