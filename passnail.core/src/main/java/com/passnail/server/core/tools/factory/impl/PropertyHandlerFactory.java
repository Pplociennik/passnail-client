package com.passnail.server.core.tools.factory.impl;

import com.passnail.server.core.tools.factory.PropertyHandlerFactoryIf;
import com.passnail.server.core.tools.factory.impl.generator.GeneratorPropertyHandlerFactory;
import com.passnail.server.core.tools.service.PropertyHandlerIf;

public class PropertyHandlerFactory implements PropertyHandlerFactoryIf {

    @Override
    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new GeneratorPropertyHandlerFactory().getGeneratorPropertyHandler();
    }
}
