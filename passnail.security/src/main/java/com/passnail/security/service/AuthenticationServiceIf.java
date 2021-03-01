package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:27
 * Project: passnail-client
 */
public interface AuthenticationServiceIf {

    /**
     * Registers a new offline user.
     *
     * @param aDto Register credentials.
     */
    void registerNewUserProfile(RegistrationDto aDto);

    /**
     * Logs in a user and sets a session data.
     *
     * @param aDto A login credentials.
     */
    void authenticateUser(LoginDto aDto);

    /**
     * Logs out the user. Clears the session data and switches the database to the default authentication database.
     *
     * @param aTestDb If true - then after logout the database will switch to the test authentication database.
     */
    void logout(Boolean aTestDb);
}
