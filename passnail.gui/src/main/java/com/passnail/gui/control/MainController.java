package com.passnail.gui.control;

import com.passnail.gui.GuiConstants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 19:35
 * Project: passnail-client
 */
@Component
public class MainController implements Initializable {

    @Autowired
    private SplashScreenLoader loader;

    @FXML
    private AnchorPane root;

    private GuiConstants constants = GuiConstants.INSTANCE;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!constants.wasSplashLoaded()) {
            loader.loadSplashScreen(root);
            constants.setSplashLoaded(true);
        }
    }
}
