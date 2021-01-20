package com.passnail.server.core.tools.prop;

import com.passnail.server.core.app.config.ConfAttributes;

/**
 * A class holding the constants containing the information needed for the property handlers.
 */
public final class PropertyConstants {

    private static ConfAttributes confAttributes = ConfAttributes.INSTANCE;

    private PropertyConstants() {
    }

    public static final String DEFAULT_GENERATOR_PROPERTIES_PATH = confAttributes.getInstallationPath() + "/conf/generator.properties";

}
