package com.passnail.server.core.tools.factory;

import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.prop.generator.DefaultGeneratorPropertyHandler;


/**
 * An {@link com.passnail.server.core.tools.factory.impl.propertyhandler.PropertyHandlerFactory} factory's interface containing methods for getting property handlers.
 */
public interface PropertyHandlerFactoryIf {

    /**
     * Calls the {@link com.passnail.server.core.tools.factory.impl.propertyhandler.gen.GeneratorPropertyHandlerFactory} factory and returns the {@link DefaultGeneratorPropertyHandler} fabricated object.
     *
     * @return {@link DefaultGeneratorPropertyHandler}
     */
    PropertyHandlerIf getGeneratorPropertyHandler();
}
