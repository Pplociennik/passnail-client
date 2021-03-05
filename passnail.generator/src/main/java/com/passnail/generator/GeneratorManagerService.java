package com.passnail.generator;

import com.passnail.generator.service.gen.DefaultPasswordGeneratorManager;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by: Pszemko at piątek, 05.03.2021 20:18
 * Project: passnail-client
 */
@Service
public class GeneratorManagerService implements GeneratorManagerServiceIf {

    @Override
    public DefaultPasswordGeneratorManager createDefaultPasswordGeneratorManagerWithDefaultPropertiesLoaded() throws IOException {
        DefaultPasswordGeneratorManager manager = new DefaultPasswordGeneratorManager();
        manager.createNewDefaultPasswordGenerator();
        manager.loadDefaultProperties();

        return manager;
    }
}
