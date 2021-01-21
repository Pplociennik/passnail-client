package com.passnail.server.core.tools.pass;

import com.passnail.server.core.app.config.ConfAttributes;
import com.passnail.server.core.service.gen.PasswordGeneratorManagerIf;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by: Pszemko at czwartek, 21.01.2021 02:45
 * Project: passnail-client
 */
class PasswordGeneratorManagerTest {

    @BeforeAll
    public static void prepareConfAttributes() {
        ConfAttributes attributes = ConfAttributes.INSTANCE;
        attributes.setInstallationPath(System.getProperty("user.dir"));
    }

    @Test
    public void testCreatingDefaultPasswordGeneratorAndLoadingProperties() throws IOException {
        PasswordGeneratorManagerIf manager = new PasswordGeneratorManager();

        PasswordGeneratorIf passwordGenerator = manager.createDefaultPasswordGenerator();

        assertNotNull(passwordGenerator.generateNewPassword());
    }
}