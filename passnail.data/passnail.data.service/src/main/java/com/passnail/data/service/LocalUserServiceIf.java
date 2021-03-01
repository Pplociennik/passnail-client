package com.passnail.data.service;

import com.passnail.data.model.entity.LocalUserEntity;

/**
 * A service for managing the table of LocalUsers in the database.
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 01:09
 * Project: passnail-client
 */
public interface LocalUserServiceIf {

    /**
     * Returns true if user with specified email exists in the database.
     *
     * @param aEmail An email address of the user being searched.
     * @return True if user with specified email exists in the database.
     */
    Boolean localEmailExists(String aEmail);

    /**
     * Returns true if user with specified login exists in the database.
     *
     * @param aLogin An login address of the user being searched.
     * @return True if user with specified email exists in the database.
     */
    Boolean localLoginExists(String aLogin);

    /**
     * Registers a new local user name in the local authentication database.
     *
     * @param aLocalEntity A local user for registration.
     */
    void registerNewLocalUserName(LocalUserEntity aLocalEntity);

    /**
     * Returns a local user with specified email address found in the database.
     *
     * @param aEmail An email address of the user being searched.
     * @return {@link LocalUserEntity}
     */
    LocalUserEntity getByEmailAddress(String aEmail);
}
