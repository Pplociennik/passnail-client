package com.passnail.server.core.app.main;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.throwable.exception.WrongStartExecutingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * A main class of the client's application.
 */
@SpringBootApplication
@EnableJpaRepositories
public class ClientStarter {

    public static void main(String[] args) {

        if (args.length == 0 || args[0].equals("PROJECT_ENV_PATH")) {
            throw new WrongStartExecutingException();
        }

        ConfAttributes confAttributes = ConfAttributes.INSTANCE;
        confAttributes.setINSTALLATION_PATH(args[0]);

        SpringApplication.run(ClientStarter.class, args);

    }

}
