package com.passnail.server.core.tools.pass;

import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;
import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.prop.generator.DefaultGeneratorPropertyHandler;

import java.io.IOException;

/**
 * {@inheritDoc}
 * <p>
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

    @Override
    public PropertyHandlerIf getPropertyHandler() {
        return this.handler;
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
        this.handler.resetToDefaults();
    }

    @Override
    public void setAllProperties(Integer aPasswordLength, Integer aLowerCaseNumber, Integer aUpperCaseNumber, Integer aDigitsNumber, Integer aSpecialCharactersNumber) throws IOException {
        this.handler.setAll(aPasswordLength, aLowerCaseNumber, aUpperCaseNumber, aDigitsNumber, aSpecialCharactersNumber);
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
