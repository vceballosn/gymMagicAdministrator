package com.gym.magic.administrator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Permite todas las peticiones sin autenticación.
            .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
            // Deshabilita la protección CSRF para poder usar Postman, etc.
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}