package com.passnail.server.core.service.gen;

import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.pass.PasswordGeneratorIf;

import java.io.IOException;

/**
 * Created by: Pszemko at środa, 20.01.2021 23:27
 * Project: passnail-client
 */
public interface PasswordGeneratorManagerIf {

    public void createNewDefaultPasswordGenerator();

    public String generateNewPassword();

    public void loadDefaultProperties() throws IOException;

    public void updateProperties() throws IOException;

    public void setPasswordLength(final Integer aNumber);

    public void setSpecialCharactersNumber(final Integer aNumber);

    public void setLowerCaseNumber(final Integer aNumber);

    public void setUpperCaseNumber(final Integer aNumber);

    public void setDigitsNumber(final Integer aNumber);

    public Integer getPasswordLength();

    public Integer getSpecialCharactersNumber();

    public Integer getLowerCaseNumber();

    public Integer getUpperCaseNumber();

    public Integer getDigitsNumber();

    public PasswordGeneratorIf getGenerator();

    public void setGenerator(PasswordGeneratorIf generator);

    public PropertyHandlerIf getHandler();

    public void setHandler(PropertyHandlerIf handler);


}
