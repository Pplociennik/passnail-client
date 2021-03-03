package com.passnail.gui.control;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.gui.GuiConstants;
import com.passnail.security.service.AuthenticationServiceIf;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static com.passnail.gui.control.tools.PlatformUtils.run;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 22:05
 * Project: passnail-client
 */
@Component
public class AuthController implements Initializable {

    @Autowired
    private AuthenticationServiceIf authenticationService;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

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
    void loginUser(MouseEvent event) {

        try {
            authenticationService.authenticateUser(createLoginDto());
        } catch (AuthenticationException e) {
            run(() -> {
                loginErrorLabel.setText(e.getMessage());
            });
        }
    }

    @FXML
    void registerUser(MouseEvent event) {

        try {
            authenticationService.registerNewUserProfile(createRegistrationDto());
        } catch (AuthenticationException e) {
            run(() -> {
                registrationErrorLabel.setText(e.getMessage());
            });
        } finally {
            run(() -> {
                registrationPasswordField.setText("");
                registrationRptPasswordField.setText("");
            });
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

    private GuiConstants constants = GuiConstants.INSTANCE;


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
