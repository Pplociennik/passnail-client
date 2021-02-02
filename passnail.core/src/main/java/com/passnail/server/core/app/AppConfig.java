package com.passnail.server.core.app;

import com.passnail.connect.ConnectModuleConfig;
import com.passnail.data.access.model.dao.DataAccessModuleConfig;
import com.passnail.data.model.entity.DataModelModuleConfig;
import com.passnail.security.SecurityModuleConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 23:33
 * Project: passnail-client
 */
@Configuration
@ComponentScan
@Import({
        ConnectModuleConfig.class,
        DataAccessModuleConfig.class,
        DataModelModuleConfig.class,
        SecurityModuleConfig.class
})
public class AppConfig {

}
