package com.passnail.security;

import java.util.regex.Pattern;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:56
 * Project: passnail-client
 */
public class SecurityConstants {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    public static final Integer SESSION_EXPIRATION_TIME_MILIS = 300000;

    private SecurityConstants() {
    }
}
