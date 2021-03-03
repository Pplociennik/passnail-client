package com.passnail.gui.control.tools;

import javafx.application.Platform;

/**
 * Created by: Pszemko at środa, 03.03.2021 19:23
 * Project: passnail-client
 */
public class PlatformUtils {

    public static void run(Runnable treatment) {
        if (treatment == null) throw new IllegalArgumentException("The treatment to perform can not be null");

        if (Platform.isFxApplicationThread()) treatment.run();
        else Platform.runLater(treatment);
    }
}
