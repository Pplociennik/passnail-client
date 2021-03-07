package com.passnail.gui.control.tools;

/**
 * Created by: Pszemko at niedziela, 07.03.2021 07:06
 * Project: passnail-client
 */
public enum SplashScreenInfo {

    INSTANCE();

    private boolean isFinished = false;

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
