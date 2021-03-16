package com.passnail.gui.control;

import com.passnail.connect.service.SynchronizationServiceIf;
import com.passnail.generator.GeneratorManagerServiceIf;
import com.passnail.generator.service.gen.PasswordGeneratorManagerIf;
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
import javafx.scene.input.MouseEvent;
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

/**
 * Created by: Pszemko at środa, 03.03.2021 20:14
 * Project: passnail-client
 */
@Component
@Lazy(value = true)
public class MainController implements Initializable {


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
    private Label lastSynchDateLabel;

    @FXML
    private Label lastSynchDate;

    @FXML
    private Button synchronizeOnDemandButton;

    @FXML
    void generatorSettingsButtonOnMouseClicked(MouseEvent event) {
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

    @FXML
    void onMouseMoved(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareUserInfo();
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

    private void switchToGeneratorSettingsScene() {
        stageManager.switchScene(GENERATORSETTINGS);
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
