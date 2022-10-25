package com.SGSJ.Saturn.rest_controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configures CORS
// Configuramos CORS
@Controller
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${spring.application.domain.cors}")
    private String corsURL;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Only execute it on production mode.
        // Ejecutarlo solo en modo de producción.
        // registry.addMapping("/user/add").allowedOrigins(corsURL);
    }
}
