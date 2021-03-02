package com.passnail.core.main;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import static com.passnail.core.main.GuiApplication.StageReadyEvent;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 19:13
 * Project: passnail-client
 */
@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        Stage stage = stageReadyEvent.getStage();
    }
}
