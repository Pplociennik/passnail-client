package com.passnail.server.core.tools.factory.impl.propertyhandler;

import com.passnail.server.core.tools.factory.PropertyHandlerFactoryIf;
import com.passnail.server.core.tools.factory.impl.propertyhandler.gen.GeneratorPropertyHandlerFactory;
import com.passnail.server.core.service.prop.PropertyHandlerIf;
import com.passnail.server.core.tools.prop.generator.DefaultGeneratorPropertyHandler;


/**
 * A factory for the {@link PropertyHandlerIf} interface implementations' creation. Contains methods calling a specific factories.
 */
public class PropertyHandlerFactory implements PropertyHandlerFactoryIf {

    /**
     * Calls the {@link GeneratorPropertyHandlerFactory} and returns the {@link DefaultGeneratorPropertyHandler} fabricated object.
     *
     * @return {@link DefaultGeneratorPropertyHandler}
     */
    @Override
    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new GeneratorPropertyHandlerFactory().getGeneratorPropertyHandler();
    }
}
