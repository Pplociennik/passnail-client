package com.passnail.server.core.tools.pass;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by: Pszemko at czwartek, 21.01.2021 02:45
 * Project: passnail-client
 */
public class DefaultPasswordGeneratorManagerTest {

    final Integer TEST_PASSWORD_LENGTH = 32;

    PasswordGeneratorManagerIf manager;

    @BeforeAll
    public static void prepareConfAttributes() throws IOException {
        ConfAttributes attributes = ConfAttributes.INSTANCE;
        attributes.setInstallationPath(System.getProperty("user.dir"));
    }

    @BeforeEach
    public void preparePasswordGeneratorAndManager() throws IOException {
        manager = new DefaultPasswordGeneratorManager();
        manager.createNewDefaultPasswordGenerator();
        manager.loadDefaultProperties();
    }


    @Test
    public void testCreatingDefaultPasswordGeneratorWithDefaultProperties() {

        assertNotNull(manager.getGenerator());
    }

    @Test
    public void testGeneratingASpecifiedNumberOfSpecialCharacters() {

        String password = manager.generateNewPassword();

        int loadedValue = manager.getSpecialCharactersNumber();
        int specialCharCount = 0;

        for (char c : password.toCharArray()) {
            if (c >= 33 && c <= 47) {
                specialCharCount++;
            }
        }
        assertEquals(loadedValue, specialCharCount);
    }

    @Test
    public void testPasswordsUniqueness() {

        Set<String> generatedPasswords = new HashSet<>();

        Integer passwordsNumber = 10000;

        for (int i = 0; i < passwordsNumber; i++) {
            generatedPasswords.add(manager.generateNewPassword());
        }

        assertEquals(passwordsNumber, generatedPasswords.size());
    }

    @Test
    public void testGeneratedPasswordLength() {

        Integer passwordLength = manager.getPasswordLength();

        assertEquals(passwordLength, manager.generateNewPassword().length());
    }

    @Test
    public void testPasswordGenerationAfterPropertyChange() {

        manager.setPasswordLength(TEST_PASSWORD_LENGTH);
        assertEquals(TEST_PASSWORD_LENGTH, manager.generateNewPassword().length());
    }

    @Test
    public void testPropertiesAfterUpdate() throws IOException {
        manager.setPasswordLength(TEST_PASSWORD_LENGTH);
        manager.updateProperties();

        PasswordGeneratorManagerIf managerTwo = new DefaultPasswordGeneratorManager();
        managerTwo.createNewDefaultPasswordGenerator();

        assertEquals(TEST_PASSWORD_LENGTH, managerTwo.getPasswordLength());
    }
}