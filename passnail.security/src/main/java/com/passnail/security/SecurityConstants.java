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

    public static final String UNAUTHORIZED_TOKEN_SESSION_DATA = "404_TKN_DAT";
    public static final String UNAUTHORIZED_ONLINE_TOKEN_SESSION_DATA = "404_ONL_TKN_DAT";
    public static final String UNAUTHORIZED_PASSWORD_SESSION_DATA = "404_PASS_DAT";

    public static final String DB_DEFAULT_AUTH_URL = "jdbc:h2:file:./data/UNAuthenDB.mv";
    public static final String DB_DEFAULT_AUTH_USERNAME = System.getenv("USERNAME");
    public static final String DB_DEFAULT_AUTH_PASSWORD = System.getenv("COMPUTERNAME");
    public static final String DB_DEFAULT_AUTH_DDL = "update";

    public static final String DB_TEST_URL = "jdbc:h2:file:./data/test/TEST_CREDDB";
    public static final String DB_TEST_USERNAME = "test_username";
    public static final String DB_TEST_PASSWORD = "test_password";
    public static final String DB_TEST_DDL = "create";

    private SecurityConstants() {
    }
}
