package com.passnail.core.tools.pass;

/**
 * Default Password Generator. Generates password depending od the number of specified characters.
 */
public interface PasswordGeneratorIf {

    /**
     * Returns a newly generated password.
     *
     * @param aPasswordLength          Expected length of a generated password.
     * @param aSpecialCharactersNumber Expected number of special characters contained in the generated password.
     * @param aLowerCaseNumber         Expected number of lower case characters contained in the generated password.
     * @param aUpperCaseNumber         Expected number of upper case contained in the generated password.
     * @param aDigitNumber             Expected number of digits contained in the generated password.
     * @return {@link String} The generated password.
     */
    String generateNewPassword(final Integer aPasswordLength, final Integer aSpecialCharactersNumber, final Integer aLowerCaseNumber, final Integer aUpperCaseNumber, final Integer aDigitNumber);
}
