package com.app.productservice.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced       // Client side load balancing
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
