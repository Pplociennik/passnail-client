package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.RegistrationDto;

/**
 * A service for registration of the new local user profiles (usernames).
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 00:28
 * Project: passnail-client
 */
public interface RegistrationServiceIf {

    /**
     * Creates a new local user name in the local authentication database.
     *
     * @param aDto Registration data.
     */
    void registerNewOfflineUserProfile(RegistrationDto aDto);
}
