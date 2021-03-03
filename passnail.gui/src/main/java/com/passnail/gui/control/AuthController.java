package com.passnail.gui.control;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.security.service.AuthenticationServiceIf;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
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
    void loginUser(MouseEvent event) throws IOException {

        try {
            authenticationService.authenticateUser(createLoginDto());
        } catch (AuthenticationException e) {
            run(() -> {
                loginErrorLabel.setText(e.getMessage());
            });
        }

        switchScene();
    }

    @FXML
    void registerUser(MouseEvent event) throws IOException {

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

        switchScene();
    }

    private RegistrationDto createRegistrationDto() {
        return RegistrationDto.builder()
                .email(registrationEmailField.getText())
                .login(registrationLoginField.getText())
                .password(registrationPasswordField.getText())
                .passwordRepeat(registrationRptPasswordField.getText())
                .build();
    }

    private void switchScene() throws IOException {

        run(() -> {
            var mainResource = this.getClass().getResource("/main.fxml");
            Parent parent = null;
            try {
                parent = new FXMLLoader(mainResource).load();
                Scene scene = root.getScene();
                scene.setRoot(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


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
