package com.passnail.data.access.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by: Pszemko at wtorek, 02.02.2021 23:45
 * Project: passnail-client
 */
@Configuration
@EnableJpaRepositories
@ComponentScan
public class DataAccessModuleConfig {
}
