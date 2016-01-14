package com.nestos.weathertrial;

import com.nestos.weathertrial.config.AppConfig;
import org.springframework.boot.SpringApplication;

/**
 * Spring boot entry point.
 *
 * @author Roman Osipov
 */
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

}
