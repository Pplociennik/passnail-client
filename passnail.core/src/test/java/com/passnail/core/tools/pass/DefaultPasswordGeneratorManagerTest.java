package com.passnail.core.tools.pass;

import com.passnail.common.throwable.core.IncorrectPropertiesException;
import com.passnail.core.main.config.ConfAttributes;
import com.passnail.core.service.gen.DefaultPasswordGeneratorManager;
import com.passnail.core.service.gen.PasswordGeneratorManagerIf;
import com.passnail.core.tools.prop.generator.DefaultGeneratorPropertyHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This tests if the {@link DefaultPasswordGeneratorManager} works properly.
 * <p>
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

    /**
     * Tests creating a {@link DefaultPasswordGenerator} with default properties using {@link DefaultGeneratorPropertyHandler}.
     */
    @Test
    public void testCreatingDefaultPasswordGeneratorWithDefaultProperties() {

        assertNotNull(manager.getGenerator());
        assertNotNull(manager.getPropertyHandler());
    }

    /**
     * Tests if password with specified number of special characters is being properly generated.
     */
    @Test
    public void testGeneratingASpecifiedNumberOfSpecialCharacters() {

        int loadedValue = manager.getSpecialCharactersNumber();

        assertEquals(loadedValue, countSpecialCharacters());
    }

    /**
     * Tests if password with specified number of lower case letters is being properly generated.
     */
    @Test
    public void testGeneratingASpecifiedNumberOfLowerCaseCharacters() {

        int loadedValue = manager.getLowerCaseNumber();

        assertEquals(loadedValue, countSpecifiedCharacters(97, 122));

    }

    /**
     * Tests if password with specified number of upper case letters is being properly generated.
     */
    @Test
    public void testGeneratingASpecifiedNumberOfUpperCaseCharacters() {

        int loadedValue = manager.getUpperCaseNumber();

        assertEquals(loadedValue, countSpecifiedCharacters(65, 90));
    }

    /**
     * Tests if password with specified number of digits is being properly generated.
     */
    @Test
    public void testGeneratingASpecifiedNumberOfDigits() {

        int loadedValue = manager.getDigitsNumber();

        assertEquals(loadedValue, countSpecifiedCharacters(48, 57));
    }

    /**
     * Tests an uniqueness of passwords being generated.
     */
    @Test
    public void testPasswordsUniqueness() {

        Set<String> generatedPasswords = new HashSet<>();

        Integer passwordsNumber = 10000;

        for (int i = 0; i < passwordsNumber; i++) {
            generatedPasswords.add(manager.generateNewPassword());
        }

        assertEquals(passwordsNumber, generatedPasswords.size());
    }

    /**
     * Tests if generated password has a valid length.
     */
    @Test
    public void testGeneratedPasswordLength() {

        Integer passwordLength = manager.getPasswordLength();

        assertEquals(passwordLength, manager.generateNewPassword().length());
    }

    /**
     * Tests if passwords are being generating properly after changing properties.
     */
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

    /**
     * Tests saving properties. Checks if properties were saved correctly.
     *
     * @throws IOException
     */
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

    /**
     * Tests if setting inconsistent properties is managed properly.
     *
     * @throws IOException
     */
    @Test
    public void testSettingInconsistentProperties() throws IOException {

        assertThrows(IncorrectPropertiesException.class, () -> {
            manager.setUpperCaseNumber(120);
            manager.saveProperties();
        });
    }

    /**
     * Counts the specified characters.
     *
     * @param aMinCode Min. ASCII code taken into consideration during the counting process.
     * @param aMaxCode Max. ASCII code taken into consideration during the counting process.
     * @return Number of the characters in the newly generated password.
     */
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

    /**
     * Counts special characters in newly generated password.
     *
     * @return Number of the characters in the newly generated password.
     */
    private int countSpecialCharacters() {

        String password = manager.generateNewPassword();

        int charactersCounter = 0;

        for (char c : password.toCharArray()) {
            if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {
                charactersCounter++;
            }
        }

        return charactersCounter;

    }
}