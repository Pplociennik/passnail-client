package com.passnail.gui;

import com.passnail.generator.GeneratorModuleConfig;
import com.passnail.gui.config.SpringFXMLLoader;
import com.passnail.gui.control.tools.StageManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import java.util.ResourceBundle;

/**
 * Created by: Pszemko at wtorek, 02.03.2021 17:58
 * Project: passnail-client
 */
@Configuration
@ComponentScan
@Import({GeneratorModuleConfig.class})
public class GuiModuleConfig {

    @Autowired
    @Lazy(value = true)
    SpringFXMLLoader springFXMLLoader;

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("Bundle");
    }

    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage) {
        return new StageManager(springFXMLLoader, stage);
    }

}
