package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.LoginDto;

/**
 * A service for logging users in.
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 00:27
 * Project: passnail-client
 */
public interface LoginServiceIf {

    /**
     * Creates a session for the logging user.
     *
     * @param aDto Login credentials.
     */
    void authenticateAndLoginUser(LoginDto aDto);
}
