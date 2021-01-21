package com.passnail.server.core.tools.prop;

import com.passnail.server.core.service.prop.PropertyHandlerIf;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.Properties;

/**
 * The basic property handler being a base abstract class for the specific handlers being used for another purposes.
 */
@Log4j2
public abstract class BasicPropertyHandler implements PropertyHandlerIf {

    /**
     * A field holding the loaded properties.
     */
    protected Properties properties;

    protected BasicPropertyHandler() {
        properties = new Properties();
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
            log.error("Could not find a file under the: " + aPath);
            e.printStackTrace();
        }
        properties.load(in);
        in.close();

        log.info("Properties loaded successfully.");
    }

    /**
     * Saves the properties file to the specified path.
     *
     * @throws IOException - when the file cannot be found.
     */
    protected void saveProperties(String aPath) throws IOException {

        OutputStream out = null;
        try {
            out = new FileOutputStream(aPath);
        } catch (FileNotFoundException e) {
            log.error("Could not find a file under the: " + aPath);
            e.printStackTrace();
        }
        properties.store(out, null);
        out.close();
    }

}
