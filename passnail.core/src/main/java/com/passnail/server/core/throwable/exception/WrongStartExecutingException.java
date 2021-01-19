package com.passnail.server.core.throwable.exception;

public class WrongStartExecutingException extends IllegalArgumentException {
    public WrongStartExecutingException(final String s) {
        throw new WrongStartExecutingException(s);
    }

    public WrongStartExecutingException() {
        throw new WrongStartExecutingException("The application was not run correctly!");
    }
}
