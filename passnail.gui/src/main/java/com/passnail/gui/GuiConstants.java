package com.passnail.gui;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 21:51
 * Project: passnail-client
 */
public enum GuiConstants {

    INSTANCE();

    private Boolean splashLoaded;

    GuiConstants() {
        splashLoaded = false;
    }

    public Boolean wasSplashLoaded() {
        return splashLoaded;
    }

    public void setSplashLoaded(Boolean splashLoaded) {
        this.splashLoaded = splashLoaded;
    }
}
