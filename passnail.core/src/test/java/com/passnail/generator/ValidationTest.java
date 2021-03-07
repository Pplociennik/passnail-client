package com.passnail.generator;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.generator.main.config.AppConfig;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.config.datasource.DataSourceSettingsSwitcher;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.service.LoginValidationServiceIf;
import com.passnail.security.service.RegistrationValidationServiceIf;
import com.passnail.security.service.impl.AuthenticationService;
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
    private RegistrationValidationServiceIf registrationValidationService;

    @Autowired
    private LoginValidationServiceIf loginValidationService;

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
        Long currentTime = 1L;
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
            registrationValidationService.validateRegistrationData(dto);
        });

        RegistrationDto dto2 = new RegistrationDto();
        dto2.setLogin("TEST_LOGIN_V" + 2);
        dto2.setEmail("testemail2@passtest.com");
        dto2.setPassword(tooShortPass);
        dto2.setPasswordRepeat(tooShortPass);

        assertThrows(AuthenticationException.class, () -> {
            registrationValidationService.validateRegistrationData(dto2);
        });

        RegistrationDto dto3 = new RegistrationDto();
        dto3.setLogin("TEST_LOGIN_V" + 3);
        dto3.setEmail("testemail3@passtest.com");
        dto3.setPassword(passWithoutSpecialChar);
        dto3.setPasswordRepeat(passWithoutSpecialChar);

        assertThrows(AuthenticationException.class, () -> {
            registrationValidationService.validateRegistrationData(dto3);
        });

        RegistrationDto dto4 = new RegistrationDto();
        dto4.setLogin("TEST_LOGIN_V" + 4);
        dto4.setEmail("testemail4@passtest.com");
        dto4.setPassword(passWithoutDigit);
        dto4.setPasswordRepeat(passWithoutDigit);

        assertThrows(AuthenticationException.class, () -> {
            registrationValidationService.validateRegistrationData(dto4);
        });

        RegistrationDto dto5 = new RegistrationDto();
        dto5.setLogin("TEST_LOGIN_V" + 5);
        dto5.setEmail("testemail5@passtest.com");
        dto5.setPassword(passWithoutUpperCase);
        dto5.setPasswordRepeat(passWithoutUpperCase);

        assertThrows(AuthenticationException.class, () -> {
            registrationValidationService.validateRegistrationData(dto5);
        });

        RegistrationDto dto6 = new RegistrationDto();
        dto6.setLogin("TEST_LOGIN_V" + 6);
        dto6.setEmail("testemail6@passtest.com");
        dto6.setPassword(passWithoutLowerCase);
        dto6.setPasswordRepeat(passWithoutLowerCase);

        assertThrows(AuthenticationException.class, () -> {
            registrationValidationService.validateRegistrationData(dto6);
        });

        RegistrationDto dto7 = new RegistrationDto();
        dto7.setLogin("TEST_LOGIN_V" + 7 + "__CORRECT");
        dto7.setEmail("testemail7@passtest.com");
        dto7.setPassword(correctPass);
        dto7.setPasswordRepeat(correctPass);

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
