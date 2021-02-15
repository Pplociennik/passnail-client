package com.passnail.server.core;

import com.passnail.connect.service.impl.UserService;
import com.passnail.data.transfer.model.dto.UserDto;
import com.passnail.server.core.app.AppConfig;
import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.app.config.datasource.DataSourceSettings;
import com.passnail.server.core.app.config.datasource.DataSourceSettingsImpl;
import com.passnail.server.core.app.config.datasource.DataSourceSettingsSwitcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @BeforeAll
    private static void setAttributes() {
//        conf.setInstallationPath("C:/");
        conf.setAuthDbLogin("ex");
        conf.setAuthDbPassword("ex");
    }

    @Test
    public void testDatabaseSwitcher() {
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

        Assertions.assertNotNull(userService.findByLogin(dto.getLogin()));
    }

}