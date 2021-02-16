package com.passnail.server.core;

import com.passnail.connect.service.impl.UserService;
import com.passnail.data.transfer.model.dto.UserDto;
import com.passnail.server.core.app.AppConfig;
import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.security.config.datasource.DataSourceSettings;
import com.passnail.security.config.datasource.DataSourceSettingsImpl;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
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

        switcher.applySettings(ds);

        UserDto dto = new UserDto();
        dto.setLogin("EX2");
        dto.setLocal(true);
        dto.setPassword("ex2");
        userService.createNewUser(dto);

        String u2 = "ex_user_2";

        DataSourceSettings ds2 = new DataSourceSettingsImpl();
        ds2.setJDBConnectionUrlForUsername(u2, "./");
        ds2.setUserName(u2);
        ds2.setPassword(u2);


        UserDto dto2 = new UserDto();
        dto2.setLogin("EX23232");
        dto2.setLocal(true);
        dto2.setPassword("ex223232");
        switcher.applySettings(ds2);
        userService.createNewUser(dto2);

        Assertions.assertNotNull(userService.findByLogin(dto2.getLogin()));
        Assertions.assertNull(userService.findByLogin(dto.getLogin()));
    }

    @AfterEach
    public void clear() {
        File f = new File("./data");
        f.delete();
    }

}