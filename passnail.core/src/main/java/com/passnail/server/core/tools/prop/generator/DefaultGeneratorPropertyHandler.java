package com.passnail.server.core.tools.prop.generator;

import com.passnail.server.core.tools.prop.BasicPropertyHandler;
import com.passnail.server.core.tools.pass.PasswordGeneratorIf;
import com.passnail.server.core.service.prop.PropertyHandlerIf;

import java.io.IOException;
import java.util.Properties;

import static com.passnail.server.core.tools.prop.PropertyConstants.DEFAULT_GENERATOR_PROPERTIES_PATH;

/**
 * A property handler for the {@link PasswordGeneratorIf}. Contains methods for loading properties files and getting values of them.
 */
public class DefaultGeneratorPropertyHandler extends BasicPropertyHandler implements PropertyHandlerIf {

    /**
     * Loads properties for the {@link PasswordGeneratorIf}.
     *
     * @throws IOException
     */
    @Override
    public void loadProperties() throws IOException {
        super.loadProperties(DEFAULT_GENERATOR_PROPERTIES_PATH);
    }

    @Override
    public void saveProperties() throws IOException {
        super.saveProperties(DEFAULT_GENERATOR_PROPERTIES_PATH);
    }

    /**
     * Returns the {@link Properties} typed object being a set of properties for the {@link PasswordGeneratorIf}.
     *
     * @return {@link Properties}
     */
    @Override
    public Properties getProperties() {
        return properties != null ? properties : new Properties();
    }


}
