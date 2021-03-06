package com.passnail.security.session;

import com.passnail.data.transfer.model.dto.CredentialsDto;
import com.passnail.security.SecurityConstants;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

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
        this.authorizedUsername = SecurityConstants.UNAUTHORIZED_USERNAME_SESSION_DATA;
        this.authorizedOnlineId = UNAUTHORIZED_ONLINE_TOKEN_SESSION_DATA;
        this.authorizedPassNumber = String.valueOf(0);
        this.authorizedUserSavedCredentials = Collections.emptyList();

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

    private String authorizedUsername;

    private String authorizedOnlineId;

    private String authorizedPassNumber;

    private List<CredentialsDto> authorizedUserSavedCredentials;


    public void setToken(String token) {
        this.token = token;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOnlineToken(String onlineToken) {
        this.onlineToken = onlineToken;
    }

    public void setAuthorizedUsername(String authorizedUsername) {
        this.authorizedUsername = authorizedUsername;
    }

    public void setAuthorizedOnlineId(String authorizedOnlineId) {
        this.authorizedOnlineId = authorizedOnlineId;
    }

    public void setAuthorizedPassNumber(String authorizedPassNumber) {
        this.authorizedPassNumber = authorizedPassNumber;
    }

    public void setAuthorizedUserSavedCredentials(List<CredentialsDto> authorizedUserSavedCredentials) {
        this.authorizedUserSavedCredentials = authorizedUserSavedCredentials;
    }

}
