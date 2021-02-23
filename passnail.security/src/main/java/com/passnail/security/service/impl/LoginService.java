package com.passnail.security.service.impl;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.security.service.JWTServiceIf;
import com.passnail.security.service.LoginServiceIf;
import com.passnail.security.service.LoginValidationServiceIf;
import com.passnail.security.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:29
 * Project: passnail-client
 */
@Service
public class LoginService implements LoginServiceIf {


    @Autowired
    private LoginValidationServiceIf loginValidationService;

    @Autowired
    private JWTServiceIf jwtService;


    @Override
    public void authenticateAndLoginUser(LoginDto aDto) {
        validateLoginData(aDto);
    }

    private void validateLoginData(LoginDto aDto) {
        if (validateOnlineId(aDto.getOnlineID())) {
            authorizeOnlineWithSynchronization(aDto);
        } else {
            authorizeOffline(aDto);
        }
    }

    private void authorizeOffline(LoginDto aDto) {
        Objects.requireNonNull(aDto);
        loginValidationService.validateLocalLoginOrEmail(aDto.getLoginOrEmail());
        loginValidationService.validatePasswordInUserDb(aDto);

        createSession(aDto);
    }

    private void createSession(LoginDto aDto) {
        SessionData sessionData = SessionData.INSTANCE;
        sessionData.setPassword(aDto.getPassword());
        sessionData.setToken(jwtService.createToken(aDto));
    }

    private void authorizeOnlineWithSynchronization(LoginDto aDto) {

    }

    private Boolean validateOnlineId(String onlineID) {
        return onlineID != null;
    }
}
