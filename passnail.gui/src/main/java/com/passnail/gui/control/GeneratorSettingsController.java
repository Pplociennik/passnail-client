package com.passnail.gui.control;

import com.passnail.generator.GeneratorManagerServiceIf;
import com.passnail.generator.service.gen.PasswordGeneratorManagerIf;
import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.tools.PlatformUtils;
import com.passnail.gui.control.tools.StageManager;
import com.passnail.gui.control.tools.SystemClipboardManager;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.session.SessionData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.passnail.gui.GuiConstants.*;
import static com.passnail.gui.config.FxmlView.*;

/**
 * Created by: Pszemko at niedziela, 07.03.2021 01:23
 * Project: passnail-client
 */
@Component
@Lazy(value = true)
public class GeneratorSettingsController implements Initializable {

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @Autowired
    private GeneratorManagerServiceIf generatorManagerService;

    @Autowired
    @Lazy(value = true)
    private StageManager stageManager;

    String password;


    @FXML
    private Label mainPaneHelpLabel;

    @FXML
    private Label userBarLogin;

    @FXML
    private Label userBarOnlineIdLabel;

    @FXML
    private Label userBarPasswordsLabel;


    @FXML
    void logoutButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(LOGOUT_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void logoutButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void logoutUser(MouseEvent event) {
        authenticationService.logout(false);
        switchToAuthScene();
    }

    @FXML
    void newCredentialsButtonOnMouseClicked(MouseEvent event) {
        switchToNewCredentialsScene();
    }

    private void switchToNewCredentialsScene() {
        stageManager.switchScene(NEWCREDENTIALS);
    }

    @FXML
    void newCredentialsButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(NEW_CREDENTIALS_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void newCredentialsButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void quickPasswordOnMouseClicked(MouseEvent event) throws IOException, AWTException {
        PasswordGeneratorManagerIf manager = generatorManagerService.createDefaultPasswordGeneratorManagerWithDefaultPropertiesLoaded();
        password = manager.generateNewPassword();

        new SystemClipboardManager().copyTextToTheClipboard(password, PASSWORD_COPIED_NOTIFICATION_MESSAGE);
    }

    @FXML
    void quickPasswordOnMouseEntered(MouseEvent event) {
        showHelpMessage(QUICK_PASSWORD_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void quickPasswordOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
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
    void showLibraryButtonOnMouseClicked(MouseEvent event) {
        switchToLibraryScene();
    }

    @FXML
    void showLibraryButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(SHOW_LIBRARY_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void showLibraryButtonOnMouseExited(MouseEvent event) {
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
        stageManager.switchScene(AUTH);
    }

    private void switchToLibraryScene() {
        stageManager.switchScene(LIBRARY);
    }


    public void resetButtonOnMouseClicked(MouseEvent event) {
    }

    public void resetButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_RESET_BUTTON_HELP_MESSAGE);
    }

    public void resetButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    public void lengthFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_LENGTH_FIELD_HELP_MESSAGE);
    }

    public void lengthFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    public void upperCaseFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    public void upperCaseFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_UPPER_CASE_FIELD_HELP_MESSAGE);
    }

    public void lowerCaseFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_LOWER_CASE_FIELD_HELP_MESSAGE);
    }

    public void lowerCaseFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    public void specialCharactersFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_SPECIAL_CHARACTERS_FIELD_HELP_MESSAGE);
    }

    public void specialCharactersFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    public void digitsFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_DIGITS_FIELD_HELP_MESSAGE);
    }

    public void digitsFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    public void saveButtonOnMouseClicked(MouseEvent event) {
    }

    public void saveButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_SAVE_BUTTON_HELP_MESSAGE);
    }

    public void saveButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    public void backButtonOnMouseClicked(MouseEvent event) {
        switchToMainScene();
    }

    public void backButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(BACK_BUTTON_HELP_MESSAGE);
    }

    public void backButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    private void switchToMainScene() {
        stageManager.switchScene(FxmlView.MAIN);
    }
}
