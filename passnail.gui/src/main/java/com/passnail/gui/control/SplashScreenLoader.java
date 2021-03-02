package com.passnail.gui.control;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 21:21
 * Project: passnail-client
 */
@Component
public class SplashScreenLoader {

    @Value("classpath:/splash.fxml")
    private Resource splashResource;

    @Value("classpath:/auth.fxml")
    private Resource authResource;

    public void loadSplashScreen(AnchorPane aRoot) {
        try {

            StackPane pane = new FXMLLoader(splashResource.getURL()).load();
            aRoot.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = new FXMLLoader(authResource.getURL()).load();
                    aRoot.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
