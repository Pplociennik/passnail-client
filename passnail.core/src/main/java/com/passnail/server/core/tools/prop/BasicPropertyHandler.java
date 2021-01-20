package com.passnail.server.core.tools.prop;

import com.passnail.server.core.app.config.ConfAttributes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The basic property handler being a base abstract class for the specific handlers being used for another purposes.
 */
public abstract class BasicPropertyHandler {

    /**
     * An instance of the Singleton {@link ConfAttributes} typed object containing the launch application attributes.
     */
    protected ConfAttributes confAttributes = ConfAttributes.INSTANCE;

    /**
     * A field holding the loaded properties.
     */
    protected Properties properties;

    protected BasicPropertyHandler() {
    }

    /**
     * Loads the properties file from the specified path.
     *
     * @param aPath A path to the properties file.
     * @throws IOException - when the file cannot be found.
     */
    protected void loadProperties(String aPath) throws IOException {
        InputStream in = null;

        try {
            in = new FileInputStream(aPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties.load(in);
    }

    protected String getPropertyValue(String aProperty) {
        return properties.getProperty(aProperty);
    }


}
