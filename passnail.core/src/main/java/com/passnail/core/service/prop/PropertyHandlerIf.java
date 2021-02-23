package com.passnail.core.service.prop;

import com.passnail.core.tools.pass.PasswordGeneratorIf;

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

    /**
     * Resets properties to default values.
     *
     * @throws IOException When the file cannot be found.
     */

    void resetToDefaults() throws IOException;

    /**
     * Sets all the properties to the specified values.
     *
     * @param aPasswordLength          A length of the password.
     * @param aLowerCaseNumber         A number of the lower case characters.
     * @param aUpperCaseNumber         A number of the upper case characters.
     * @param aDigitsNumber            A number of digits.
     * @param aSpecialCharactersNumber A number of the special characters.
     * @throws IOException When the file cannot be found.
     */
    void setAll(final Integer aPasswordLength, final Integer aLowerCaseNumber, final Integer aUpperCaseNumber, final Integer aDigitsNumber, final Integer aSpecialCharactersNumber) throws IOException;
}
