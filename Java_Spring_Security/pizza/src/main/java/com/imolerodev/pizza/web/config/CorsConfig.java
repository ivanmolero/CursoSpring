package com.imolerodev.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // configura origenes para cors
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // configura metodos http para cors
        corsConfiguration.setAllowedHeaders(Arrays.asList("*")); // configura los diferentes encabezados (headers) para cors

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // registra todos los endpoints de la aplicacion

        return source;
    }
}
