package com.passnail.server.core.tools.factory.impl;

import com.passnail.server.core.tools.factory.PropertyHandlerFactoryIf;
import com.passnail.server.core.tools.factory.impl.generator.GeneratorPropertyHandlerFactory;
import com.passnail.server.core.tools.service.PropertyHandlerIf;


/**
 * A factory for the {@link PropertyHandlerIf} interface implementations' creation. Containing methods calling a specific factories.
 */
public class PropertyHandlerFactory implements PropertyHandlerFactoryIf {

    /**
     * Calls the {@link GeneratorPropertyHandlerFactory} and returns the {@link com.passnail.server.core.tools.prop.generator.GeneratorPropertyHandler} fabricated object.
     *
     * @return {@link com.passnail.server.core.tools.prop.generator.GeneratorPropertyHandler}
     */
    @Override
    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new GeneratorPropertyHandlerFactory().getGeneratorPropertyHandler();
    }
}
