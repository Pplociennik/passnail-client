package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.LoginDto;

/**
 * A service for validating login credentials;
 * <p>
 * Created by: Pszemko at wtorek, 23.02.2021 00:38
 * Project: passnail-client
 */
public interface LoginValidationServiceIf {

    /**
     * Validates the user's password in the user's private local database.
     *
     * @param aDto Login credentials;
     */
    void validatePasswordInUserDb(LoginDto aDto);

    /**
     * Validates login credentials like login and email in the local authentication database.
     *
     * @param aDto Login credentials.
     */
    void validateLoginData(LoginDto aDto);
}
