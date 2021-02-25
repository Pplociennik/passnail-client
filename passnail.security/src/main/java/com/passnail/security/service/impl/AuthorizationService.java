package com.passnail.security.service.impl;

import com.passnail.security.service.AuthorizationServiceIf;
import com.passnail.security.service.JWTServiceIf;
import com.passnail.security.session.SessionData;
import com.passnail.security.throwable.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.passnail.security.SecurityConstants.UNAUTHORIZED_PASSWORD_SESSION_DATA;
import static com.passnail.security.SecurityConstants.UNAUTHORIZED_TOKEN_SESSION_DATA;

/**
 * Created by: Pszemko at czwartek, 25.02.2021 01:26
 * Project: passnail-client
 */
@Service
public class AuthorizationService implements AuthorizationServiceIf {

    @Autowired
    private JWTServiceIf jwtService;


    @Override
    public Boolean isUserAuthorized() {
        SessionData sessionData = SessionData.INSTANCE;

        validateSessionData(sessionData);
        jwtService.isValid(sessionData.getToken(), sessionData.getPassword());
        return true;
    }

    private void validateSessionData(SessionData sessionData) {
        if (sessionData.getToken().equals(UNAUTHORIZED_TOKEN_SESSION_DATA)) {
            throw new AuthorizationException("You are not allowed!");
        }
        if (sessionData.getPassword().equals(UNAUTHORIZED_PASSWORD_SESSION_DATA)) {
            throw new AuthorizationException("You are not allowed!");
        }
    }
}
