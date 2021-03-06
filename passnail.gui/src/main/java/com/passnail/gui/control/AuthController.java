package com.passnail.gui.control;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.tools.StageManager;
import com.passnail.security.service.AuthenticationServiceIf;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.passnail.gui.control.tools.PlatformUtils.run;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 22:05
 * Project: passnail-client
 */
@Component
@Lazy(value = true)
public class AuthController implements Initializable {

    @Autowired
    private AuthenticationServiceIf authenticationService;

    @Autowired
    @Lazy(value = true)
    private StageManager stageManager;


    @FXML
    private TextField loginLoginField;

    @FXML
    private TextField loginPasswordField;

    @FXML
    private Label loginErrorLabel;


    @FXML
    private TextField registrationLoginField;

    @FXML
    private TextField registrationEmailField;

    @FXML
    private TextField registrationPasswordField;

    @FXML
    private TextField registrationRptPasswordField;

    @FXML
    private Label registrationErrorLabel;


    @FXML
    void loginUser(MouseEvent event) throws IOException {

        boolean authorized = true;

        try {
            authenticationService.authenticateUser(createLoginDto());
        } catch (AuthenticationException e) {
            authorized = false;
            run(() -> {
                loginErrorLabel.setText(e.getMessage());
            });
        }
        if (authorized) {
            switchScene();
        }
    }

    @FXML
    void registerUser(MouseEvent event) throws IOException {

        boolean authorized = true;

        try {
            authenticationService.registerNewUserProfile(createRegistrationDto());
        } catch (AuthenticationException e) {
            authorized = false;
            run(() -> {
                registrationErrorLabel.setText(e.getMessage());
            });
        } finally {
            run(() -> {
                registrationPasswordField.setText("");
                registrationRptPasswordField.setText("");
            });
        }

        if (authorized) {
            switchScene();
        }
    }

    private RegistrationDto createRegistrationDto() {
        return RegistrationDto.builder()
                .email(registrationEmailField.getText())
                .login(registrationLoginField.getText())
                .password(registrationPasswordField.getText())
                .passwordRepeat(registrationRptPasswordField.getText())
                .build();
    }

    private void switchScene() {
        stageManager.switchScene(FxmlView.MAIN);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private LoginDto createLoginDto() {
        return LoginDto.builder()
                .loginOrEmail(loginLoginField.getText())
                .password(loginPasswordField.getText())
                /*.onlineID(loginOnlineIdField.getText())*/
                .build();
    }

}
