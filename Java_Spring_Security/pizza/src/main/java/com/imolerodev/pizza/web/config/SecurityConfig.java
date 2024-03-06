package com.imolerodev.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .cors().and
//                .authorizeHttpRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers(HttpMethod.GET, "/api/**")// /api/* solo opera a un nivel, /api/** es multinivel
                    .permitAll() // se permiten todas las peticiones sin autenticar que coincide con el requestMatchers
                    .requestMatchers(HttpMethod.PUT) // aplica a todos los metodos put de la aplicación
                    .denyAll() //niega todas las peticiones
                    .anyRequest()
                    .authenticated();
        }).httpBasic(Customizer.withDefaults())
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .cors(Customizer.withDefaults());
        return http.build();
    }
}
