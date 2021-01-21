package com.passnail.server.core.service.gen;

import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.pass.PasswordGeneratorIf;

import java.io.IOException;

/**
 * Created by: Pszemko at środa, 20.01.2021 23:27
 * Project: passnail-client
 */
public interface PasswordGeneratorManagerIf {

    void createNewDefaultPasswordGenerator();

    String generateNewPassword();

    void loadDefaultProperties() throws IOException;

    void saveProperties() throws IOException;

    void setPasswordLength(final Integer aNumber);

    void setSpecialCharactersNumber(final Integer aNumber);

    void setLowerCaseNumber(final Integer aNumber);

    void setUpperCaseNumber(final Integer aNumber);

    void setDigitsNumber(final Integer aNumber);

    Integer getPasswordLength();

    Integer getSpecialCharactersNumber();

    Integer getLowerCaseNumber();

    Integer getUpperCaseNumber();

    Integer getDigitsNumber();

    PasswordGeneratorIf getGenerator();

    void setGenerator(PasswordGeneratorIf generator);

    PropertyHandlerIf getHandler();

    void setHandler(PropertyHandlerIf handler);

    void resetPropertiesToDefaults() throws IOException;


}
