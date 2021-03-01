package com.passnail.core.tools;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.core.main.config.AppConfig;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.AuthenticationServiceIf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This tests a credentials' validation process during the registration and logging in.
 * <p>
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


    /**
     * Tests email validation.
     */
    @Test
    public void testEmailValidation() {

        Random rand = new Random();
        Long currentTime = rand.nextLong();
        RegistrationDto dto = new RegistrationDto();
        dto.setEmail(currentTime + "@passtest.com");
        dto.setLogin("test_user_" + currentTime);
        dto.setPassword("eXpassword!2");
        dto.setPasswordRepeat("eXpassword!2");

        authenticationService.registerNewUserProfile(dto);
        authenticationService.logout(true);

        Long currentTime2 = System.currentTimeMillis();
        RegistrationDto dto2 = new RegistrationDto();
        dto2.setEmail(currentTime + "@passtest.com");
        dto2.setLogin("test_user_" + currentTime2);
        dto2.setPassword("eXpassword!2");
        dto2.setPasswordRepeat("eXpassword!2");

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto2);
        });

        authenticationService.logout(true);

        Long currentTime3 = System.currentTimeMillis();
        RegistrationDto dto3 = new RegistrationDto();
        dto3.setEmail(currentTime3 + "$%^passtest.com");
        dto3.setLogin("test_user_" + currentTime3);
        dto3.setPassword("eXpassword!2");
        dto3.setPasswordRepeat("eXpassword!2");

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto3);
        });
    }

    /**
     * Tests password validation.
     */
    @Test
    public void testPasswordValidation() {

        String correctPass = "examplecorrectpassword12X!";
        String tooShortPass = "a!1A";
        String passWithoutSpecialChar = "examplepasswordwithoutspec1A";
        String passWithoutDigit = "examplePasswordWithoutDigit!";
        String passWithoutUpperCase = "examplepasswordwithoutuppercase1!";
        String passWithoutLowerCase = "EXAMPLEPASSWORDWITHOUTLOWERCASE!1";

        RegistrationDto dto = new RegistrationDto();
        dto.setLogin("TEST_LOGIN_V" + 1);
        dto.setEmail("testemail@passtest.com");
        dto.setPassword(correctPass);
        dto.setPasswordRepeat("incorrect_second_pass");

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto);
        });

        authenticationService.logout(true);
        RegistrationDto dto2 = new RegistrationDto();
        dto.setLogin("TEST_LOGIN_V" + 2);
        dto.setEmail("testemail2@passtest.com");
        dto.setPassword(tooShortPass);
        dto.setPasswordRepeat(tooShortPass);

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto2);
        });

        authenticationService.logout(true);
        RegistrationDto dto3 = new RegistrationDto();
        dto.setLogin("TEST_LOGIN_V" + 3);
        dto.setEmail("testemail3@passtest.com");
        dto.setPassword(passWithoutSpecialChar);
        dto.setPasswordRepeat(passWithoutSpecialChar);

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto3);
        });

        authenticationService.logout(true);
        RegistrationDto dto4 = new RegistrationDto();
        dto.setLogin("TEST_LOGIN_V" + 4);
        dto.setEmail("testemail4@passtest.com");
        dto.setPassword(passWithoutDigit);
        dto.setPasswordRepeat(passWithoutDigit);

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto4);
        });

        authenticationService.logout(true);
        RegistrationDto dto5 = new RegistrationDto();
        dto.setLogin("TEST_LOGIN_V" + 5);
        dto.setEmail("testemail5@passtest.com");
        dto.setPassword(passWithoutUpperCase);
        dto.setPasswordRepeat(passWithoutUpperCase);

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto5);
        });

        authenticationService.logout(true);
        RegistrationDto dto6 = new RegistrationDto();
        dto.setLogin("TEST_LOGIN_V" + 6);
        dto.setEmail("testemail6@passtest.com");
        dto.setPassword(passWithoutLowerCase);
        dto.setPasswordRepeat(passWithoutLowerCase);

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto6);
        });

        authenticationService.logout(true);
        RegistrationDto dto7 = new RegistrationDto();
        dto.setLogin("TEST_LOGIN_V" + 7 + "__CORRECT");
        dto.setEmail("testemail7@passtest.com");
        dto.setPassword(correctPass);
        dto.setPasswordRepeat(correctPass);

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto7);
        });
    }

    /**
     * Tests login validation.
     */
    @Test
    public void testLoginValidation() {

        String exampleLogin = "LOGIN_VALIDATION_TEST_LOGIN";

        RegistrationDto dto = new RegistrationDto();
        dto.setLogin("LOGIN_VALIDATION_TEST_LOGIN");
        dto.setPassword("ExamplePass12!");
        dto.setPasswordRepeat("ExamplePass12!");
        dto.setEmail("exampleEmail@passtest.com");

        authenticationService.registerNewUserProfile(dto);

        authenticationService.logout(true);

        assertThrows(AuthenticationException.class, () -> {
            authenticationService.registerNewUserProfile(dto);
        });

    }

}
