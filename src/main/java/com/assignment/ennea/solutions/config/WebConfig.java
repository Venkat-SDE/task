package com.assignment.ennea.solutions.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * WebConfig Class
 */
@Configuration
public class WebConfig {

    /**
     * @return the mapped object
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
