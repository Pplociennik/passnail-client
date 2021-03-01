package com.passnail.common.throwable.security;

/**
 * Created by: Pszemko at czwartek, 25.02.2021 01:16
 * Project: passnail-client
 */
public class ExpiredSessionException extends IllegalArgumentException {

    public ExpiredSessionException(final String s) {
        super(s);
    }

    public ExpiredSessionException() {
        super("Session expired. Please login again.");
    }
}
