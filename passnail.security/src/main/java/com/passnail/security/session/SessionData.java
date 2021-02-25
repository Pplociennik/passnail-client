package com.passnail.security.session;

import lombok.Getter;

import static com.passnail.security.SecurityConstants.*;

/**
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


    private String token;
    private String onlineToken;
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
