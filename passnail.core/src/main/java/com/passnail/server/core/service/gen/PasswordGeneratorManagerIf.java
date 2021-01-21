package com.passnail.server.core.service.gen;

import com.passnail.server.core.tools.pass.PasswordGeneratorIf;

import java.io.IOException;

/**
 * Created by: Pszemko at środa, 20.01.2021 23:27
 * Project: passnail-client
 */
public interface PasswordGeneratorManagerIf {

    PasswordGeneratorIf createDefaultPasswordGenerator() throws IOException;

    void setNewPasswordLength(final Integer aNumber);

    void setNewLowerCaseCharactersNumber(final Integer aNumber);

    void setNewUpperCaseCharactersNumber(final Integer aNumber);

    void setNewDigitsNumber(final Integer aNumber);

    void setNewSpecialCharactersNumber(final Integer aNumber);
}
