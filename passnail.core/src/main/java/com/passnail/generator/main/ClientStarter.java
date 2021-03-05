package com.passnail.generator.main;

import com.passnail.generator.main.config.AttributesInitializer;
import javafx.application.Application;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * A main class of the client's application.
 */
@Log4j2
@SpringBootApplication
@EnableJpaRepositories
public class ClientStarter {

    private static AttributesInitializer init;

    public static void main(String[] args) {
        init = new AttributesInitializer();
        init.setAttributes(args);

//        SpringApplication.run(ClientStarter.class, args);
        Application.launch(GuiApplication.class, args);

    }

}
