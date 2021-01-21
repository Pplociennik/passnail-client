package com.passnail.server.core.tools.pass;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

/**
 * {@inheritDoc}
 */
@Log4j2
@Getter
public class DefaultPasswordGenerator extends PasswordGenerator implements PasswordGeneratorIf {

    private static final String ERROR_CODE = "ERROR WHILE USING SPECIAL CHARACTERS";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()_+";

    private final CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
    private CharacterRule lowerCaseRule;

    private final CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
    private CharacterRule upperCaseRule;

    private final CharacterData digitChars = EnglishCharacterData.Digit;
    private CharacterRule digitRule;

    private final CharacterData specialChars = new CharacterData() {
        @Override
        public String getErrorCode() {
            return ERROR_CODE;
        }

        @Override
        public String getCharacters() {
            return SPECIAL_CHARACTERS;
        }
    };
    private final CharacterRule specialCharactersRule;

    DefaultPasswordGenerator() {
        this.specialCharactersRule = new CharacterRule(specialChars);
        this.lowerCaseRule = new CharacterRule(lowerCaseChars);
        this.upperCaseRule = new CharacterRule(upperCaseChars);
        this.digitRule = new CharacterRule(digitChars);
    }

    public String generateNewPassword(final Integer aPasswordLength, final Integer aSpecialCharactersNumber, final Integer aLowerCaseNumber, final Integer aUpperCaseNumber, final Integer aDigitNumber) {
        specialCharactersRule.setNumberOfCharacters(aSpecialCharactersNumber);
        lowerCaseRule.setNumberOfCharacters(aLowerCaseNumber);
        upperCaseRule.setNumberOfCharacters(aUpperCaseNumber);
        digitRule.setNumberOfCharacters(aDigitNumber);

        return generatePassword(aPasswordLength, specialCharactersRule, lowerCaseRule, upperCaseRule, digitRule);
    }
}
