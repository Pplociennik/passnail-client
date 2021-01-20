package com.passnail.server.core.app.config;


import lombok.Getter;
import lombok.extern.log4j.Log4j2;


/**
 * A Singleton holding the application's starting attributes.
 */
@Getter
@Log4j2
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
    private String installationPath;

    // SETTERS ==============================================================

    public void setInstallationPath(String installationPath) {
        this.installationPath = installationPath;
        log.info("Installation path set.");
    }
}
