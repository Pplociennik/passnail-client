package com.passnail.security.service;

/**
 * A service for authorization.
 * <p>
 * Created by: Pszemko at czwartek, 25.02.2021 01:26
 * Project: passnail-client
 */
public interface AuthorizationServiceIf {

    /**
     * Authorizes the user. Checks if the user is logged in.
     *
     * @return True if the user is currently logged in and the session has not expired.
     */
    Boolean isUserAuthorized();

    void getAuthorizedUserCredentials();
}
