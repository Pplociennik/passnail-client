package com.passnail.security.service.impl;

import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.security.SecurityConstants;
import com.passnail.security.service.JWTServiceIf;
import com.passnail.security.service.LoginServiceIf;
import com.passnail.security.service.LoginValidationServiceIf;
import com.passnail.security.session.SavedCredentialsSessionDataService;
import com.passnail.security.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;

import static java.util.Objects.requireNonNull;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 00:29
 * Project: passnail-client
 */
@Service
public class LoginService implements LoginServiceIf {


    @Autowired
    private LoginValidationServiceIf loginValidationService;

    @Autowired
    private JWTServiceIf jwtService;

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private SavedCredentialsSessionDataService sessionDataService;


    @Override
    public void authenticateAndLoginUser(LoginDto aDto) {
        validateLoginData(aDto);
    }

    private void validateLoginData(LoginDto aDto) {
        if (validateOnlineId(aDto.getOnlineID())) {
            authorizeOnlineWithSynchronization(aDto);
        } else {
            authorizeOfflineByPassword(aDto);
        }
    }

    private void authorizeOfflineByPassword(LoginDto aDto) {
        requireNonNull(aDto);
        loginValidationService.validatePasswordInUserDb(aDto);

        createSession(aDto);
    }

    private void createSession(LoginDto aDto) {
        SessionData sessionData = SessionData.INSTANCE;
        sessionData.setPassword(aDto.getPassword());
        sessionData.setToken(jwtService.createToken(aDto));
        sessionData.setOnlineToken(getOnlineToken(aDto));
        sessionData.setAuthorizedUsername(getLogin(aDto));
        sessionData.setAuthorizedUserLastSynchDate(getAuthorizedUserLastSynchDate(aDto));

        sessionDataService.refreshAuthorizedUserSavedCredentialsData();
    }

    private String getOnlineToken(LoginDto aDto) {
        Matcher matcher = SecurityConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(aDto.getLoginOrEmail());
        UserEntity user = matcher.find() ?
                userService.findByEmail(aDto.getLoginOrEmail()) :
                userService.findByLogin(aDto.getLoginOrEmail());

        return validateOnlineId(user.getOnlineID()) ?
                user.getOnlineID() :
                null;
    }

    private void authorizeOnlineWithSynchronization(LoginDto aDto) {

    }

    private Boolean validateOnlineId(String onlineID) {
        return onlineID != null;
    }

    private String getLogin(LoginDto aDto) {
        return !aDto.getLoginOrEmail().contains("@") ?
                aDto.getLoginOrEmail() :
                userService.findByEmail(aDto.getLoginOrEmail()).getLogin();
    }

    private Date getAuthorizedUserLastSynchDate(LoginDto aDto) {
        return userService.findByLogin(getLogin(aDto)).getLastSynchronization();
    }
}
