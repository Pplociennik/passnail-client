package com.passnail.server.core.tools.factory.impl.generator;

import com.passnail.server.core.tools.prop.generator.GeneratorPropertyHandler;
import com.passnail.server.core.tools.service.PropertyHandlerIf;

public class GeneratorPropertyHandlerFactory {

    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new GeneratorPropertyHandler();
    }
}
