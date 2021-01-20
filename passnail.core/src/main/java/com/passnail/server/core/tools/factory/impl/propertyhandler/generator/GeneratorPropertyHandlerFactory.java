package com.passnail.server.core.tools.factory.impl.propertyhandler.generator;

import com.passnail.server.core.tools.pass.DefaultPasswordGenerator;
import com.passnail.server.core.tools.prop.generator.GeneratorPropertyHandler;
import com.passnail.server.core.tools.service.PropertyHandlerIf;
import lombok.extern.log4j.Log4j2;


/**
 * A factory for the {@link GeneratorPropertyHandler} objects creation.
 */
public class GeneratorPropertyHandlerFactory {

    /**
     * Returns a {@link GeneratorPropertyHandler} object being used in the {@link DefaultPasswordGenerator} for getting properties.
     *
     * @return {@link PropertyHandlerIf}
     */
    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new GeneratorPropertyHandler();
    }
}
