package com.imolerodev.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
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
                    .requestMatchers("/api/customers/**")
                    .hasAnyRole("ADMIN", "CUSTOMER")
                    .requestMatchers(HttpMethod.GET, "/api/pizzas/**")// /api/* solo opera a un nivel, /api/** es multinivel
                    .hasAnyRole("ADMIN", "CUSTOMER") // se permiten peticiones get para los dos roles existentes
                    .requestMatchers(HttpMethod.POST, "/api/pizzas/**")// /api/* solo opera a un nivel, /api/** es multinivel
                    .hasRole("ADMIN") // se permiten peticiones tipo post para el rol administrador
                    .requestMatchers(HttpMethod.PUT) // aplica a todos los metodos put de la aplicación
                    .hasRole("ADMIN") // solo se atienden si tienen el rol admin
                    .requestMatchers("/api/orders/random")
                    .hasAuthority("random_orders")
                    .requestMatchers("/api/orders/**") // aplica a todos los metodos put de la aplicación
                    .hasRole("ADMIN") // solo se atienden si tienen el rol admin
                    .anyRequest()
                    .authenticated();
        }).httpBasic(Customizer.withDefaults())
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean // para facilitar un password encoder
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean // para que reconozca que se trabajan con usuarios propios
//    public UserDetailsService memoryUsers() {
//        UserDetails admin = User.builder() // creación de un usuario admin
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        UserDetails customer = User.builder() // creación de un usuario customer
//                .username("customer")
//                .password(passwordEncoder().encode("customer123"))
//                .roles("CUSTOMER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, customer);
//    }


}
