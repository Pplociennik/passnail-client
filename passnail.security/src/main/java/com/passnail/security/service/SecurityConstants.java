package com.passnail.security.service;

import java.util.regex.Pattern;

/**
 * Created by: Pszemko at wtorek, 16.02.2021 00:56
 * Project: passnail-client
 */
public class SecurityConstants {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    private SecurityConstants() {
    }
}
