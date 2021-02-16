package com.passnail.security.service.impl;

import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.service.LoginServiceIf;
import com.passnail.security.service.RegistrationServiceIf;
import com.passnail.security.service.ValidationServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 19:20
 * Project: passnail-client
 */
@Service
public class AuthenticationService implements AuthenticationServiceIf {

    @Autowired
    private LoginServiceIf loginService;

    @Autowired
    private RegistrationServiceIf registrationService;

    @Autowired
    private ValidationServiceIf validationService;


    @Override
    public UserEntity registerNewUserProfile(RegistrationDto aDto) {
        validationService.validateRegistrationData(aDto);
        return registrationService.registerNewOfflineUserProfile(aDto);
    }

    @Override
    public UserEntity authenticateUser(LoginDto aDto) {
        return null;
    }
}
