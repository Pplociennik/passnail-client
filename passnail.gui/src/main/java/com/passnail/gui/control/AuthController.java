package com.passnail.gui.control;

import com.passnail.gui.GuiConstants;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 22:05
 * Project: passnail-client
 */
@Component
public class AuthController implements Initializable {


    @FXML
    private AnchorPane root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    private GuiConstants constants = GuiConstants.INSTANCE;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
