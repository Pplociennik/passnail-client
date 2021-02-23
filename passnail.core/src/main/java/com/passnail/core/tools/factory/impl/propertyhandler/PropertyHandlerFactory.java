package com.passnail.core.tools.factory.impl.propertyhandler;

import com.passnail.core.service.prop.PropertyHandlerIf;
import com.passnail.core.tools.factory.impl.propertyhandler.gen.GeneratorPropertyHandlerFactory;
import com.passnail.core.tools.prop.generator.DefaultGeneratorPropertyHandler;
import com.passnail.core.tools.factory.PropertyHandlerFactoryIf;


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
