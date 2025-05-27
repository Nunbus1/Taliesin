package com.taliesin.taliesin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // Permet tout sans authentification
            )
            .csrf(csrf -> csrf.disable()) // Désactive CSRF correctement en lambda DSL
            .formLogin(form -> form.disable()); // Désactive la page /login

        return http.build();
    }
}