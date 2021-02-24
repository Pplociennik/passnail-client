package com.passnail.core.main.config;

import com.passnail.connect.ConnectModuleConfig;
import com.passnail.data.DataServiceModuleConfig;
import com.passnail.data.access.model.DataAccessModuleConfig;
import com.passnail.data.model.DataModelModuleConfig;
import com.passnail.security.SecurityModuleConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 23:33
 * Project: passnail-client
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Import({
        ConnectModuleConfig.class,
        DataAccessModuleConfig.class,
        DataModelModuleConfig.class,
        DataServiceModuleConfig.class,
        SecurityModuleConfig.class
})
public class AppConfig {

}