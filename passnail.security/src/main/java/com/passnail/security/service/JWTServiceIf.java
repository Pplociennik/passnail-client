package com.passnail.security.service;

import com.passnail.data.transfer.model.dto.LoginDto;

/**
 * A service for creating and validating tokens.
 * <p>
 * Created by: Pszemko at poniedziałek, 22.02.2021 23:28
 * Project: passnail-client
 */
public interface JWTServiceIf {

    /**
     * Checks if the specified token is valid and has not expired yet.
     *
     * @param aToken A token.
     * @param aKey   A key for token's decryption.
     */
    void validateToken(String aToken, String aKey);

    /**
     * Creates a token for the logging user.
     *
     * @param aDto Login credentials.
     * @return A token.
     */
    String createToken(LoginDto aDto);

    /**
     * Creates an online token for the synchronization user's data with the online database.
     *
     * @param aDto Login credentials.
     * @return An online token.
     */
    String createOnlineToken(LoginDto aDto);

    String getAuthorizedUserName(String aToken, String aKey);

    String getAuthorizedOnlineId(String aOnlineToken, String aKey);


}
