package com.fetchEquipos.octoSports.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfigTopic {

    private  Map<String,String> generalConfiguration(){
        Map<String,String> configurations = new HashMap<>();

        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);
        configurations.put(TopicConfig.RETENTION_MS_CONFIG,"8640000");
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824");
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"100012");

        return configurations;
    }

    @Bean
    public NewTopic fetchAllTeamsTopic(){

        Map<String,String> configurations = generalConfiguration();

        return TopicBuilder.name("fetchAllTeams-Topic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
