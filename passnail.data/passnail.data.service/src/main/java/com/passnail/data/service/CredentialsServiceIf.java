package com.passnail.data.service;

import com.passnail.data.model.entity.CredentialsEntity;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import com.passnail.data.transfer.model.dto.UserDto;

/**
 * A service containing functions for managing credentials stored in the local database.
 * <p>
 * Created by: Pszemko at poniedziałek, 01.03.2021 19:34
 * Project: passnail-client
 */
public interface CredentialsServiceIf {

    /**
     * Creates a new {@link CredentialsEntity} and saves it in the database.
     *
     * @param aCredentialsDto A dto containing information about the credentials being created.
     * @param aUserDto        A dto containing information about the user being the credentials' creator.
     */
    void sendNewCredentialsToLocalDatabase(CredentialsDto aCredentialsDto, UserDto aUserDto);
}
