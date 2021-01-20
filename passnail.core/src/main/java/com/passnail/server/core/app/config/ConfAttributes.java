package com.passnail.server.core.app.config;


import lombok.Getter;


/**
 * A Singleton holding the application's starting attributes.
 */
@Getter
public enum ConfAttributes {

    /**
     * An instance of the Singleton object.
     */
    INSTANCE();


    /**
     * A private constructor.
     */
    private ConfAttributes() {
    }

    // ATTRIBUTES ===========================================================

    /**
     * A variable holding a path to the application installation directory.
     */
    private String INSTALLATION_PATH;

    // SETTERS ==============================================================

    public void setINSTALLATION_PATH(String INSTALLATION_PATH) {
        this.INSTALLATION_PATH = INSTALLATION_PATH;
    }
}
