package com.passnail.server.core.app.main;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.throwable.WrongStartExecutingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * A main class of the client's application.
 */
@Log4j2
@SpringBootApplication
@EnableJpaRepositories
public class ClientStarter {

    public static void main(String[] args) {

        if (args.length == 0 || args[0].equals("PROJECT_ENV_PATH")) {
            log.error("Incorrect env variables' values!");
            throw new WrongStartExecutingException();
        }

        ConfAttributes confAttributes = ConfAttributes.INSTANCE;
        confAttributes.setInstallationPath(args[0] + "/passnail.core");

        SpringApplication.run(ClientStarter.class, args);

    }

}
