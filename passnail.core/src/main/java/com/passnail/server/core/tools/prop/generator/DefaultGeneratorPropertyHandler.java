package com.passnail.server.core.tools.prop.generator;

import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.pass.PasswordGeneratorIf;
import com.passnail.server.core.tools.prop.BasicPropertyHandler;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

import static com.passnail.server.core.tools.prop.PropertyConstants.*;
import static java.lang.String.valueOf;

/**
 * A property handler for the {@link PasswordGeneratorIf}. Contains methods for loading properties files and getting values of them.
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
        super.loadProperties(DEFAULT_GENERATOR_PROPERTIES_PATH);
        setDefaultGeneratorProperties();
    }

    @Override
    public void updateProperties() throws IOException {
        super.saveProperties(DEFAULT_GENERATOR_PROPERTIES_PATH);
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

        if (!aNumber.equals(this.passwordLength)) {
            this.passwordLength = aNumber;
            this.properties.setProperty(PASSWORD_LENGTH_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    @Override
    public void setSpecialCharactersNumber(Integer aNumber) {

    }

    @Override
    public void setLowerCaseNumber(Integer aNumber) {

    }

    @Override
    public void setUpperCaseNumber(Integer aNumber) {

    }

    @Override
    public void setDigitsNumber(Integer aNumber) {

    }

    /**
     * Sets a property defining a number of lower case characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfLowerCaseCharacters(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfLowerCaseCharacters)) {
            this.numberOfLowerCaseCharacters = aNumber;
            this.properties.setProperty(NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of upper case characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfUpperCaseCharacters(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfUpperCaseCharacters)) {
            this.numberOfUpperCaseCharacters = aNumber;
            this.properties.setProperty(NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of digits of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfDigits(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfDigits)) {
            this.numberOfDigits = aNumber;
            this.properties.setProperty(NUMBER_OF_DIGITS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets a property defining a number of special characters of passwords being generated.
     *
     * @param aNumber
     */
    public void setNumberOfSpecialCharacters(final Integer aNumber) {

        if (!aNumber.equals(this.numberOfSpecialCharacters)) {
            this.numberOfSpecialCharacters = aNumber;
            this.properties.setProperty(NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY, valueOf(aNumber));
        }
    }

    /**
     * Sets the properties loaded from file.
     */
    private void setDefaultGeneratorProperties() {
        setNumberOfDigits(Integer.parseInt(String.valueOf(properties.get(NUMBER_OF_DIGITS_PROPERTY_KEY))));
        setNumberOfLowerCaseCharacters(Integer.parseInt(String.valueOf(properties.get(NUMBER_OF_LOWER_CASE_CHARACTERS_PROPERTY_KEY))));
        setNumberOfSpecialCharacters(Integer.parseInt(String.valueOf(properties.get(NUMBER_OF_SPECIAL_CHARACTERS_PROPERTY_KEY))));
        setNumberOfUpperCaseCharacters(Integer.parseInt(String.valueOf(properties.get(NUMBER_OF_UPPER_CASE_CHARACTERS_PROPERTY_KEY))));
        setPasswordLength(Integer.parseInt(String.valueOf(properties.get(PASSWORD_LENGTH_PROPERTY_KEY))));

        log.info("The default generator properties has been loaded.");
    }


}
