package com.passnail.server.core.tools.prop.generator;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.service.gen.DefaultPasswordGeneratorManager;
import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;
import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.throwable.IncorrectPropertiesException;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
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

        Assertions.assertThrows(IncorrectPropertiesException.class, () -> {
            handler.setNumberOfUpperCaseCharacters(45);
            handler.saveProperties();
        });
    }

    @Test
    public void testSettingANullValueParameter() {

        Assertions.assertThrows(IncorrectPropertiesException.class, () -> {
            handler.setNumberOfUpperCaseCharacters(null);
        });
    }

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