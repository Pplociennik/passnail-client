package com.passnail.security.service.impl;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.LocalUserServiceIf;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.security.SecurityConstants;
import com.passnail.security.config.datasource.DataSourceSettings;
import com.passnail.security.config.datasource.DataSourceSettingsImpl;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.LoginValidationServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

import static com.passnail.security.SecurityConstants.VALID_EMAIL_ADDRESS_REGEX;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at wtorek, 23.02.2021 00:38
 * Project: passnail-client
 */
@Service
public class LoginValidationService implements LoginValidationServiceIf {

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private LocalUserServiceIf localUserService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private DataSourceSettingsSwitcher switcher;


    private void validateLogin(String aLogin) {
        if (aLogin == null || aLogin.length() == 0) {
            throw new AuthenticationException("Login not specified!");
        }
        if (!localUserService.localLoginExists(aLogin)) {
            throw new AuthenticationException("User '" + aLogin + "' does not exist!");
        }
    }

    private void validateEmail(String aEmail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(aEmail);
        if (aEmail == null || aEmail.length() == 0) {
            throw new AuthenticationException("Email not specified!");
        }
        if (!matcher.find()) {
            throw new AuthenticationException("Specified email is not correct!");
        }
        if (!localUserService.localEmailExists(aEmail)) {
            throw new AuthenticationException("A profile with email '" + aEmail + "' does not exist!");
        }
    }

    @Override
    public void validatePasswordInUserDb(LoginDto aDto) {
        String login = getUserLogin(aDto);
        switchDatabase(aDto);

        UserEntity user = userService.findByLogin(login);
        if (!encoder.matches(aDto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Invalid password!");
        }
    }

    @Override
    public void validateLoginData(LoginDto aDto) {
        validateLocalLoginOrEmail(aDto.getLoginOrEmail());
    }

    private void validateLocalLoginOrEmail(String aLoginOrEmail) {
        Matcher matcher = SecurityConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(aLoginOrEmail);
        if (matcher.find()) {
            validateEmail(aLoginOrEmail);
        } else {
            validateLogin(aLoginOrEmail);
        }
    }

    private void switchDatabase(LoginDto aDto) {
        String login = getUserLogin(aDto);

        DataSourceSettings ds = new DataSourceSettingsImpl();
        ds.setDdlAuto("none");
        ds.setUserName(login);
        ds.setPassword(aDto.getPassword());
        ds.setJDBConnectionUrlForUsername(login, null);

        switcher.applySettings(ds);
    }

    private String getUserLogin(LoginDto aDto) {
        Matcher matcher = SecurityConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(aDto.getLoginOrEmail());
        return !matcher.find() ?
                aDto.getLoginOrEmail() :
                localUserService.getByEmailAddress(aDto.getLoginOrEmail()).getLogin();
    }

}
