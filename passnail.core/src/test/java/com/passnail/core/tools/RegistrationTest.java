package com.passnail.core.tools;

import com.passnail.core.main.config.AppConfig;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.service.RegistrationServiceIf;
import com.passnail.security.session.SessionData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void testRegistration() {
        SessionData sessionData = SessionData.INSTANCE;

        RegistrationDto dto = new RegistrationDto();
        dto.setEmail("myexampleemail@gmail.com");
        dto.setLogin("EX22222");
        dto.setPassword("eXpassword!2");
        dto.setPasswordRepeat("eXpassword!2");

        authenticationService.registerNewUserProfile(dto);

        assertNotNull(userService.findByLogin("EX22222"));
        assertNotNull(sessionData.getPassword());
        assertNotNull(sessionData.getToken());
    }
}
