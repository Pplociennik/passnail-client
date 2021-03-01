package com.passnail.core.main.config;


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
    ConfAttributes() {
    }

    // ATTRIBUTES ===========================================================

    /**
     * A variable holding a path to the application's installation directory.
     */
    private String installationPath;

    /**
     * A variable holding a path to the application's default authentication database.
     */
    private String dbPath;

    /**
     * A variable holding a login to the application's default auth database.
     */
    private String authDbLogin;

    /**
     * A variable holding a password to the application's default auth database.
     */
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
