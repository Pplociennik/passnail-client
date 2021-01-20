package com.passnail.server.core.tools.prop.generator;

import com.passnail.server.core.tools.prop.BasicPropertyHandler;
import com.passnail.server.core.tools.prop.PropertyConstants;
import com.passnail.server.core.tools.service.PropertyHandlerIf;

import java.io.IOException;
import java.util.Properties;

/**
 * A property handler for the {@link com.passnail.server.core.tools.service.PasswordGeneratorIf}. Contains methods for loading properties files and getting values of them.
 */
public class GeneratorPropertyHandler extends BasicPropertyHandler implements PropertyHandlerIf {

    /**
     * Loads properties for the {@link com.passnail.server.core.tools.service.PasswordGeneratorIf}.
     *
     * @throws IOException
     */
    @Override
    public void loadProperties() throws IOException {
        super.loadProperties(confAttributes.getINSTALLATION_PATH() + PropertyConstants.GENERATOR_PROPERTIES_SUFFIX);
    }

    /**
     * Returns the {@link Properties} typed object being a set of properties for the {@link com.passnail.server.core.tools.service.PasswordGeneratorIf}.
     *
     * @return {@link Properties}
     */
    @Override
    public Properties getProperties() {
        return properties != null ? properties : new Properties();
    }


}
