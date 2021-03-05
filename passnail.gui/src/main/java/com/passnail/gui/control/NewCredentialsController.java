package com.passnail.gui.control;

import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.tools.PlatformUtils;
import com.passnail.gui.control.tools.StageManager;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.session.SessionData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.passnail.gui.GuiConstants.*;

/**
 * Created by: Pszemko at piątek, 05.03.2021 17:37
 * Project: passnail-client
 */
//TODO Javadoc
@Component
@Lazy(value = true)
public class NewCredentialsController implements Initializable {

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @Autowired
    @Lazy(value = true)
    private StageManager stageManager;


    @FXML
    private Label mainPaneHelpLabel;

    @FXML
    private Label userBarLogin;

    @FXML
    private Label userBarOnlineIdLabel;

    @FXML
    private Label userBarPasswordsLabel;


    @FXML
    void backButtonClicked(MouseEvent event) {
        switchToMainScene();
    }

    @FXML
    void backButtonEntered(MouseEvent event) {
        showHelpMessage(BACK_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void backButtonExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void generatorSettingsButtonClicked(MouseEvent event) throws IOException {

    }

    @FXML
    void generatorSettingsButtonEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void generatorSettingsButtonExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void showLibraryButtonClicked(MouseEvent event) {

    }

    @FXML
    void showLibraryButtonEntered(MouseEvent event) {
        showHelpMessage(SHOW_LIBRARY_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void showLibraryButtonExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void quickPasswordButtonClicked(MouseEvent event) {

    }

    @FXML
    void quickPasswordButtonEntered(MouseEvent event) {
        showHelpMessage(QUICK_PASSWORD_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void quickPasswordButtonExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void logoutButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(LOGOUT_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void logoutButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void logoutButtonClicked(MouseEvent event) {
        authenticationService.logout(false);
        switchToAuthScene();
    }


    @FXML
    void settingsButtonOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void settingsButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(SETTINGS_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void settingsButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void generatePasswordButtonClicked(MouseEvent event) {

    }

    @FXML
    void generatePasswordButtonEntered(MouseEvent event) {
        showHelpMessage(GENERATE_NEW_PASSWORD_HELP_MESSAGE);
    }

    @FXML
    void generatePasswordButtonExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void saveCredentialsButtonClicked(MouseEvent event) {

    }

    @FXML
    void saveCredentialsButtonEntered(MouseEvent event) {
        showHelpMessage(SAVE_CREDENTIALS_HELP_MESSAGE);
    }

    @FXML
    void saveCredentialsButtonExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareUserInfo();
    }

    private void prepareUserInfo() {
        SessionData sessionData = SessionData.INSTANCE;

        PlatformUtils.run(() -> {
            userBarLogin.setText(sessionData.getAuthorizedUsername());
            userBarOnlineIdLabel.setText(sessionData.getAuthorizedOnlineId());
            userBarPasswordsLabel.setText(sessionData.getAuthorizedPassNumber());
        });

    }

    private void showHelpMessage(String aMessage) {
        PlatformUtils.run(() -> {
            mainPaneHelpLabel.setText(aMessage);
        });
    }

    private void switchToAuthScene() {
        stageManager.switchScene(FxmlView.AUTH);
    }

    private void switchToMainScene() {
        stageManager.switchScene(FxmlView.MAIN);
    }
}
