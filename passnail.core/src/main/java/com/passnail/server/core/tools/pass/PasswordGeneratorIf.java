package com.passnail.server.core.tools.pass;

import java.io.IOException;

/**
 * An interface of the {@link com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator} containing methods for passwords generating.
 */
public interface PasswordGeneratorIf {

    public void loadProperties() throws IOException;

    public String generateNewPassword();

    void setPasswordLength(final Integer aNumber);

    void setNumberOfLowerCaseCharacters(final Integer aNumber);

    void setNumberOfUpperCaseCharacters(final Integer aNumber);

    void setNumberOfDigits(final Integer aNumber);

    public void setNumberOfSpecialCharacters(final Integer aNumber);
}
