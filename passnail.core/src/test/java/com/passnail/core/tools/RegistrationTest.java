package com.passnail.core.tools;

import com.passnail.core.main.test.AppConfig;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.RegistrationServiceIf;
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
    private RegistrationServiceIf registrationService;

    @Autowired
    private UserServiceIf userService;

    @Test
    public void testRegistration() {
        RegistrationDto dto = new RegistrationDto();
        dto.setEmail("myexampleemail@gmail.com");
        dto.setLogin("EX22222");
        dto.setPassword("ex_password!2");
        dto.setPasswordRepeat("ex_password!2");

        registrationService.registerNewOfflineUserProfile(dto);

        assertNotNull(userService.findByLogin("EX22222"));
    }
}
