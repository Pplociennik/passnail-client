package com.passnail.data.service;

import com.passnail.data.model.entity.UserEntity;

/**
 * A service for managing the table of Users in the database.
 * <p>
 * Created by: Pszemko at wtorek, 02.02.2021 21:50
 * Project: passnail-client
 */
public interface UserServiceIf {

    /**
     * Returns a user with the specified login found in the database.
     *
     * @param aLogin A login of the user being searched.
     * @return {@link UserEntity}
     */
    UserEntity findByLogin(String aLogin);

    /**
     * Returns a user with the specified email found in the database.
     *
     * @param aEmail An email of the user being searched.
     * @return {@link UserEntity}
     */
    UserEntity findByEmail(String aEmail);

    /**
     * Saves a new user in the database.
     *
     * @param aEntity A user being sent to the database.
     */
    void registerNewLocalUser(UserEntity aEntity);

    void saveUserInDatabase(UserEntity aEntity);
}
