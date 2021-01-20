package com.passnail.server.core.tools.factory;

import com.passnail.server.core.tools.service.PropertyHandlerIf;


/**
 * An {@link com.passnail.server.core.tools.factory.impl.propertyhandler.PropertyHandlerFactory} factory's interface containing methods for getting property handlers.
 */
public interface PropertyHandlerFactoryIf {

    /**
     * Calls the {@link com.passnail.server.core.tools.factory.impl.propertyhandler.generator.GeneratorPropertyHandlerFactory} factory and returns the {@link com.passnail.server.core.tools.prop.generator.GeneratorPropertyHandler} fabricated object.
     *
     * @return {@link com.passnail.server.core.tools.prop.generator.GeneratorPropertyHandler}
     */
    PropertyHandlerIf getGeneratorPropertyHandler();
}
