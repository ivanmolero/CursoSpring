package com.imolerodev.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                    .anyRequest()
                    .authenticated();
        }).httpBasic(Customizer.withDefaults())
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .cors(Customizer.withDefaults());
        return http.build();
    }
}
