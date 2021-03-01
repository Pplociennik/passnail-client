package com.passnail.security.service.impl;

import com.passnail.data.model.entity.LocalUserEntity;
import com.passnail.data.model.entity.UserEntity;
import com.passnail.data.service.LocalUserServiceIf;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettings;
import com.passnail.security.config.datasource.DataSourceSettingsImpl;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.map.RegistrationDtoToLoginDtoMapper;
import com.passnail.security.service.LoginServiceIf;
import com.passnail.security.service.RegistrationServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 00:29
 * Project: passnail-client
 */
@Service
public class RegistrationService implements RegistrationServiceIf {


    @Autowired
    private LoginServiceIf loginService;

    @Autowired
    private LocalUserServiceIf localUserService;

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private DataSourceSettingsSwitcher switcher;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public void registerNewOfflineUserProfile(RegistrationDto aDto) {
        registerLocalUserName(aDto);
        registerLocalUser(aDto);

        loginService.authenticateAndLoginUser(mapRegistrationDtoToLoginDto(aDto));
    }

    private LoginDto mapRegistrationDtoToLoginDto(RegistrationDto aDto) {
        RegistrationDtoToLoginDtoMapper mapper = new RegistrationDtoToLoginDtoMapper();
        return mapper.map(aDto);
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
        authorizeNewlyRegisteredUser(aDto);
    }


    private void registerLocalUserName(RegistrationDto aDto) {
        LocalUserEntity localUserName = new LocalUserEntity();
        localUserName.setLogin(aDto.getLogin());
        localUserName.setEmailAddress(aDto.getEmail());

        localUserService.registerNewLocalUserName(localUserName);
    }

    private void switchDatabase(String aLogin, String aPassword) {
        DataSourceSettings ds = new DataSourceSettingsImpl();
        ds.setDdlAuto("create");
        ds.setJDBConnectionUrlForUsername(aLogin, "./data/");
        ds.setUserName(aLogin);
        ds.setPassword(aPassword);

        switcher.applySettings(ds);
    }

    private void authorizeNewlyRegisteredUser(RegistrationDto aDto) {
    }
}
