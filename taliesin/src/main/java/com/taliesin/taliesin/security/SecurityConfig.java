package com.taliesin.taliesin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactive CSRF correctement en lambda DSL
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/login.html", "/register.html", "/register", "/css/**", "/js/**").permitAll() // autorise l'accès à login et register sans authentification
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/login") 
                .defaultSuccessUrl("/index.html", true)
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/logout")                      // URL à appeler pour déconnexion (POST par défaut)
                .logoutSuccessUrl("/login?logout")        // URL de redirection après logout
                .invalidateHttpSession(true)               // Invalide la session
                .deleteCookies("JSESSIONID")               // Supprime cookie session
                .permitAll()
            );

        return http.build();
    }

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
        return builder.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}