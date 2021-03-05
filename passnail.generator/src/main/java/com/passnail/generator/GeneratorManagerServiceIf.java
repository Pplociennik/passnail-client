package com.passnail.generator;

import com.passnail.generator.service.gen.DefaultPasswordGeneratorManager;

import java.io.IOException;

/**
 * Created by: Pszemko at piątek, 05.03.2021 20:18
 * Project: passnail-client
 */
public interface GeneratorManagerServiceIf {

    DefaultPasswordGeneratorManager createDefaultPasswordGeneratorManagerWithDefaultPropertiesLoaded() throws IOException;
}
