package com.passnail.server.core.tools.pass;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;
import com.passnail.server.core.throwable.IncorrectPropertiesException;
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
    public void setUp() throws IOException {
        manager = new DefaultPasswordGeneratorManager();
        manager.createNewDefaultPasswordGenerator();
        manager.loadDefaultProperties();
    }

    @AfterEach
    public void tearDown() throws IOException {
        manager.resetPropertiesToDefaults();
    }


    @Test
    public void testCreatingDefaultPasswordGeneratorWithDefaultProperties() {

        assertNotNull(manager.getGenerator());
        assertNotNull(manager.getPropertyHandler());
    }

    @Test
    public void testGeneratingASpecifiedNumberOfSpecialCharacters() {

        int loadedValue = manager.getSpecialCharactersNumber();

        assertEquals(loadedValue, countSpecifiedCharacters(33, 47));
    }

    @Test
    public void testGeneratingASpecifiedNumberOfLowerCaseCharacters() {

        int loadedValue = manager.getLowerCaseNumber();

        assertEquals(loadedValue, countSpecifiedCharacters(97, 122));

    }

    @Test
    public void testGeneratingASpecifiedNumberOfUpperCaseCharacters() {

        int loadedValue = manager.getUpperCaseNumber();

        assertEquals(loadedValue, countSpecifiedCharacters(65, 90));
    }

    @Test
    public void testGeneratingASpecifiedNumberOfDigits() {

        int loadedValue = manager.getDigitsNumber();

        assertEquals(loadedValue, countSpecifiedCharacters(48, 57));
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
    public void testPropertiesAfterSaving() throws IOException {
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

    @Test
    public void testSettingInconsistentProperties() throws IOException {

        Assertions.assertThrows(IncorrectPropertiesException.class, () -> {
            manager.setUpperCaseNumber(120);
            manager.saveProperties();
        });
    }

    private Integer countSpecifiedCharacters(final Integer aMinCode, final Integer aMaxCode) {

        String password = manager.generateNewPassword();

        int charactersCounter = 0;

        for (char c : password.toCharArray()) {
            if (c >= aMinCode && c <= aMaxCode) {
                charactersCounter++;
            }
        }

        return charactersCounter;
    }
}