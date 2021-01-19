package com.passnail.server.core.app.main;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.throwable.exception.WrongStartExecutingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories
public class ServerStarter {

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new WrongStartExecutingException();
        }

        ConfAttributes confAttributes = ConfAttributes.INSTANCE;
        confAttributes.setINSTALLATION_PATH(args[0]);

        SpringApplication.run(ServerStarter.class, args);

    }

}
