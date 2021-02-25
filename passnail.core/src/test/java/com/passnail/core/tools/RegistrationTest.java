package com.passnail.core.tools;

import com.passnail.core.main.config.AppConfig;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.session.SessionData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.passnail.security.SecurityConstants.UNAUTHORIZED_TOKEN_SESSION_DATA;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by: Pszemko at wtorek, 23.02.2021 02:39
 * Project: passnail-client
 */
@SpringBootTest(classes = {AppConfig.class})
public class RegistrationTest {

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private DataSourceSettingsSwitcher switcher;


    @BeforeEach
    public void clearData() throws IOException {
        switcher.switchToTestDatabase();
    }


    @Test
    public void testRegistrationWithLoginAfterwards() {
        SessionData sessionData = SessionData.INSTANCE;

        Long currentTime = System.currentTimeMillis();
        RegistrationDto dto = new RegistrationDto();
        dto.setEmail("myexampleemail@gmail.com");
        dto.setLogin("test_user_" + currentTime);
        dto.setPassword("eXpassword!2");
        dto.setPasswordRepeat("eXpassword!2");

        authenticationService.registerNewUserProfile(dto);

        assertNotNull(userService.findByLogin("test_user_" + currentTime));
        assertNotEquals(sessionData.getToken(), UNAUTHORIZED_TOKEN_SESSION_DATA);
        assertEquals(sessionData.getPassword(), dto.getPassword());
    }

    @Test
    public void testRegistrationWithLogoutAfterwards() {
        SessionData sessionData = SessionData.INSTANCE;

        Long currentTime = System.currentTimeMillis();
        RegistrationDto dto = new RegistrationDto();
        dto.setEmail("myexampleemail@gmail.com");
        dto.setLogin("test_user_" + currentTime);
        dto.setPassword("eXpassword!2");
        dto.setPasswordRepeat("eXpassword!2");

        authenticationService.registerNewUserProfile(dto);

        authenticationService.logout();

        assertEquals(sessionData.getToken(), UNAUTHORIZED_TOKEN_SESSION_DATA);
    }
}
