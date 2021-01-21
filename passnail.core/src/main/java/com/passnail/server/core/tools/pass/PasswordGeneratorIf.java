package com.passnail.server.core.tools.pass;

/**
 * An interface of the {@link com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator} containing methods for passwords generating.
 */
public interface PasswordGeneratorIf {

    String generateNewPassword(final Integer aPasswordLength, final Integer aSpecialCharactersNumber, final Integer aLowerCaseNumber, final Integer aUpperCaseNumber, final Integer aDigitNumber);
}
