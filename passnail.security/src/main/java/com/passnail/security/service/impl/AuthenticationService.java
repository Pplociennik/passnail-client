package com.passnail.security.service.impl;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.*;
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
    private RegistrationValidationServiceIf validationService;

    @Autowired
    private LoginValidationServiceIf loginValidationService;


    @Override
    public void registerNewUserProfile(RegistrationDto aDto) {
        validationService.validateRegistrationData(aDto);
        registrationService.registerNewOfflineUserProfile(aDto);
    }

    @Override
    public void authenticateUser(LoginDto aDto) {
        loginValidationService.validateLoginData(aDto);
        loginService.authenticateAndLoginUser(aDto);
    }
}
