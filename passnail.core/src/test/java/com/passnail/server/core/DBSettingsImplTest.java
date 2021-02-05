package com.passnail.server.core;

import com.passnail.connect.service.impl.UserService;
import com.passnail.data.transfer.model.dto.LocalUserDto;
import com.passnail.server.core.app.AppConfig;
import com.passnail.server.core.app.DBSettingsImpl;
import com.passnail.server.core.app.DBSettingsSwitcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by: Pszemko at piątek, 05.02.2021 02:35
 * Project: passnail-client
 */
@SpringBootTest(classes = {AppConfig.class})
public class DBSettingsImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DBSettingsSwitcher switcher;

    @Test
    public void testDatabaseSwitcher() {
        switcher.applySettings(new DBSettingsImpl());

        LocalUserDto dto = new LocalUserDto();
        dto.setLogin("EX2");
        dto.setLocal(true);
        dto.setPassword("ex2");
        userService.createNewLocalUser(dto);

        Assertions.assertNotNull(userService.findByLogin(dto.getLogin()));
    }

}