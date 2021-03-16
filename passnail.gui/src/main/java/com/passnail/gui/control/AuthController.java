package com.passnail.gui.control;

import com.passnail.common.throwable.security.AuthenticationException;
import com.passnail.data.transfer.model.dto.LoginDto;
import com.passnail.data.transfer.model.dto.RegistrationDto;
import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.tools.PlatformUtils;
import com.passnail.gui.control.tools.SplashScreenInfo;
import com.passnail.gui.control.tools.StageManager;
import com.passnail.security.service.AuthenticationServiceIf;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
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
    private AnchorPane root;

    @FXML
    private TextField loginLoginField;

    @FXML
    private TextField loginPasswordField;

    @FXML
    private TextField loginOnlineIdField;

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

        PlatformUtils.run(() -> {
            if (!SplashScreenInfo.INSTANCE.isFinished()) {
                loadSplashScreen();
            }
        });
    }

    private LoginDto createLoginDto() {
        return LoginDto.builder()
                .loginOrEmail(loginLoginField.getText())
                .password(loginPasswordField.getText())
                .onlineID(loginOnlineIdField.getText())
                .build();
    }

    private void loadSplashScreen() {
        try {

            SplashScreenInfo.INSTANCE.setFinished(true);
            StackPane pane = FXMLLoader.load(getClass().getResource(("/splash.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(5), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(4), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                //Do the loading tasks
                int i = 0;
                while (authenticationService == null) {
                    i++;
                }
                //...
                //After the background tasks are done, load the fadeout
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                //                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/auth.fxml")));
//                    root.getChildren().setAll(parentContent);
                stageManager.switchScene(FxmlView.AUTH);
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
