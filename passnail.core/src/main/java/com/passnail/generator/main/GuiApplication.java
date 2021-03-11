package com.passnail.generator.main;

import com.passnail.gui.config.FxmlView;
import com.passnail.gui.control.tools.StageManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
        stageManager = applicationContext.getBean(StageManager.class, stage);
        displayInitialScene();
    }

    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.AUTH);
    }
}
