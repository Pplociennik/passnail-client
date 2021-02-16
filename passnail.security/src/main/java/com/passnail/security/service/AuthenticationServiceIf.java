package com.passnail.security.service;

import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:27
 * Project: passnail-client
 */
public interface AuthenticationServiceIf {

    UserEntity registerNewUserProfile(RegistrationDto aDto);

    UserEntity authenticateUser(LoginDto aDto);
}
