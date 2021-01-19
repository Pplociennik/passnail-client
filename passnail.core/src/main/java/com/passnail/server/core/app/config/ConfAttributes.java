package com.passnail.server.core.app.config;


import lombok.Getter;


/**
 * A Singleton holding the application's starting attributes.
 */
@Getter
public enum ConfAttributes {
    INSTANCE();

    private ConfAttributes() {
    }

    // ATTRIBUTES ===========================================================

    private String INSTALLATION_PATH;

    // SETTERS ==============================================================

    public void setINSTALLATION_PATH(String INSTALLATION_PATH) {
        this.INSTALLATION_PATH = INSTALLATION_PATH;
    }
}
