package com.prashant.ai_dev_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Your Vercel frontend URL
        config.setAllowedOrigins(List.of(
                "https://ai-dev-platform-fe.vercel.app"
        ));

        // Allow browser preflight + API calls
        config.setAllowedMethods(List.of(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        // Allow all headers from frontend
        config.setAllowedHeaders(List.of("*"));

        // No cookies/session needed
        config.setAllowCredentials(false);

        // Cache preflight for 1 hour
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        // Apply to all routes
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}