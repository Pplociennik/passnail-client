package com.passnail.gui.control;

import com.passnail.connect.service.SynchronizationServiceIf;
import com.passnail.generator.GeneratorManagerServiceIf;
import com.passnail.generator.service.gen.PasswordGeneratorManagerIf;
import com.passnail.generator.service.prop.PropertyHandlerIf;
import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.tools.PlatformUtils;
import com.passnail.gui.control.tools.StageManager;
import com.passnail.gui.control.tools.SystemClipboardManager;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.session.SavedCredentialsSessionDataService;
import com.passnail.security.session.SessionData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import static com.passnail.gui.GuiConstants.*;
import static com.passnail.gui.config.FxmlView.*;
import static com.passnail.gui.control.tools.PlatformUtils.run;

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
    private SynchronizationServiceIf synchronizationService;

    @Autowired
    private SavedCredentialsSessionDataService sessionDataService;

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
    private TextField lengthField;

    @FXML
    private TextField uppserCaseField;

    @FXML
    private TextField lowerCaseField;

    @FXML
    private TextField specialCharactersField;

    @FXML
    private TextField digitsField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label lastSynchDateLabel;

    @FXML
    private Label lastSynchDate;

    @FXML
    private Button synchronizeOnDemandButton;


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
        switchToSettingsScene();
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
        try {
            prepareUserInfo();
            prepareProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareProperties() throws IOException {

        run(() -> {

            PasswordGeneratorManagerIf manager = null;
            try {
                manager = generatorManagerService.createDefaultPasswordGeneratorManagerWithDefaultPropertiesLoaded();
                PropertyHandlerIf handler = manager.getHandler();

                lengthField.setText(String.valueOf(handler.getPasswordLength()));
                uppserCaseField.setText(String.valueOf(handler.getUpperCaseNumber()));
                lowerCaseField.setText(String.valueOf(handler.getLowerCaseNumber()));
                specialCharactersField.setText(String.valueOf(handler.getSpecialCharactersNumber()));
                digitsField.setText(String.valueOf(handler.getDigitsNumber()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void prepareUserInfo() {
        SessionData sessionData = SessionData.INSTANCE;

        PlatformUtils.run(() -> {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            userBarLogin.setText(sessionData.getAuthorizedUsername());
            userBarOnlineIdLabel.setText(sessionData.getAuthorizedOnlineId());
            userBarPasswordsLabel.setText(sessionData.getAuthorizedPassNumber());
            lastSynchDate.setText(
                    sessionData.getAuthorizedUserLastSynchDate() == null ?
                            null :
                            df.format(sessionData.getAuthorizedUserLastSynchDate()));

            if (sessionData.getAuthorizedOnlineId() != null) {
                synchronizeOnDemandButton.setVisible(true);
                lastSynchDateLabel.setVisible(true);
            }
        });

    }

    private void showHelpMessage(String aMessage) {
        run(() -> {
            mainPaneHelpLabel.setText(aMessage);
        });
    }

    private void switchToAuthScene() {
        stageManager.switchScene(AUTH);
    }

    private void switchToLibraryScene() {
        stageManager.switchScene(LIBRARY);
    }


    public void resetButtonOnMouseClicked(MouseEvent event) throws IOException {
        resetToDefaults();
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

        boolean success = true;

        try {
            saveProperties();
        } catch (Exception e) {
            success = false;
            showErrorMessage(e.getMessage());
            e.printStackTrace();
        }

        if (success) {
            showSuccessMessage();
        }
    }

    private void showSuccessMessage() {

        run(() -> {
            errorLabel.setTextFill(Color.web("#34a300"));
            errorLabel.setText("Properties saved successfully!");
        });
    }

    private void showErrorMessage(String aMessage) {
        run(() -> {
            errorLabel.setTextFill(Color.web("#a30000"));
            errorLabel.setText(aMessage);
        });
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


    private void saveProperties() throws IOException {
        PasswordGeneratorManagerIf manager = generatorManagerService.createDefaultPasswordGeneratorManagerWithDefaultPropertiesLoaded();
        PropertyHandlerIf handler = manager.getHandler();

        var length = Integer.valueOf(lengthField.getText());
        var upperCase = Integer.valueOf(uppserCaseField.getText());
        var lowerCase = Integer.valueOf(lowerCaseField.getText());
        var digits = Integer.valueOf(digitsField.getText());
        var specialChars = Integer.valueOf(specialCharactersField.getText());

        handler.setAll(length,
                lowerCase,
                upperCase,
                digits,
                specialChars);

        handler.saveProperties();
        handler.loadProperties();

        prepareProperties();
    }

    private void resetToDefaults() throws IOException {
        PasswordGeneratorManagerIf manager = generatorManagerService.createDefaultPasswordGeneratorManagerWithDefaultPropertiesLoaded();
        PropertyHandlerIf handler = manager.getHandler();

        handler.resetToDefaults();
        handler.loadProperties();

        prepareProperties();
    }

    private void switchToSettingsScene() {
        stageManager.switchScene(FxmlView.SETTINGS);
    }

    public void synchronizeOnDemandButtonOnMouseClicked(MouseEvent event) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        PlatformUtils.run(() -> {
            SessionData sessionData = SessionData.INSTANCE;
            synchronizationService.synchronize(sessionData.getAuthorizedUsername());

            sessionDataService.refreshAuthorizedUserSavedCredentialsData();
            lastSynchDate.setText(df.format(sessionData.getAuthorizedUserLastSynchDate()));
        });
    }

    public void synchronizeOnDemandButtonOnMouseEntered(MouseEvent event) {
    }

    public void synchronizeOnDemandButtonOnMouseExited(MouseEvent event) {
    }
}
