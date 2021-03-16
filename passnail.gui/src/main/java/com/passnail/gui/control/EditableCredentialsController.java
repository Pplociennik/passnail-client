package com.passnail.gui.control;

import com.passnail.connect.service.SynchronizationServiceIf;
import com.passnail.data.service.CredentialsServiceIf;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import com.passnail.generator.GeneratorManagerServiceIf;
import com.passnail.generator.service.gen.PasswordGeneratorManagerIf;
import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.data.EditableCredentialsData;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static com.passnail.gui.GuiConstants.*;
import static com.passnail.gui.config.FxmlView.*;
import static com.passnail.gui.control.tools.PlatformUtils.run;

/**
 * Created by: Pszemko at niedziela, 07.03.2021 03:25
 * Project: passnail-client
 */
@Component
@Lazy(value = true)
public class EditableCredentialsController implements Initializable {

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @Autowired
    private GeneratorManagerServiceIf generatorManagerService;

    @Autowired
    private CredentialsServiceIf credentialsService;

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
    private TextArea descriptionArea;

    @FXML
    private TextField shortNameField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField urlField;

    @FXML
    private Label lastSynchDateLabel;

    @FXML
    private Label lastSynchDate;

    @FXML
    private Button synchronizeOnDemandButton;


    @FXML
    void generatorSettingsButtonOnMouseClicked(MouseEvent event) throws IOException {
        switchToGeneratorSettingsScene();
    }

    @FXML
    void generatorSettingsOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATOR_SETTINGS_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void generatorSettingsOnMouseExited(MouseEvent event) {
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


    public void backButtonOnMouseClicked(MouseEvent event) {
        switchToMainScene();
    }

    public void backButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(BACK_BUTTON_HELP_MESSAGE);
    }

    public void backButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void backToLibButtonOnMouseClicked(MouseEvent event) {
        switchToLibraryScene();
    }

    @FXML
    void backToLibButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(BACK_TO_LIBRARY_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void backToLibButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void descriptionAreaOnMouseEntered(MouseEvent event) {
        showHelpMessage(EDIT_CREDS_DESCRIPTION_AREA_HELP_MESSAGE);
    }

    @FXML
    void descriptionAreaOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void loginFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(EDIT_CREDS_LOGIN_FIELD_HELP_MESSAGE);
    }

    @FXML
    void loginFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void passwordFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(EDIT_CREDS_PASSWORD_FIELD_HELP_MESSAGE);
    }

    @FXML
    void passwordFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void removeButtonOnMouseClicked(MouseEvent event) {
        removeCredentialsFromDatabase();
        switchToLibraryScene();
    }

    @FXML
    void removeButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(REMOVE_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void removeButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void shortNameFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(EDIT_CREDS_SHORT_NAME_FIELD_HELP_MESSAGE);
    }

    @FXML
    void shortNameFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void urlFieldOnMouseEntered(MouseEvent event) {
        showHelpMessage(EDIT_CREDS_URL_FIELD_MESSAGE_TEXT);
    }

    @FXML
    void urlFieldOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareUserInfo();
        prepareCredentialsInfo();
    }

    private void prepareCredentialsInfo() {

        run(() -> {
            EditableCredentialsData dataEditable = EditableCredentialsData.EDITABLE;

            descriptionArea.setText(dataEditable.getDescription());
            shortNameField.setText(dataEditable.getShortName());
            loginField.setText(dataEditable.getLogin());
            passwordField.setText(dataEditable.getPassword());
            urlField.setText(dataEditable.getUrl());
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

    private void switchToMainScene() {
        stageManager.switchScene(MAIN);
    }

    private void removeCredentialsFromDatabase() {
        SessionData sessionData = SessionData.INSTANCE;
        CredentialsDto credentials = CredentialsDto.builder()
                .credentialsShortName(shortNameField.getText())
                .build();

        credentialsService.removeCredentialsFromTheDatabase(credentials, sessionData.getAuthorizedUsername(), sessionData.getPassword());

        sessionDataService.refreshAuthorizedUserSavedCredentialsData();
        refreshGui();
    }

    private void refreshGui() {
        run(() -> {
            SessionData sessionData = SessionData.INSTANCE;
            userBarPasswordsLabel.setText(sessionData.getAuthorizedPassNumber());
        });
    }

    private void switchToGeneratorSettingsScene() {
        stageManager.switchScene(GENERATORSETTINGS);
    }

    public void saveButtonOnMouseClicked(MouseEvent event) {
        SessionData sessionData = SessionData.INSTANCE;
        EditableCredentialsData dataOriginal = EditableCredentialsData.ORIGINAL;

        CredentialsDto original = CredentialsDto.builder()
                .url(dataOriginal.getUrl())
                .credentialsShortName(dataOriginal.getShortName())
                .description(dataOriginal.getDescription())
                .login(dataOriginal.getLogin())
                .password(dataOriginal.getPassword())
                .build();

        CredentialsDto toUpdate = CredentialsDto.builder()
                .url(urlField.getText())
                .login(loginField.getText())
                .password(passwordField.getText())
                .credentialsShortName(shortNameField.getText())
                .description(descriptionArea.getText())
                .lastModificationDate(new Date())
                .build();

        credentialsService.updateCredentials(original, toUpdate, sessionData.getAuthorizedUsername(), sessionData.getPassword());

        switchToLibraryScene();
    }

    public void saveButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(EDITABLE_CREDENTIALS_SAVE_BUTTON_HELP_MESSAGE);
    }

    public void saveButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    public void generatePasswordButtonOnMouseClicked(MouseEvent event) throws IOException {
        PasswordGeneratorManagerIf manager = generatorManagerService.createDefaultPasswordGeneratorManagerWithDefaultPropertiesLoaded();
        password = manager.generateNewPassword();

        PlatformUtils.run(() -> {
            passwordField.setText(password);
        });
    }

    public void generatePasswordButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(GENERATE_NEW_PASSWORD_HELP_MESSAGE);
    }

    public void generatePasswordButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
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
