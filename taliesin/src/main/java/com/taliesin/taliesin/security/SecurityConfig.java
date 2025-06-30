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

/**
 * Configuration de sécurité pour l'application Taliesin.
 * Définit les règles d'authentification, d'autorisation, et de gestion des sessions.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * Définit la stratégie de sécurité HTTP (accès aux ressources, formulaires, logout...).
     *
     * @param http l'objet de configuration HTTP fourni par Spring Security
     * @return la chaîne de filtres de sécurité configurée
     * @throws Exception si une erreur survient lors de la configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF désactivé pour les formulaires simples
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/login.html", "/register.html", "/register", "/css/**", "/js/**")
                    .permitAll() // Accès libre aux pages publiques et ressources statiques
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html")               // Page de login personnalisée
                .loginProcessingUrl("/login")           // URL à laquelle Spring traite les identifiants
                .defaultSuccessUrl("/index.html", true) // Redirection après succès
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")                         // URL de déconnexion
                .logoutSuccessUrl("/login?logout")            // Redirection après déconnexion
                .invalidateHttpSession(true)                  // Invalidation de session
                .deleteCookies("JSESSIONID")                  // Suppression du cookie de session
                .permitAll()
            );

        return http.build();
    }

    /**
     * Configure le gestionnaire d'authentification avec le service utilisateur personnalisé.
     *
     * @param http l'objet HttpSecurity
     * @return l'AuthenticationManager configuré
     * @throws Exception en cas d'erreur
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
        return builder.build();
    }

    /**
     * Encodeur de mot de passe utilisant BCrypt.
     *
     * @return une instance de {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
