package com.imolerodev.pizza.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

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
                    .requestMatchers("/api/auth/**").permitAll()
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
//        }).httpBasic(Customizer.withDefaults())
        }).addFilterBefore(jwtFilter, BasicAuthenticationFilter.class)
                .csrf(csrfConfigurer -> csrfConfigurer.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
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
