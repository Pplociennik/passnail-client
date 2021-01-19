package com.passnail.server.core.tools.service;

import java.io.IOException;
import java.util.Properties;

public interface PropertyHandlerIf {

    void loadProperties(String aPath) throws IOException;

    Properties getProperties();
}
