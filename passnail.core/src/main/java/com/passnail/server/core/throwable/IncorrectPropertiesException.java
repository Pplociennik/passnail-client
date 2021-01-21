package com.passnail.server.core.throwable;

/**
 * Created by: Pszemko at czwartek, 21.01.2021 21:57
 * Project: passnail-client
 */
public class IncorrectPropertiesException extends IllegalArgumentException {
    public IncorrectPropertiesException(final String s) {
        super(s);
    }

    public IncorrectPropertiesException() {
        super("Incorrect properties!");
    }
}
