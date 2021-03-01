package com.passnail.common.throwable.security;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 19:30
 * Project: passnail-client
 */
public class AuthenticationException extends IllegalArgumentException {
    public AuthenticationException() {
        super("Incorrect credentials!");
    }

    public AuthenticationException(final String s) {
        super(s);
    }
}
