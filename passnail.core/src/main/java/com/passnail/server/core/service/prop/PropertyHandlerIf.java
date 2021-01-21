package com.passnail.server.core.service.prop;

import java.io.IOException;

/**
 * An interface of the property handlers.
 */
public interface PropertyHandlerIf {

    /**
     * Loads the properties file for the specific implementation.
     *
     * @throws IOException when the file cannot be found or does not exist.
     */
    void loadProperties() throws IOException;

    void updateProperties() throws IOException;

    Integer getPasswordLength();

    Integer getSpecialCharactersNumber();

    Integer getLowerCaseNumber();

    Integer getUpperCaseNumber();

    Integer getDigitsNumber();

    void setPasswordLength(final Integer aNumber);

    void setSpecialCharactersNumber(final Integer aNumber);

    void setLowerCaseNumber(final Integer aNumber);

    void setUpperCaseNumber(final Integer aNumber);

    void setDigitsNumber(final Integer aNumber);

}
