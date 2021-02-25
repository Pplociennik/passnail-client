package com.passnail.core.tools;

import com.passnail.core.main.config.AppConfig;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.throwable.AuthenticationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

/**
 * Created by: Pszemko at czwartek, 25.02.2021 21:13
 * Project: passnail-client
 */
@SpringBootTest(classes = {AppConfig.class})
public class ValidationTest {

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @Autowired
    private DataSourceSettingsSwitcher switcher;


    @BeforeEach
    public void prepareTestDb() {
        switcher.switchToTestDatabase();
    }

    @Test
    public void testEmailValidation() {

        Random rand = new Random();
        Long currentTime = rand.nextLong();
        RegistrationDto dto = new RegistrationDto();
        dto.setEmail(currentTime + "@gmail.com");
        dto.setLogin("test_user_" + currentTime);
        dto.setPassword("eXpassword!2");
        dto.setPasswordRepeat("eXpassword!2");

        authenticationService.registerNewUserProfile(dto);
        authenticationService.logout(true);

        Long currentTime2 = System.currentTimeMillis();
        RegistrationDto dto2 = new RegistrationDto();
        dto2.setEmail(currentTime + "@gmail.com");
        dto2.setLogin("test_user_" + currentTime2);
        dto2.setPassword("eXpassword!2");
        dto2.setPasswordRepeat("eXpassword!2");

        Assertions.assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto2);
        });

        authenticationService.logout(true);

        Long currentTime3 = System.currentTimeMillis();
        RegistrationDto dto3 = new RegistrationDto();
        dto3.setEmail(currentTime3 + "$%^gmail.com");
        dto3.setLogin("test_user_" + currentTime3);
        dto3.setPassword("eXpassword!2");
        dto3.setPasswordRepeat("eXpassword!2");

        Assertions.assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto3);
        });
    }
}
