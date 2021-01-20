package com.passnail.server.core.tools.pass;

import com.passnail.server.core.tools.factory.PropertyHandlerFactoryIf;
import com.passnail.server.core.tools.factory.impl.PropertyHandlerFactory;
import com.passnail.server.core.tools.service.PasswordGeneratorIf;
import com.passnail.server.core.tools.service.PropertyHandlerIf;

import java.io.IOException;
import java.util.Properties;

/**
 * An implementation of the {@link PasswordGeneratorIf}. Containing methods for passwords generating and setting options for the process.
 */
public class PasswordGenerator implements PasswordGeneratorIf {

    /**
     * A factory interface.
     */
    PropertyHandlerFactoryIf factory;

    /**
     * The {@link Properties} typed object which stores the generator's properties loaded from file.
     */
    private Properties generatorProperties;


    public PasswordGenerator() throws IOException {
        factory = new PropertyHandlerFactory();
        loadProperties();
    }

    /**
     * Loads the properties from file.
     *
     * @throws IOException - when the file could not be found.
     */
    private void loadProperties() throws IOException {
        PropertyHandlerIf handler = factory.getGeneratorPropertyHandler();
        handler.loadProperties();

        generatorProperties = handler.getProperties();
    }
}
