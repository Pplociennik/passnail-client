package com.passnail.security.service.impl;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.service.LoginServiceIf;
import com.passnail.security.service.RegistrationServiceIf;
import com.passnail.security.service.RegistrationValidationServiceIf;
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


    @Override
    public void registerNewUserProfile(RegistrationDto aDto) {
        validationService.validateRegistrationData(aDto);
        registrationService.registerNewOfflineUserProfile(aDto);
    }

    @Override
    public void authenticateUser(LoginDto aDto) {
        loginService.authenticateAndLoginUser(aDto);
    }
}
