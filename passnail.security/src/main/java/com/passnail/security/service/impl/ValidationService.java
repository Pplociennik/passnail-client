package com.passnail.security.service.impl;

import com.passnail.connect.service.LocalUserServiceIf;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.SecurityConstants;
import com.passnail.security.service.ValidationServiceIf;
import com.passnail.security.throwable.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:24
 * Project: passnail-client
 */
@Service
public class ValidationService implements ValidationServiceIf {

    @Autowired
    private LocalUserServiceIf localUserService;


    @Override
    public void validateRegistrationData(RegistrationDto aDto) {
        validateLogin(aDto.getLogin());
        validateEmail(aDto.getEmail());
        validatePassword(aDto.getPassword(), aDto.getPasswordRepeat());
    }

    private void validatePassword(String aPassword, String aPasswordRepeat) {
        Matcher matcher = SecurityConstants.VALID_PASSWORD_REGEX.matcher(aPassword);
        if (aPassword == null && aPasswordRepeat == null) {
            throw new AuthenticationException("Password not specified!");
        }
        if (!aPassword.equals(aPasswordRepeat)) {
            throw new AuthenticationException("Passwords do not match!");
        }
    }

    private void validateEmail(String aEmail) {
        Matcher matcher = SecurityConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(aEmail);
        if (!matcher.find()) {
            throw new AuthenticationException("Specified email is not correct!");
        }
    }

    private void validateLogin(String aLogin) {
        if (aLogin == null) {
            throw new AuthenticationException("Login not specified!");
        }
        if (localUserService.localLoginExists(aLogin)) {
            throw new AuthenticationException("Specified login is not available!");
        }
    }
}
