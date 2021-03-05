package com.passnail.gui.control.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by: Pszemko at piątek, 05.03.2021 20:53
 * Project: passnail-client
 */
public class NotificationSender {

    public void sendWindowsNotification(String aTitle, String aBody) throws IOException, AWTException {

        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //Get image
        BufferedImage trayIconImage = ImageIO.read(getClass().getResource("/test_Logo.png"));
        int trayIconWidth = new TrayIcon(trayIconImage).getSize().width;
        TrayIcon trayIcon = new TrayIcon(trayIconImage.getScaledInstance(trayIconWidth, -1, Image.SCALE_SMOOTH));

        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);

        trayIcon.setToolTip(aBody);
        tray.add(trayIcon);
        trayIcon.displayMessage(aTitle, aBody, TrayIcon.MessageType.NONE);
    }
}
