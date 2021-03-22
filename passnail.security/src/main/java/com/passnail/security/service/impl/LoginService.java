package com.passnail.security.service.impl;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.connect.service.RequestSenderServiceIf;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.data.transfer.model.dto.UserDto;
import com.passnail.security.SecurityConstants;
import com.passnail.security.service.JWTServiceIf;
import com.passnail.security.service.LoginServiceIf;
import com.passnail.security.service.LoginValidationServiceIf;
import com.passnail.security.service.RegistrationServiceIf;
import com.passnail.security.session.SavedCredentialsSessionDataService;
import com.passnail.security.session.SessionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Matcher;

import static com.passnail.connect.util.ConnectionConstants.AUTHORIZE_WITH_ONLINE_ID;
import static com.passnail.connect.util.ConnectionConstants.SERVER_RPI_HOST;
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

    @Autowired
    private RequestSenderServiceIf requestSenderService;

    @Autowired
    private RegistrationServiceIf registrationService;


    @Override
    public void authenticateAndLoginUser(LoginDto aDto) {
        validateLoginData(aDto);
    }

    private void validateLoginData(LoginDto aDto) {
        if (aDto.getLoginOrEmail() == null || aDto.getLoginOrEmail().isEmpty()) {
            throw new AuthenticationException("Login or email not specified!");
        }

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
        validateEmailGiven(aDto);
        UserEntity user = userService.findByEmail(aDto.getLoginOrEmail());

        if (user != null) {
            authorizeOfflineByPassword(aDto);
        } else {
            processOnlineAuthorization(aDto);
        }


    }

    private void validateEmailGiven(LoginDto aDto) {
        Matcher matcher = SecurityConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(aDto.getLoginOrEmail());
        if (!matcher.find()) {
            throw new AuthenticationException("Please, use email address for online login.");
        }
    }

    private void processOnlineAuthorization(LoginDto aDto) {
        UserDto receivedUser = requestSenderService.sendOnlineAuthorizationRequest(getUrlForRpi(AUTHORIZE_WITH_ONLINE_ID), aDto)
                .block();

        registerUser(receivedUser, aDto);
    }

    private String getUrlForRpi(String aUri) {
        StringBuilder builder = new StringBuilder(SERVER_RPI_HOST);
        return builder.append(aUri).toString();
    }

    private void registerUser(UserDto aUserDto, LoginDto aLoginDto) {
        String login = createLoginForUser(aUserDto);

        RegistrationDto regDto = RegistrationDto.builder()
                .login(login)
                .email(aUserDto.getEmailAddress())
                .password(aLoginDto.getPassword())
                .passwordRepeat(aLoginDto.getPassword())
                .onlineId(aUserDto.getOnlineId())
                .build();

        registrationService.registerNewOfflineUserProfile(regDto);
    }

    private String createLoginForUser(UserDto aDto) {
        UserEntity user = userService.findByLogin(aDto.getLogin());

        if (user == null) {
            return aDto.getLogin();
        }
        int nr = 0;
        String newLogin;

        do {
            nr++;
            newLogin = aDto.getLogin() + nr;
            user = userService.findByLogin(newLogin);
        } while (user != null);

        return newLogin;
    }

    private Boolean validateOnlineId(String onlineID) {
        return (onlineID != null && !onlineID.isEmpty());
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
