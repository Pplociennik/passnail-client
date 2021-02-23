package com.passnail.core.main.test;


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
    private String dbPath;
    private String authDbLogin;
    private String authDbPassword;

    // SETTERS ==============================================================

    public void setInstallationPath(String installationPath) {
        this.installationPath = installationPath;
        log.info("Installation path set.");
    }

    public void setAuthDbLogin(String authDbLogin) {
        this.authDbLogin = authDbLogin;
        log.info("Default auth login set.");
    }

    public void setAuthDbPassword(String authDbPassword) {
        this.authDbPassword = authDbPassword;
        log.info("Default auth password set.");
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
        log.info("Default db path set.");
    }
}
