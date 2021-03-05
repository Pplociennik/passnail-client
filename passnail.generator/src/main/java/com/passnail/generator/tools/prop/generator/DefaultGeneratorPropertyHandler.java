package com.passnail.generator.tools.prop.generator;

import com.passnail.common.throwable.core.IncorrectPropertiesException;
import com.passnail.generator.service.prop.PropertyHandlerIf;
import com.passnail.generator.tools.pass.PasswordGeneratorIf;
import com.passnail.generator.tools.prop.BasicPropertyHandler;
import com.passnail.generator.tools.prop.PropertiesConstants;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

/**
 * {@inheritDoc}
 */
@Log4j2
public class DefaultGeneratorPropertyHandler extends BasicPropertyHandler implements PropertyHandlerIf {

    private Integer passwordLength;
    private Integer numberOfLowerCaseCharacters;
    private Integer numberOfUpperCaseCharacters;
    private Integer numberOfDigits;
    private Integer numberOfSpecialCharacters;

    /**
     * Loads properties for the {@link PasswordGeneratorIf}.
     *
     * @throws IOException
     */
    @Override
    public void loadProperties() throws IOException {
        super.loadProperties(PropertiesConstants.DEFAULT_GENERATOR_PROPERTIES_PATH);
        setDefaultGeneratorProperties();
    }

    @Override
    public void saveProperties() throws IOException {
        if (!arePropertiesConsistent(this.properties)) {
            loadProperties();
            throw new IncorrectPropertiesException();
        } else {
            super.saveProperties(PropertiesConstants.DEFAULT_GENERATOR_PROPERTIES_PATH);
        }
    }

    private boolean arePropertiesConsistent(Properties properties) {

        final Integer LOCAL_SUM = numberOfSpecialCharacters + numberOfDigits + numberOfLowerCaseCharacters + numberOfUpperCaseCharacters;

        final Integer LENGTH = parseInt(valueOf(properties.get((PropertiesConstants.PASSWORD_LENGTH_PROPERTY_KEY))));

        final Integer SPEC_CHARS = parseInt(valueOf(properties.get(PropertiesConstants.NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY)));
        final Integer LOWER_CASE_CHARS = parseInt(valueOf(properties.get(PropertiesConstants.NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY)));
        final Integer UPPER_CASE_CHARS = parseInt(valueOf(properties.get(PropertiesConstants.NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY)));
        final Integer DIGITS = parseInt(valueOf(properties.get(PropertiesConstants.NUMBER_OF_DIGITS_PROPERTY_KEY)));

        final Integer SUM = SPEC_CHARS + LOWER_CASE_CHARS + UPPER_CASE_CHARS + DIGITS;

        return SUM.equals(LENGTH) && LOCAL_SUM.equals(passwordLength) && SUM.equals(LOCAL_SUM);
    }

    @Override
    public Integer getPasswordLength() {
        return passwordLength;
    }

    @Override
    public Integer getSpecialCharactersNumber() {
        return numberOfSpecialCharacters;
    }

    @Override
    public Integer getLowerCaseNumber() {
        return numberOfLowerCaseCharacters;
    }

    @Override
    public Integer getUpperCaseNumber() {
        return numberOfUpperCaseCharacters;
    }

    @Override
    public Integer getDigitsNumber() {
        return numberOfDigits;
    }

    /**
     * Sets a property defining a length of passwords being generated.
     *
     * @param aNumber
     */
    public void setPasswordLength(final Integer aNumber) {
        checkIfNotNull(aNumber);

        if (!aNumber.equals(this.passwordLength) && Objects.nonNull(aNumber)) {
            this.passwordLength = aNumber;
            this.properties.setProperty(PropertiesConstants.PASSWORD_LENGTH_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of lower case characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfLowerCaseCharacters(final Integer aNumber) {
        checkIfNotNull(aNumber);

        if (!aNumber.equals(this.numberOfLowerCaseCharacters) && Objects.nonNull(aNumber)) {
            this.numberOfLowerCaseCharacters = aNumber;
            this.properties.setProperty(PropertiesConstants.NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of upper case characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfUpperCaseCharacters(final Integer aNumber) {
        checkIfNotNull(aNumber);

        if (!aNumber.equals(this.numberOfUpperCaseCharacters) && Objects.nonNull(aNumber)) {
            this.numberOfUpperCaseCharacters = aNumber;
            this.properties.setProperty(PropertiesConstants.NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of digits of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfDigits(final Integer aNumber) {
        checkIfNotNull(aNumber);

        if (!aNumber.equals(this.numberOfDigits) && Objects.nonNull(aNumber)) {
            this.numberOfDigits = aNumber;
            this.properties.setProperty(PropertiesConstants.NUMBER_OF_DIGITS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    @Override
    public void resetToDefaults() throws IOException {
        setNumberOfDigits(PropertiesConstants.NUMBER_OF_DIGITS_DEFAULT_VALUE);
        setNumberOfLowerCaseCharacters(PropertiesConstants.NUMBER_OF_LOWER_CASE_CHARACTERS_DEFAULT_VALUE);
        setNumberOfUpperCaseCharacters(PropertiesConstants.NUMBER_OF_UPPER_CASE_CHARACTERS_DEFAULT_VALUE);
        setNumberOfSpecialCharacters(PropertiesConstants.NUMBER_OF_SPECIAL_CHARACTERS_DEFAULT_VALUE);
        setPasswordLength(PropertiesConstants.PASSWORD_LENGTH_DEFAULT_VALUE);

        saveProperties();
    }

    @Override
    public void setAll(Integer aPasswordLength, Integer aLowerCaseNumber, Integer aUpperCaseNumber, Integer aDigitsNumber, Integer aSpecialCharactersNumber) throws IOException {
        setPasswordLength(aPasswordLength);
        setNumberOfLowerCaseCharacters(aLowerCaseNumber);
        setNumberOfUpperCaseCharacters(aUpperCaseNumber);
        setNumberOfDigits(aDigitsNumber);
        setNumberOfSpecialCharacters(aSpecialCharactersNumber);

        saveProperties();
    }

    /**
     * Sets a property defining a number of special characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfSpecialCharacters(final Integer aNumber) {
        checkIfNotNull(aNumber);

        if (!aNumber.equals(this.numberOfSpecialCharacters)) {
            this.numberOfSpecialCharacters = aNumber;
            this.properties.setProperty(PropertiesConstants.NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }

    }

    /**
     * Sets the properties loaded from file.
     */
    private void setDefaultGeneratorProperties() {

        setNumberOfDigits(parseInt(String.valueOf(properties.get(PropertiesConstants.NUMBER_OF_DIGITS_PROPERTY_KEY))));
        setNumberOfLowerCaseCharacters(parseInt(String.valueOf(properties.get(PropertiesConstants.NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY))));
        setNumberOfSpecialCharacters(parseInt(String.valueOf(properties.get(PropertiesConstants.NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY))));
        setNumberOfUpperCaseCharacters(parseInt(String.valueOf(properties.get(PropertiesConstants.NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY))));
        setPasswordLength(parseInt(String.valueOf(properties.get(PropertiesConstants.PASSWORD_LENGTH_PROPERTY_KEY))));

        if (!arePropertiesConsistent(properties)) {
            log.error("Incorrect properties!");
            throw new IncorrectPropertiesException();
        }

        log.info("The default generator properties has been loaded.");
    }

    private void checkIfNotNull(Object aObject) {
        if (aObject == null) {
            throw new IncorrectPropertiesException("A property cannot be null!");
        }
    }


}
