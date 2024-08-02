package com.fetchEquipos.octoSports.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configGson {

    @Bean
    public Gson genGson(){
        return new GsonBuilder().serializeNulls().create();
    }
}
