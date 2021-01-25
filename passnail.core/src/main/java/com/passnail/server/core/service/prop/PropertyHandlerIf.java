package com.passnail.server.core.service.prop;

import com.passnail.server.core.tools.pass.PasswordGeneratorIf;
import com.sun.istack.NotNull;

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

    /**
     * Saves properties to the properties file.
     *
     * @throws IOException When the file cannot be found.
     */
    void saveProperties() throws IOException;

    /**
     * Returns a length of passwords being generated.
     *
     * @return
     */
    Integer getPasswordLength();

    /**
     * Returns a number of special characters contained in passwords being generated.
     *
     * @return {@link Integer}
     */
    Integer getSpecialCharactersNumber();

    /**
     * Returns a number of lower case characters contained in passwords being generated.
     *
     * @return {@link Integer}
     */
    Integer getLowerCaseNumber();

    /**
     * Returns a number of upper case characters contained in passwords being generated.
     *
     * @return {@link Integer}
     */
    Integer getUpperCaseNumber();

    /**
     * Returns a number of digits contained in passwords being generated.
     *
     * @return {@link Integer}
     */
    Integer getDigitsNumber();

    /**
     * Returns a {@link PasswordGeneratorIf} being hold by the manager.
     *
     * @return {@link PasswordGeneratorIf}
     */
    void setPasswordLength(final Integer aNumber);

    /**
     * Sets a number of lower case characters contained in passwords being generated.
     *
     * @param aNumber
     */
    void setNumberOfSpecialCharacters(final Integer aNumber);

    /**
     * Sets a number of upper case characters contained in passwords being generated.
     *
     * @param aNumber
     */
    void setNumberOfLowerCaseCharacters(final Integer aNumber);

    /**
     * Sets a number of digits contained in passwords being generated.
     *
     * @param aNumber
     */
    void setNumberOfUpperCaseCharacters(final Integer aNumber);

    /**
     * Returns a length of the passwords being generated.
     *
     * @return {@link Integer}
     */
    void setNumberOfDigits(final Integer aNumber);

    void resetToDefaults() throws IOException;
}
