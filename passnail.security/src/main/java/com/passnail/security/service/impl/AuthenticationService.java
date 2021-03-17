package com.passnail.security.service.impl;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.*;
import com.passnail.security.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.passnail.security.SecurityConstants.*;

/**
 * {@inheritDoc}
 * <p>
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
    private RegistrationValidationServiceIf registrationValidationService;

    @Autowired
    private LoginValidationServiceIf loginValidationService;

    @Autowired
    private DataSourceSettingsSwitcher switcher;


    @Override
    public void registerNewUserProfile(RegistrationDto aDto) {
        registrationValidationService.validateRegistrationData(aDto);
        registrationService.registerNewOfflineUserProfile(aDto);
    }

    @Override
    public void authenticateUser(LoginDto aDto) {
        if (aDto.getOnlineID() == null) {
            loginValidationService.validateLoginData(aDto);
        }
        loginService.authenticateAndLoginUser(aDto);
    }

    @Override
    public void logout(Boolean aTestDb) {
        SessionData sessionData = SessionData.INSTANCE;
        sessionData.setToken(UNAUTHORIZED_TOKEN_SESSION_DATA);
        sessionData.setOnlineToken(UNAUTHORIZED_ONLINE_TOKEN_SESSION_DATA);
        sessionData.setPassword(UNAUTHORIZED_PASSWORD_SESSION_DATA);
        sessionData.setAuthorizedOnlineId(UNAUTHORIZED_ONLINE_TOKEN_SESSION_DATA);
        sessionData.setAuthorizedUsername(UNAUTHORIZED_USERNAME_SESSION_DATA);
        sessionData.setAuthorizedPassNumber(String.valueOf(0));
        sessionData.setAuthorizedUserSavedCredentials(Collections.emptyList());

        if (aTestDb) {
            switcher.switchToTestDatabase();
        } else {
            switcher.switchToDefaultAuthDatabase();
        }
    }
}
