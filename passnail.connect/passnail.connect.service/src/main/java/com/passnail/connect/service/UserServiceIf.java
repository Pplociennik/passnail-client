package com.passnail.connect.service;

import com.passnail.data.transfer.model.dto.LocalUserDto;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 21:50
 * Project: passnail-client
 */
public interface UserServiceIf {

    void createNewLocalUser(LocalUserDto aUserDto);
}
