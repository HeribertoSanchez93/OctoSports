package com.fetchEquipos.octoSports.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
@Getter
public class ConfigApiKey {

    @Value("${api.key}")
    private String apiKey;
}
