package com.passnail.server.core.tools.pass;

import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;
import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.prop.generator.DefaultGeneratorPropertyHandler;

import java.io.IOException;

import static com.passnail.server.core.tools.prop.PropertiesConstants.*;

/**
 * {@inheritDoc}
 *
 * Created by: Pszemko at środa, 20.01.2021 23:39
 * Project: passnail-client
 */
public class DefaultPasswordGeneratorManager implements PasswordGeneratorManagerIf {

    private PasswordGeneratorIf generator;

    private PropertyHandlerIf handler;


    public DefaultPasswordGeneratorManager() {
    }


    public PasswordGeneratorIf getGenerator() {
        return generator;
    }

    public void setGenerator(PasswordGeneratorIf generator) {
        this.generator = generator;
    }

    public PropertyHandlerIf getHandler() {
        return handler;
    }

    public void setHandler(PropertyHandlerIf handler) {
        this.handler = handler;
    }

    @Override
    public void resetPropertiesToDefaults() throws IOException {
        setDigitsNumber(NUMBER_OF_DIGITS_DEFAULT_VALUE);
        setLowerCaseNumber(NUMBER_OF_LOWER_CASE_CHARACTERS_DEFAULT_VALUE);
        setUpperCaseNumber(NUMBER_OF_UPPER_CASE_CHARACTERS_DEFAULT_VALUE);
        setSpecialCharactersNumber(NUMBER_OF_SPECIAL_CHARACTERS_DEFAULT_VALUE);
        setPasswordLength(PASSWORD_LENGTH_DEFAULT_VALUE);

        saveProperties();
    }


    public void createNewDefaultPasswordGenerator() {
        this.generator = new DefaultPasswordGenerator();
        this.handler = new DefaultGeneratorPropertyHandler();
    }

    public String generateNewPassword() {
        return
                this.generator.generateNewPassword(
                        handler.getPasswordLength(),
                        handler.getSpecialCharactersNumber(),
                        handler.getLowerCaseNumber(),
                        handler.getUpperCaseNumber(),
                        handler.getDigitsNumber()
                );
    }

    public void loadDefaultProperties() throws IOException {
        this.handler.loadProperties();
    }

    public void saveProperties() throws IOException {
        this.handler.saveProperties();
    }

    public void setPasswordLength(final Integer aNumber) {
        this.handler.setPasswordLength(aNumber);
    }

    public void setSpecialCharactersNumber(final Integer aNumber) {
        this.handler.setNumberOfSpecialCharacters(aNumber);
    }

    public void setLowerCaseNumber(final Integer aNumber) {
        this.handler.setNumberOfLowerCaseCharacters(aNumber);
    }

    public void setUpperCaseNumber(final Integer aNumber) {
        this.handler.setNumberOfUpperCaseCharacters(aNumber);
    }

    public void setDigitsNumber(final Integer aNumber) {
        this.handler.setNumberOfDigits(aNumber);
    }

    public Integer getPasswordLength() {
        return this.handler.getPasswordLength();
    }

    public Integer getSpecialCharactersNumber() {
        return this.handler.getSpecialCharactersNumber();
    }

    public Integer getLowerCaseNumber() {
        return this.handler.getLowerCaseNumber();
    }

    public Integer getUpperCaseNumber() {
        return this.handler.getUpperCaseNumber();
    }

    public Integer getDigitsNumber() {
        return this.handler.getDigitsNumber();
    }
}
