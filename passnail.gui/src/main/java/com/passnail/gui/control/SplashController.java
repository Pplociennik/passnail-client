package com.passnail.gui.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by: Pszemko at niedziela, 07.03.2021 07:00
 * Project: passnail-client
 */
@Component
@Lazy(value = true)
public class SplashController implements Initializable {

    @FXML
    private StackPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


