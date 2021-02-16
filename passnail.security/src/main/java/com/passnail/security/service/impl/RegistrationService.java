package com.passnail.security.service.impl;

import com.passnail.connect.service.LocalUserServiceIf;
import com.passnail.connect.service.UserServiceIf;
import com.passnail.data.model.entity.LocalUserEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettings;
import com.passnail.security.config.datasource.DataSourceSettingsImpl;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.RegistrationServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:29
 * Project: passnail-client
 */
@Service
public class RegistrationService implements RegistrationServiceIf {

    @Autowired
    private LocalUserServiceIf localUserService;

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private DataSourceSettingsSwitcher switcher;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public UserEntity registerNewOfflineUserProfile(RegistrationDto aDto) {
        registerLocalUserName(aDto.getLogin());
        registerLocalUser(aDto);
        return userService.findByEmail(aDto.getEmail());
    }

    private void registerLocalUser(RegistrationDto aDto) {
        switchDatabase(aDto.getLogin(), aDto.getPassword());

        Date creationDate = new Date();
        UserEntity localEntity = UserEntity.builder()
                .local(true)
                .creationDate(creationDate)
                .emailAddress(aDto.getEmail())
                .login(aDto.getLogin())
                .password(encoder.encode(aDto.getPassword()))
                .build();

        userService.registerNewLocalUser(localEntity);
    }


    private void registerLocalUserName(String aLogin) {
        LocalUserEntity localLogin = new LocalUserEntity();
        localLogin.setLogin(aLogin);

        localUserService.registerNewLocalUserName(localLogin);
    }

    private void switchDatabase(String aLogin, String aPassword) {
        DataSourceSettings ds = new DataSourceSettingsImpl();
        ds.setDdlAuto("create");
        ds.setJDBConnectionUrlForUsername(aLogin, "./data");
        ds.setUserName(aLogin);
        ds.setPassword(encoder.encode(aPassword));

        switcher.applySettings(ds);
    }
}
