package com.passnail.server.core.service.gen;

import com.passnail.client.core.tools.pass.PasswordGeneratorIf;
import com.passnail.server.core.service.prop.PropertyHandlerIf;

import java.io.IOException;

/**
 * Created by: Pszemko at środa, 20.01.2021 23:27
 * Project: passnail-client
 */
public interface PasswordGeneratorManagerIf {

    /**
     * Creates a new instance of {@link com.passnail.client.core.tools.pass.DefaultPasswordGenerator}.
     */
    void createNewDefaultPasswordGenerator();

    /**
     * Returns a new password generated using the {@link com.passnail.client.core.tools.pass.PasswordGeneratorIf}.
     *
     * @return {@link String} Generated password
     */
    String generateNewPassword();

    /**
     * Loads the default properties file for the specific generator.
     *
     * @throws IOException When the file cannot be found.
     */
    void loadDefaultProperties() throws IOException;

    /**
     * Saves the properties of the generator to the properties file.
     *
     * @throws IOException When the file cannot be found.
     */
    void saveProperties() throws IOException;

    /**
     * Sets a new length for passwords being generated.
     *
     * @param aNumber The new length.
     */
    void setPasswordLength(final Integer aNumber);

    /**
     * Sets a number of special characters contained in passwords being generated.
     *
     * @param aNumber
     */
    void setSpecialCharactersNumber(final Integer aNumber);

    /**
     * Sets a number of lower case characters contained in passwords being generated.
     *
     * @param aNumber
     */
    void setLowerCaseNumber(final Integer aNumber);

    /**
     * Sets a number of upper case characters contained in passwords being generated.
     *
     * @param aNumber
     */
    void setUpperCaseNumber(final Integer aNumber);

    /**
     * Sets a number of digits contained in passwords being generated.
     *
     * @param aNumber
     */
    void setDigitsNumber(final Integer aNumber);

    /**
     * Returns a length of the passwords being generated.
     *
     * @return {@link Integer}
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
     * Returns a {@link com.passnail.client.core.tools.pass.PasswordGeneratorIf} being hold by the manager.
     *
     * @return {@link com.passnail.client.core.tools.pass.PasswordGeneratorIf}
     */
    PasswordGeneratorIf getGenerator();

    /**
     * Returns a {@link PropertyHandlerIf} being hold by the manager.
     *
     * @return {@link PropertyHandlerIf}
     */
    PropertyHandlerIf getPropertyHandler();

    /**
     * Sets a {@link PasswordGeneratorIf} for the manager.
     *
     * @param generator
     */
    void setGenerator(PasswordGeneratorIf generator);

    /**
     * Returns a {@link PropertyHandlerIf} being hold by the manager.
     *
     * @return {@link PropertyHandlerIf}
     */
    PropertyHandlerIf getHandler();

    /**
     * Sets a {@link PropertyHandlerIf} for the manager.
     *
     * @param handler
     */
    void setHandler(PropertyHandlerIf handler);

    /**
     * Resets properties to default values.
     *
     * @throws IOException When the file cannot be found.
     */
    void resetPropertiesToDefaults() throws IOException;

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
    void setAllProperties(final Integer aPasswordLength, final Integer aLowerCaseNumber, final Integer aUpperCaseNumber, final Integer aDigitsNumber, final Integer aSpecialCharactersNumber) throws IOException;


}
