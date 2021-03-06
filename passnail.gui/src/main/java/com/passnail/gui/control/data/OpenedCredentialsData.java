package com.passnail.gui.control.data;

import lombok.Data;

/**
 * Created by: Pszemko at sobota, 06.03.2021 23:11
 * Project: passnail-client
 */
public enum OpenedCredentialsData {

    INSTANCE();

    OpenedCredentialsData() {
    }

    private String description;

    private String shortName;

    private String login;

    private String password;

    private String url;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
