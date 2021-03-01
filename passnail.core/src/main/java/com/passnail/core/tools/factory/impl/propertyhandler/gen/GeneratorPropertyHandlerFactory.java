package com.passnail.core.tools.factory.impl.propertyhandler.gen;

import com.passnail.core.service.prop.PropertyHandlerIf;
import com.passnail.core.tools.pass.DefaultPasswordGenerator;
import com.passnail.core.tools.prop.generator.DefaultGeneratorPropertyHandler;


/**
 * A factory for the {@link DefaultGeneratorPropertyHandler} objects creation.
 */
public class GeneratorPropertyHandlerFactory {

    /**
     * Returns a {@link DefaultGeneratorPropertyHandler} object being used in the {@link DefaultPasswordGenerator} for getting properties.
     *
     * @return {@link PropertyHandlerIf}
     */
    public PropertyHandlerIf getGeneratorPropertyHandler() {
        return new DefaultGeneratorPropertyHandler();
    }
}
