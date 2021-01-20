package com.passnail.server.core.tools.factory.impl.generator;

import com.passnail.server.core.tools.prop.generator.GeneratorPropertyHandler;
import com.passnail.server.core.tools.service.PropertyHandlerIf;


/**
 * A factory for the {@link GeneratorPropertyHandler} objects creation.
 */
public class GeneratorPropertyHandlerFactory {

    /**
     * Returns a {@link GeneratorPropertyHandler} object being used in the {@link com.passnail.server.core.tools.pass.PasswordGenerator} for getting properties.
     *
     * @return {@link PropertyHandlerIf}
     */
    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new GeneratorPropertyHandler();
    }
}
