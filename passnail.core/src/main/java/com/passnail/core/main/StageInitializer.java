package com.passnail.core.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.passnail.core.main.GuiApplication.StageReadyEvent;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 19:13
 * Project: passnail-client
 */
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Value("classpath:/splash.fxml")
    private Resource guiResource;
    private String applicationTitle;
    private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.gui.stage.title}") String applicationTitle, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(guiResource.getURL());
            fxmlLoader.setControllerFactory(aClass -> applicationContext.getBean(aClass));


            Parent parent = fxmlLoader.load();
            Stage stage = stageReadyEvent.getStage();
            stage.setTitle(applicationTitle);
            stage.setScene(new Scene(parent, 800, 600));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
