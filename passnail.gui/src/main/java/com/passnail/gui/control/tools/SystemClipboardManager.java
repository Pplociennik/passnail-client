package com.passnail.gui.control.tools;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.awt.*;
import java.io.IOException;

/**
 * Created by: Pszemko at piątek, 05.03.2021 21:18
 * Project: passnail-client
 */
public class SystemClipboardManager {

    public void copyTextToTheClipboard(String aText, String aMessage) throws IOException, AWTException {
        ClipboardContent content = new ClipboardContent();
        content.putString(aText);
        Clipboard.getSystemClipboard().setContent(content);

        new NotificationSender().sendWindowsNotification("PASSNAIL", aMessage);
    }
}
