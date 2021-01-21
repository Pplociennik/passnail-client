package com.passnail.server.core.tools.pass;

import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.factory.PropertyHandlerFactoryIf;
import com.passnail.server.core.tools.factory.impl.propertyhandler.PropertyHandlerFactory;
import lombok.extern.log4j.Log4j2;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import java.io.IOException;
import java.util.Properties;

import static com.passnail.server.core.tools.prop.PropertyConstants.*;
import static java.lang.String.valueOf;

/**
 * An implementation of the {@link PasswordGeneratorIf}. Containing methods for passwords generating and setting options for the process.
 */
@Log4j2
public class DefaultPasswordGenerator extends PasswordGenerator implements PasswordGeneratorIf {

    /**
     * A Property Handler factory interface.
     */
    PropertyHandlerFactoryIf propertyHandlerFactory;

    /**
     * A Property Handler.
     */
    PropertyHandlerIf propertyHandler;

    private Integer passwordLength;
    private Integer numberOfLowerCaseCharacters;
    private Integer numberOfUpperCaseCharacters;
    private Integer numberOfDigits;
    private Integer numberOfSpecialCharacters;

    /**
     * The {@link Properties} typed object which stores the generator's properties loaded from file.
     */
    private Properties generatorProperties;

    private static final String ERROR_CODE = "ERROR WHILE USING SPECIAL CHARACTERS";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+";

    private final CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    private CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);

    private final CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    private CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);

    private final CharacterData digitChars = EnglishCharacterData.Digit;
    private CharacterRule digitRule = new CharacterRule(digitChars);

    private final CharacterData specialChars = new CharacterData() {
        @Override
        public String getErrorCode() {
            return ERROR_CODE;
        }

        @Override
        public String getCharacters() {
            return SPECIAL_CHARACTERS;
        }
    };
    private final CharacterRule specialCharactersRule = new CharacterRule(specialChars);

    DefaultPasswordGenerator() {
        propertyHandlerFactory = new PropertyHandlerFactory();
        propertyHandler = propertyHandlerFactory.getGeneratorPropertyHandler();
        generatorProperties = propertyHandler.getProperties();
    }

    /**
     * Loads the properties from file.
     *
     * @throws IOException when the file could not be found.
     */
    public void loadProperties() throws IOException {
        propertyHandler.loadProperties();

        setDefaultGeneratorProperties();
    }

    /**
     * Returns a new password generated using the properties.
     *
     * @return {@link String} the generated password
     */
    public String generateNewPassword() {
        return generatePassword(passwordLength, specialCharactersRule, lowerCaseRule, upperCaseRule, digitRule);
    }

    /**
     * Sets a property defining a length of passwords being generated.
     *
     * @param aNumber
     */
    public void setPasswordLength(final Integer aNumber) {

        if (!aNumber.equals(this.passwordLength)) {
            this.passwordLength = aNumber;
            this.generatorProperties.setProperty(PASSWORD_LENGTH_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of lower case characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfLowerCaseCharacters(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfLowerCaseCharacters)) {
            lowerCaseRule.setNumberOfCharacters(aNumber);
            this.numberOfLowerCaseCharacters = aNumber;
            this.generatorProperties.setProperty(NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of upper case characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfUpperCaseCharacters(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfUpperCaseCharacters)) {
            upperCaseRule.setNumberOfCharacters(aNumber);
            this.numberOfUpperCaseCharacters = aNumber;
            this.generatorProperties.setProperty(NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of digits of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfDigits(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfDigits)) {
            digitRule.setNumberOfCharacters(aNumber);
            this.numberOfDigits = aNumber;
            this.generatorProperties.setProperty(NUMBER_OF_DIGITS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of special characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfSpecialCharacters(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfSpecialCharacters)) {
            specialCharactersRule.setNumberOfCharacters(aNumber);
            this.numberOfSpecialCharacters = aNumber;
            this.generatorProperties.setProperty(NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets the properties loaded from file.
     */
    private void setDefaultGeneratorProperties() {
        setNumberOfDigits(Integer.parseInt(String.valueOf(generatorProperties.get(NUMBER_OF_DIGITS_PROPERTY_KEY))));
        setNumberOfLowerCaseCharacters(Integer.parseInt(String.valueOf(generatorProperties.get(NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY))));
        setNumberOfSpecialCharacters(Integer.parseInt(String.valueOf(generatorProperties.get(NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY))));
        setNumberOfUpperCaseCharacters(Integer.parseInt(String.valueOf(generatorProperties.get(NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY))));
        setPasswordLength(Integer.parseInt(String.valueOf(generatorProperties.get(PASSWORD_LENGTH_PROPERTY_KEY))));

        log.info("The default generator properties has been loaded.");
    }

}
