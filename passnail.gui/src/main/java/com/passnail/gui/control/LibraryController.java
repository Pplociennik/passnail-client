package com.passnail.gui.control;

import com.passnail.connect.service.SynchronizationServiceIf;
import com.passnail.data.service.CredentialsServiceIf;
import com.passnail.data.service.UserServiceIf;
import com.passnail.data.transfer.model.dto.CredentialsDto;
import com.passnail.generator.GeneratorManagerServiceIf;
import com.passnail.generator.service.gen.PasswordGeneratorManagerIf;
import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.data.EditableCredentialsData;
import com.passnail.gui.control.data.OpenedCredentialsData;
import com.passnail.gui.control.tools.PlatformUtils;
import com.passnail.gui.control.tools.StageManager;
import com.passnail.gui.control.tools.SystemClipboardManager;
import com.passnail.security.service.AuthenticationServiceIf;
import com.passnail.security.session.SavedCredentialsSessionDataService;
import com.passnail.security.session.SessionData;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
import java.util.stream.Collectors;

import static com.passnail.data.status.CredentialsStatus.MAINTAINED;
import static com.passnail.gui.GuiConstants.*;
import static com.passnail.gui.config.FxmlView.*;
import static com.passnail.gui.control.tools.PlatformUtils.run;

/**
 * Created by: Pszemko at sobota, 06.03.2021 19:16
 * Project: passnail-client
 */
@Component
@Lazy(value = true)
public class LibraryController implements Initializable {

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @Autowired
    private GeneratorManagerServiceIf generatorManagerService;

    @Autowired
    private CredentialsServiceIf credentialsService;

    @Autowired
    private UserServiceIf userService;

    @Autowired
    private SavedCredentialsSessionDataService sessionDataService;

    @Autowired
    private SynchronizationServiceIf synchronizationService;

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
    private ListView<?> credentialsList;

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
        stageManager.switchScene(FxmlView.NEWCREDENTIALS);
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
    void credentialsListMouseEntered(MouseEvent event) {
        showHelpMessage(CREDENTIALS_LIST_HELP_MESSAGE);
    }

    @FXML
    void credentialsListMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    @FXML
    void refreshButtonMouseClicked(MouseEvent event) {
        refreshCredentialsList();
    }

    @FXML
    void refreshButtonMouseEntered(MouseEvent event) {
        showHelpMessage(REFRESH_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void refreshButtonMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void addButtonOnMouseClicked(MouseEvent event) {
        switchToNewCredentialsScene();
    }

    @FXML
    void addButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(ADD_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void addButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void editButtonOnMouseClicked(MouseEvent event) {
        EditableCredentialsData dataOriginal = EditableCredentialsData.ORIGINAL;
        CredentialsDto credentials = (CredentialsDto) credentialsList.getSelectionModel().getSelectedItem();

        dataOriginal.setDescription(credentials.getDescription());
        dataOriginal.setLogin(credentials.getLogin());
        dataOriginal.setPassword(credentials.getPassword());
        dataOriginal.setShortName(credentials.getCredentialsShortName());
        dataOriginal.setUrl(credentials.getUrl());

        EditableCredentialsData dataEditable = EditableCredentialsData.EDITABLE;

        dataEditable.setDescription(credentials.getDescription());
        dataEditable.setLogin(credentials.getLogin());
        dataEditable.setPassword(credentials.getPassword());
        dataEditable.setShortName(credentials.getCredentialsShortName());
        dataEditable.setUrl(credentials.getUrl());

        switchToEditCredentialsScene();
    }

    @FXML
    void editButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(EDIT_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void editButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @FXML
    void removeButtonOnMouseClicked(MouseEvent event) {
        removeCredentialsFromDatabase();
    }

    @FXML
    void removeButtonOnMouseEntered(MouseEvent event) {
        showHelpMessage(REMOVE_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void removeButtonOnMouseExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareUserInfo();
        refreshCredentialsList();
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
        stageManager.switchScene(FxmlView.AUTH);
    }

    private void refreshCredentialsList() {

        run(() -> {

            sessionDataService.refreshAuthorizedUserSavedCredentialsData();
            refreshGui();

            SessionData sessionData = SessionData.INSTANCE;
            ObservableList list = credentialsList.getItems();

            if (!list.isEmpty()) {
                list.clear();
            }

            list.addAll(sessionData.getAuthorizedUserSavedCredentials().stream()
                    .filter(item -> item.getStatus().equals(MAINTAINED))
                    .collect(Collectors.toList()));
        });
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
    void openButtonOnClicked(MouseEvent event) {

        OpenedCredentialsData data = OpenedCredentialsData.INSTANCE;
        CredentialsDto credentials = (CredentialsDto) credentialsList.getSelectionModel().getSelectedItem();

        data.setDescription(credentials.getDescription());
        data.setLogin(credentials.getLogin());
        data.setPassword(credentials.getPassword());
        data.setShortName(credentials.getCredentialsShortName());
        data.setUrl(credentials.getUrl());

        switchToOpenedCredentialsScene();
    }

    @FXML
    void openButtonOnEntered(MouseEvent event) {
        showHelpMessage(OPEN_BUTTON_HELP_MESSAGE);
    }

    @FXML
    void openButtonOnExited(MouseEvent event) {
        showHelpMessage(EMPTY_HELP_MESSAGE);
    }

    private void switchToMainScene() {
        stageManager.switchScene(MAIN);
    }

    private void removeCredentialsFromDatabase() {
        SessionData sessionData = SessionData.INSTANCE;
        CredentialsDto credentials = (CredentialsDto) credentialsList.getSelectionModel().getSelectedItem();

        credentialsService.removeCredentialsFromTheDatabase(credentials, sessionData.getAuthorizedUsername(), sessionData.getPassword());

        sessionDataService.refreshAuthorizedUserSavedCredentialsData();
        refreshGui();

        refreshCredentialsList();
    }

    private void refreshGui() {
        run(() -> {
            SessionData sessionData = SessionData.INSTANCE;
            userBarPasswordsLabel.setText(sessionData.getAuthorizedPassNumber());
        });
    }

    private void switchToOpenedCredentialsScene() {
        stageManager.switchScene(OPENEDCREDENTIALS);
    }

    private void switchToGeneratorSettingsScene() {
        stageManager.switchScene(GENERATORSETTINGS);
    }

    private void switchToEditCredentialsScene() {
        stageManager.switchScene(EDITABLECREDENTIALS);
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
