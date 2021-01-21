package com.passnail.server.core.tools.pass;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;
import org.junit.jupiter.api.*;

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

    @AfterEach
    public void resetToDefaults() throws IOException {
        manager.resetPropertiesToDefaults();
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
    public void testPasswordGenerationAfterPropertiesChange() {

        Integer lgth = 32;
        Integer lc = 20;
        Integer uc = 7;
        Integer digits = 3;
        Integer sc = 2;

        manager.setPasswordLength(lgth);
        manager.setDigitsNumber(digits);
        manager.setLowerCaseNumber(lc);
        manager.setSpecialCharactersNumber(sc);
        manager.setUpperCaseNumber(uc);
        assertEquals(lgth, manager.generateNewPassword().length());
    }

    @Test
    public void testPropertiesAfterUpdate() throws IOException {
        Integer lgth = 32;
        Integer lc = 20;
        Integer uc = 7;
        Integer digits = 3;
        Integer sc = 2;

        manager.setPasswordLength(lgth);
        manager.setDigitsNumber(digits);
        manager.setLowerCaseNumber(lc);
        manager.setSpecialCharactersNumber(sc);
        manager.setUpperCaseNumber(uc);
        manager.saveProperties();

        PasswordGeneratorManagerIf managerTwo = new DefaultPasswordGeneratorManager();
        managerTwo.createNewDefaultPasswordGenerator();
        managerTwo.loadDefaultProperties();

        assertEquals(lgth, managerTwo.getPasswordLength());
    }
}