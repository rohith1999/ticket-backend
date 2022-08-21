package com.mithran.ticket.configuration;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mithran.ticket.constant.PropertyConstant;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**").allowedMethods(
                        PropertyConstant.PUT, PropertyConstant.GET, PropertyConstant.POST, PropertyConstant.DELETE)
                        .allowedHeaders("*").allowedOriginPatterns("*").allowCredentials(true);
                WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }

}
