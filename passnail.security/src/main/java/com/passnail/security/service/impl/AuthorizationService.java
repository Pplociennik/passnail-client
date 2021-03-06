package com.passnail.security.service.impl;

import com.passnail.common.throwable.security.AuthorizationException;
import com.passnail.data.service.CredentialsServiceIf;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import com.passnail.security.service.AuthorizationServiceIf;
import com.passnail.security.service.JWTServiceIf;
import com.passnail.security.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.passnail.security.SecurityConstants.UNAUTHORIZED_PASSWORD_SESSION_DATA;
import static com.passnail.security.SecurityConstants.UNAUTHORIZED_TOKEN_SESSION_DATA;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at czwartek, 25.02.2021 01:26
 * Project: passnail-client
 */
@Service
public class AuthorizationService implements AuthorizationServiceIf {

    @Autowired
    private JWTServiceIf jwtService;
    
    @Autowired
    private UserServiceIf userService;
    
    @Autowired
    private CredentialsServiceIf credentialsService;


    @Override
    public Boolean isUserAuthorized() {
        SessionData sessionData = SessionData.INSTANCE;

        validateSessionData(sessionData);
        jwtService.validateToken(sessionData.getToken(), sessionData.getPassword());
        return true;
    }

    @Override
    public void getAuthorizedUserCredentials() {
        SessionData sessionData = SessionData.INSTANCE;
        
        jwtService.validateToken(sessionData.getToken(), sessionData.getPassword());
        
        var authorizedUser = userService.findByLogin(sessionData.getAuthorizedUsername());
        
        sessionData.setAuthorizedUserSavedCredentials(credentialsService.decryptEntities(authorizedUser.getSavedCredentials(), sessionData.getPassword()));
        
        switchToLibraryScene();
    }

    private void switchToLibraryScene() {
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
