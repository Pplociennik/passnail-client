package com.passnail.security.session;

import lombok.Getter;

/**
 * Created by: Pszemko at wtorek, 23.02.2021 00:04
 * Project: passnail-client
 */
@Getter
public enum SessionData {

    INSTANCE();

    private SessionData() {
    }


    private String token;
    private String password;


    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
