package com.passnail.server.core.tools.factory.impl.propertyhandler.gen;

import com.passnail.server.core.tools.prop.generator.DefaultGeneratorPropertyHandler;
import com.passnail.server.core.service.prop.PropertyHandlerIf;


/**
 * A factory for the {@link DefaultGeneratorPropertyHandler} objects creation.
 */
public class GeneratorPropertyHandlerFactory {

    /**
     * Returns a {@link DefaultGeneratorPropertyHandler} object being used in the {@link com.passnail.client.core.tools.pass.DefaultPasswordGenerator} for getting properties.
     *
     * @return {@link PropertyHandlerIf}
     */
    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new DefaultGeneratorPropertyHandler();
    }
}
