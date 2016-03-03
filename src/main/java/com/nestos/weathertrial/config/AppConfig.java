package com.nestos.weathertrial.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * WeatherTrial application configuration.
 *
 * @author Roman Osipov
 */
@SpringBootApplication(scanBasePackages = "com.nestos.weathertrial")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
