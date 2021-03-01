package com.passnail.common.throwable.core;

/**
 * An exception being thrown when the properties were not set correctly.
 * <p>
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
