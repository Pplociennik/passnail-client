package com.passnail.security.throwable;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 20:01
 * Project: passnail-client
 */
public class AuthorizationException extends IllegalArgumentException {
    public AuthorizationException() {
        super("Authorization failed!");
    }

    public AuthorizationException(final String s) {
        super(s);
    }
}
