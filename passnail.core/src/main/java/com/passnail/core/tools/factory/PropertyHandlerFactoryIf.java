package com.passnail.core.tools.factory;

import com.passnail.core.service.prop.PropertyHandlerIf;
import com.passnail.core.tools.factory.impl.propertyhandler.PropertyHandlerFactory;
import com.passnail.core.tools.factory.impl.propertyhandler.gen.GeneratorPropertyHandlerFactory;
import com.passnail.core.tools.prop.generator.DefaultGeneratorPropertyHandler;


/**
 * An {@link PropertyHandlerFactory} factory's interface containing methods for getting property handlers.
 */
public interface PropertyHandlerFactoryIf {

    /**
     * Calls the {@link GeneratorPropertyHandlerFactory} factory and returns the {@link DefaultGeneratorPropertyHandler} fabricated object.
     *
     * @return {@link DefaultGeneratorPropertyHandler}
     */
    PropertyHandlerIf getGeneratorPropertyHandler();
}
