package com.passnail.server.core.tools.prop.generator;

import com.passnail.server.core.tools.prop.BasicPropertyHandler;
import com.passnail.server.core.tools.prop.PropertyConstants;
import com.passnail.server.core.tools.service.PropertyHandlerIf;

import java.io.IOException;
import java.util.Properties;

public class GeneratorPropertyHandler extends BasicPropertyHandler implements PropertyHandlerIf {


    @Override
    public void loadProperties(String aPath) throws IOException {
        super.loadProperties(confAttributes.getINSTALLATION_PATH() + PropertyConstants.GENERATOR_PROPERTIES_SUFFIX);
    }

    @Override
    public Properties getProperties() {
        return properties != null ? properties : new Properties();
    }


}
