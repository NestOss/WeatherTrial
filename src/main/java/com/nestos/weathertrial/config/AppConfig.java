package com.nestos.weathertrial.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * WeatherTrial application configuration.
 *
 * @author Roman Osipov
 */
@Configuration
@ComponentScan(basePackages = "com.nestos.weathertrial")
@EnableAutoConfiguration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
