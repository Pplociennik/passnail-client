package com.passnail.server.core;

import com.passnail.data.service.impl.UserService;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettings;
import com.passnail.security.config.datasource.DataSourceSettingsImpl;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.server.core.app.AppConfig;
import com.passnail.server.core.app.config.ConfAttributes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:35
 * Project: passnail-client
 */
@SpringBootTest(classes = {AppConfig.class})
public class DataSourceSettingsImplTest {

    private static ConfAttributes conf = ConfAttributes.INSTANCE;

    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceSettingsSwitcher switcher;

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @BeforeEach
    private void setAttributes() {
//        conf.setInstallationPath("C:/");
        conf.setAuthDbLogin("ex");
        conf.setAuthDbPassword("ex");
    }

    @Test
    public void testDatabaseSwitcher() throws IOException {
        DataSourceSettings ds = new DataSourceSettingsImpl();
        ds.setTestUrl();
        ds.setUserName(conf.getAuthDbLogin());
        ds.setPassword(conf.getAuthDbPassword());
        ds.setDdlAuto("create");

        switcher.applySettings(ds);

        RegistrationDto dto = new RegistrationDto();
        dto.setLogin("EX2");
        dto.setPassword("ex2A!dfgs");
        dto.setPasswordRepeat("ex2A!dfgs");
        dto.setEmail("ex2@ex.com");
        authenticationService.registerNewUserProfile(dto);

        String u2 = "ex_user_2";

        DataSourceSettings ds2 = new DataSourceSettingsImpl();
        ds2.setJDBConnectionUrlForUsername(u2, null);
        ds2.setUserName(u2);
        ds2.setPassword(u2);


        RegistrationDto dto2 = new RegistrationDto();
        dto2.setLogin("EX23232");
        dto2.setPassword("ex2A!dfgs");
        dto2.setPasswordRepeat("ex2A!dfgs");
        dto2.setEmail("ex22323@ex.com");
        switcher.applySettings(ds2);
        authenticationService.registerNewUserProfile(dto2);

        Assertions.assertNotNull(userService.findByLogin(dto2.getLogin()));
        Assertions.assertNull(userService.findByLogin(dto.getLogin()));
    }

    @AfterEach
    public void clear() {
        File f = new File("./data");
        f.delete();
    }

}