package com.passnail.security.service.impl;

import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.LocalUserServiceIf;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.security.config.datasource.DataSourceSettings;
import com.passnail.security.config.datasource.DataSourceSettingsImpl;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.LoginValidationServiceIf;
import com.passnail.security.throwable.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
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


    @Override
    public void validateLocalLoginOrEmail(String aLoginOrEmail) {
        if (aLoginOrEmail.contains("@")) {
            validateEmail(aLoginOrEmail);
        } else {
            validateLogin(aLoginOrEmail);
        }
    }

    private void validateLogin(String aLogin) {
        if (!localUserService.localLoginExists(aLogin)) {
            throw new AuthenticationException("User '" + aLogin + "' does not exist!");
        }
    }

    private void validateEmail(String aEmail) {
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
        return !aDto.getLoginOrEmail().contains("@") ?
                aDto.getLoginOrEmail() :
                localUserService.getByEmailAddress(aDto.getLoginOrEmail()).getLogin();
    }

}
