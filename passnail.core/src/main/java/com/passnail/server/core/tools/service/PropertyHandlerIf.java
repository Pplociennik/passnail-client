package com.passnail.server.core.tools.service;

import java.io.IOException;
import java.util.Properties;

/**
 * An interface of the property handlers.
 */
public interface PropertyHandlerIf {

    /**
     * Loads the properties file for the specific implementation.
     *
     * @throws IOException when the file cannot be found or does not exist.
     */
    void loadProperties() throws IOException;

    /**
     * Returns the properties loaded from the file.
     *
     * @return {@link Properties}
     */
    Properties getProperties();
}
