package com.passnail.security.service.impl;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.*;
import com.passnail.security.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.passnail.security.SecurityConstants.*;

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

    @Autowired
    private DataSourceSettingsSwitcher switcher;


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

    @Override
    public void logout() {
        SessionData sessionData = SessionData.INSTANCE;
        sessionData.setToken(UNAUTHORIZED_TOKEN_SESSION_DATA);
        sessionData.setOnlineToken(UNAUTHORIZED_ONLINE_TOKEN_SESSION_DATA);
        sessionData.setPassword(UNAUTHORIZED_PASSWORD_SESSION_DATA);

        switcher.switchToDefaultAuthDatabase();
    }
}
