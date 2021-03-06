package com.passnail.data;

import com.passnail.common.CommonModuleConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * A Spring configuration class for configuring and scanning beans in the module.
 * <p>
 * Created by: Pszemko at poniedziałek, 22.02.2021 21:51
 * Project: passnail-client
 */
@Configuration
@ComponentScan
@Import({CommonModuleConfig.class})
public class DataServiceModuleConfig {
}
