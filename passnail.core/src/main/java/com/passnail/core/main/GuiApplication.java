package com.passnail.core.main;

import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.tools.StageManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 18:54
 * Project: passnail-client
 */
public class GuiApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    protected StageManager stageManager;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(ClientStarter.class).run();
    }

    @Override
    public void stop() {
        applicationContext.stop();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) {
//        String[] args = getParameters().getRaw().toArray(new String[0]);
//        applicationContext.publishEvent(new StageReadyEvent(stage));
        stageManager = applicationContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.AUTH);
    }

//    static class StageReadyEvent extends ApplicationEvent {
//        public StageReadyEvent(Stage stage) {
//            super(stage);
//        }
//
//        public Stage getStage() {
//            return ((Stage) getSource());
//        }
//    }
}
