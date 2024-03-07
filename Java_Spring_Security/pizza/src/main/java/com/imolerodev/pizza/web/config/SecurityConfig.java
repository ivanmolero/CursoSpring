package com.imolerodev.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                    .requestMatchers(HttpMethod.GET, "/api/pizzas/**")// /api/* solo opera a un nivel, /api/** es multinivel
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

    @Bean // para que reconozca que se trabajan con usuarios propios
    public UserDetailsService memoryUsers() {
        UserDetails admin = User.builder() // creación de un usuario
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean // para facilitar un password encoder
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
