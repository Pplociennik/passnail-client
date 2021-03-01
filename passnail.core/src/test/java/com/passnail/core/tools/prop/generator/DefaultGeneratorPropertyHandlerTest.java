package com.passnail.core.tools.prop.generator;

import com.passnail.common.throwable.core.IncorrectPropertiesException;
import com.passnail.core.main.config.ConfAttributes;
import com.passnail.core.service.gen.DefaultPasswordGeneratorManager;
import com.passnail.core.service.gen.PasswordGeneratorManagerIf;
import com.passnail.core.service.prop.PropertyHandlerIf;
import com.passnail.core.tools.pass.DefaultPasswordGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This tests the {@link DefaultGeneratorPropertyHandler} containing loading, changing and saving properties of the {@link DefaultPasswordGenerator}.
 * <p>
 * Created by: Pszemko at poniedziałek, 25.01.2021 22:33
 * Project: passnail-client
 */
public class DefaultGeneratorPropertyHandlerTest {

    PropertyHandlerIf handler;

    @BeforeAll
    public static void prepareConfAttributes() throws IOException {
        ConfAttributes attributes = ConfAttributes.INSTANCE;
        attributes.setInstallationPath(System.getProperty("user.dir"));
    }

    @BeforeEach
    public void setUp() throws IOException {
        handler = new DefaultGeneratorPropertyHandler();
        handler.loadProperties();
    }

    @AfterEach
    public void tearDown() throws IOException {
        handler.resetToDefaults();
    }

    @Test
    public void testImpossibleValueSetting() {

        assertThrows(IncorrectPropertiesException.class, () -> {
            handler.setNumberOfUpperCaseCharacters(45);
            handler.saveProperties();
        });
    }

    /**
     * Tests exception handling while setting the null value parameter.
     */
    @Test
    public void testSettingANullValueParameter() {

        assertThrows(IncorrectPropertiesException.class, () -> {
            handler.setNumberOfUpperCaseCharacters(null);
        });
    }

    /**
     * Tests if properties are correct after unsuccessful changing a property.
     */
    @Test
    public void testPropertiesAfterUnsuccessfulPropertyChange() {

        Integer lengthDefault = handler.getPasswordLength();
        Integer lowerCaseDefault = handler.getLowerCaseNumber();
        Integer upperCaseDefault = handler.getUpperCaseNumber();
        Integer digitsDefault = handler.getDigitsNumber();
        Integer specialCharsDefault = handler.getSpecialCharactersNumber();

        Integer lengthAfter = null;
        Integer lowerCaseAfter = null;
        Integer upperCaseAfter = null;
        Integer digitsAfter = null;
        Integer specialCharsAfter = null;

        handler.setNumberOfUpperCaseCharacters(56);
        try {
            handler.saveProperties();
        } catch (IncorrectPropertiesException | IOException e) {
            lengthAfter = handler.getPasswordLength();
            lowerCaseAfter = handler.getLowerCaseNumber();
            upperCaseAfter = handler.getUpperCaseNumber();
            digitsAfter = handler.getDigitsNumber();
            specialCharsAfter = handler.getSpecialCharactersNumber();
        }

        assertEquals(lengthAfter, lengthDefault);
        assertEquals(lowerCaseAfter, lowerCaseDefault);
        assertEquals(upperCaseAfter, upperCaseDefault);
        assertEquals(digitsAfter, digitsDefault);
        assertEquals(specialCharsAfter, specialCharsDefault);
    }

    /**
     * Tests if setting correct properties works properly.
     *
     * @throws IOException
     */
    @Test
    public void testSettingCorrectProperties() throws IOException {
        Integer lengthForChange = 74;
        Integer lowerCaseForChange = 23;
        Integer upperCaseForChange = 10;
        Integer digitsForChange = 14;
        Integer specialCharsForChange = 27;

        handler.setNumberOfUpperCaseCharacters(upperCaseForChange);
        handler.setNumberOfDigits(digitsForChange);
        handler.setNumberOfLowerCaseCharacters(lowerCaseForChange);
        handler.setNumberOfSpecialCharacters(specialCharsForChange);
        handler.setPasswordLength(lengthForChange);
        handler.saveProperties();

        Integer lengthAfter = handler.getPasswordLength();
        Integer lowerCaseAfter = handler.getLowerCaseNumber();
        Integer upperCaseAfter = handler.getUpperCaseNumber();
        Integer digitsAfter = handler.getDigitsNumber();
        Integer specialCharsAfter = handler.getSpecialCharactersNumber();

        PasswordGeneratorManagerIf manager = new DefaultPasswordGeneratorManager();
        manager.createNewDefaultPasswordGenerator();
        manager.loadDefaultProperties();

        String password = manager.generateNewPassword();

        assertEquals(lengthAfter, lengthForChange);
        assertEquals(lowerCaseAfter, lowerCaseForChange);
        assertEquals(upperCaseAfter, upperCaseForChange);
        assertEquals(digitsAfter, digitsForChange);
        assertEquals(specialCharsAfter, specialCharsForChange);

        assertEquals(password.length(), 74);

    }

    /**
     * Tests if setting properties in a single method works properly.
     *
     * @throws IOException
     */
    @Test
    public void testSettingCorrectPropertiesUsingMethodForAll() throws IOException {
        Integer lengthForChange = 74;
        Integer lowerCaseForChange = 23;
        Integer upperCaseForChange = 10;
        Integer digitsForChange = 14;
        Integer specialCharsForChange = 27;

        handler.setAll(lengthForChange, lowerCaseForChange, upperCaseForChange, digitsForChange, specialCharsForChange);
        handler.saveProperties();

        Integer lengthAfter = handler.getPasswordLength();
        Integer lowerCaseAfter = handler.getLowerCaseNumber();
        Integer upperCaseAfter = handler.getUpperCaseNumber();
        Integer digitsAfter = handler.getDigitsNumber();
        Integer specialCharsAfter = handler.getSpecialCharactersNumber();

        PasswordGeneratorManagerIf manager = new DefaultPasswordGeneratorManager();
        manager.createNewDefaultPasswordGenerator();
        manager.loadDefaultProperties();

        String password = manager.generateNewPassword();

        assertEquals(lengthAfter, lengthForChange);
        assertEquals(lowerCaseAfter, lowerCaseForChange);
        assertEquals(upperCaseAfter, upperCaseForChange);
        assertEquals(digitsAfter, digitsForChange);
        assertEquals(specialCharsAfter, specialCharsForChange);

        assertEquals(password.length(), 74);

    }
}