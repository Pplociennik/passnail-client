package com.passnail.security.service.impl;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.data.service.LocalUserServiceIf;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.RegistrationValidationServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

import static com.passnail.security.SecurityConstants.VALID_EMAIL_ADDRESS_REGEX;
import static com.passnail.security.SecurityConstants.VALID_PASSWORD_REGEX;

/**
 * {@inheritDoc}
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 00:24
 * Project: passnail-client
 */
@Service
public class RegistrationValidationService implements RegistrationValidationServiceIf {

    @Autowired
    private LocalUserServiceIf localUserService;


    @Override
    public void validateRegistrationData(RegistrationDto aDto) {
        validateLogin(aDto.getLogin());
        validateEmail(aDto.getEmail());
        validatePassword(aDto.getPassword(), aDto.getPasswordRepeat());
    }

    private void validatePassword(String aPassword, String aPasswordRepeat) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(aPassword);
        if ((aPassword == null && aPasswordRepeat == null) || (aPassword.length() == 0 && aPasswordRepeat.length() == 0)) {
            throw new AuthenticationException("Password not specified!");
        }
        if (!aPassword.equals(aPasswordRepeat)) {
            throw new AuthenticationException("Passwords do not match!");
        }
        if (!matcher.find()) {
            throw new AuthenticationException("Password must contain at least 8 characters containing at least: 1 capital letter, 1 lowercase letter, 1 digit and 1 special character.");
        }
    }

    private void validateEmail(String aEmail) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(aEmail);
        if (aEmail == null || aEmail.length() == 0) {
            throw new AuthenticationException("Email not specified!");
        }
        if (!matcher.find()) {
            throw new AuthenticationException("Specified email is not correct!");
        }
        if (localUserService.localEmailExists(aEmail)) {
            throw new AuthenticationException("Specified email is not available!");
        }
    }

    private void validateLogin(String aLogin) {
        if (aLogin == null || aLogin.length() == 0) {
            throw new AuthenticationException("Login not specified!");
        }
        if (localUserService.localLoginExists(aLogin)) {
            throw new AuthenticationException("Specified login is not available!");
        }
    }
}
