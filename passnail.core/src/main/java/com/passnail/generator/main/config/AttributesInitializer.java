package com.passnail.generator.main.config;

import com.passnail.common.ConfAttributes;
import com.passnail.common.throwable.core.WrongStartExecutingException;
import lombok.extern.log4j.Log4j2;

/**
 * A class for initializing values of the application's starting attributes being stored in the {@link ConfAttributes}.
 * <p>
 * Created by: Pszemko at poniedziałek, 15.02.2021 20:45
 * Project: passnail-client
 */
@Log4j2
public class AttributesInitializer {

    /**
     * Sets the proper attributes' values.
     *
     * @param args
     */
    public void setAttributes(String[] args) {
        validateAttributes(args);

        ConfAttributes confAttributes = ConfAttributes.INSTANCE;
        confAttributes.setInstallationPath(args[0]);
        confAttributes.setDbPath(args[1]);
        confAttributes.setAuthDbLogin(args[2]);
        confAttributes.setAuthDbPassword(args[3]);
    }

    /**
     * Validates attributes' correctness.
     *
     * @param args A table of application's starting parameters.
     */
    private void validateAttributes(String[] args) {

        if (args.length == 0 || args[0].equals("PROJECT_ENV_PATH")) {
            log.error("Incorrect env variables' values!");
            throw new WrongStartExecutingException();
        }
        if (args.length == 0 || args[1].equals("PROJECT_DB_PATH")) {
            log.error("Incorrect env variables' values!");
            throw new WrongStartExecutingException();
        }
        if (args.length == 0 || args[2].equals("AUTH_DB_LOGIN")) {
            log.error("Incorrect env variables' values!");
            throw new WrongStartExecutingException();
        }
        if (args.length == 0 || args[3].equals("AUTH_DB_PASSWORD")) {
            log.error("Incorrect env variables' values!");
            throw new WrongStartExecutingException();
        }
    }
}
