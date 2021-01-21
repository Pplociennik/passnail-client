package com.passnail.server.core.tools.pass;

import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;

import java.io.IOException;

/**
 * Created by: Pszemko at środa, 20.01.2021 23:39
 * Project: passnail-client
 */
public class PasswordGeneratorManager implements PasswordGeneratorManagerIf {

    PasswordGeneratorIf generator;

    public PasswordGeneratorManager(PasswordGeneratorIf generator) {
        this.generator = generator;
    }

    public PasswordGeneratorManager() {
    }

    @Override
    public PasswordGeneratorIf createDefaultPasswordGenerator() throws IOException {
        generator = new DefaultPasswordGenerator();
        prepareDefaultConfiguration();

        return generator;
    }

    @Override
    public void setNewPasswordLength(final Integer aNumber) {
        this.generator.setPasswordLength(aNumber);
    }

    @Override
    public void setNewLowerCaseCharactersNumber(final Integer aNumber) {
        this.generator.setNumberOfLowerCaseCharacters(aNumber);
    }

    @Override
    public void setNewUpperCaseCharactersNumber(final Integer aNumber) {
        this.generator.setNumberOfUpperCaseCharacters(aNumber);
    }

    @Override
    public void setNewDigitsNumber(final Integer aNumber) {
        this.generator.setNumberOfDigits(aNumber);
    }

    @Override
    public void setNewSpecialCharactersNumber(final Integer aNumber) {
        this.generator.setNumberOfSpecialCharacters(aNumber);
    }

    private void prepareDefaultConfiguration() throws IOException {
        generator.loadProperties();
    }
}
