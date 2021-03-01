package com.passnail.security;

import java.util.regex.Pattern;

/**
 * Constants being used for security.
 * <p>
 * Created by: Pszemko at wtorek, 16.02.2021 00:56
 * Project: passnail-client
 */
public class SecurityConstants {

    /**
     * A pattern for account's email address.
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * A pattern for account's password.
     */
    public static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    /**
     * A logged in user's session expiration time in milis.
     */
    public static final Integer SESSION_EXPIRATION_TIME_MILIS = 300000;


    /**
     * A value for the unauthorized token (no user logged in).
     */
    public static final String UNAUTHORIZED_TOKEN_SESSION_DATA = "404_TKN_DAT";


    /**
     * A value for the unauthorized online token (no online user logged in/offline user logged in).
     */
    public static final String UNAUTHORIZED_ONLINE_TOKEN_SESSION_DATA = "404_ONL_TKN_DAT";

    /**
     * A value for the unauthorized decryption password.
     */
    public static final String UNAUTHORIZED_PASSWORD_SESSION_DATA = "404_PASS_DAT";


    /**
     * A default value for default local user authentication database's url.
     */
    public static final String DB_DEFAULT_AUTH_URL = "jdbc:h2:file:./data/UNAuthenDB.mv";

    /**
     * A default value for default local user authentication database's username.
     */
    public static final String DB_DEFAULT_AUTH_USERNAME = System.getenv("USERNAME");

    /**
     * A default value for default local user authentication database's password.
     */
    public static final String DB_DEFAULT_AUTH_PASSWORD = System.getenv("COMPUTERNAME");

    /**
     * A default value for default authentication database's ddl strategy.
     */
    public static final String DB_DEFAULT_AUTH_DDL = "update";


    /**
     * A default value for test local user authentication database's url.
     */
    public static final String DB_TEST_URL = "jdbc:h2:file:./data/test/TEST_AUTH_DB";

    /**
     * A default value for test local user authentication database's username.
     */
    public static final String DB_TEST_USERNAME = "test_username";

    /**
     * A default value for test local user authentication database's password.
     */
    public static final String DB_TEST_PASSWORD = "test_password";

    /**
     * A default value for test local user authentication database's ddl strategy.
     */
    public static final String DB_TEST_DDL = "create";

    private SecurityConstants() {
    }
}
