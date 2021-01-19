package com.passnail.server.core.tools.prop;

import com.passnail.server.core.app.config.ConfAttributes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BasicPropertyHandler {

    protected ConfAttributes confAttributes = ConfAttributes.INSTANCE;
    protected Properties properties;

    protected BasicPropertyHandler() {
    }

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
