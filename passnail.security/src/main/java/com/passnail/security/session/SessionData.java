package com.passnail.security.session;

import lombok.Getter;

import static com.passnail.security.SecurityConstants.*;

/**
 * A Singleton enum holding the authorized user's tokens.
 * <p>
 * Created by: Pszemko at wtorek, 23.02.2021 00:04
 * Project: passnail-client
 */
@Getter
public enum SessionData {

    INSTANCE();

    SessionData() {
        this.token = UNAUTHORIZED_TOKEN_SESSION_DATA;
        this.onlineToken = UNAUTHORIZED_ONLINE_TOKEN_SESSION_DATA;
        this.password = UNAUTHORIZED_PASSWORD_SESSION_DATA;
    }

    /**
     * A token of the logged user.
     */
    private String token;

    /**
     * An online token for synchronization.
     */
    private String onlineToken;

    /**
     * A key for the decryption.
     */
    private String password;


    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOnlineToken(String onlineToken) {
        this.onlineToken = onlineToken;
    }
}
