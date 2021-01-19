package com.passnail.server.core.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AuthService authService() {
        return new AuthService();
    }
}
