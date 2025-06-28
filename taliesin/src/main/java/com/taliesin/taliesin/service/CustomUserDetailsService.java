package com.taliesin.taliesin.service;

import com.taliesin.taliesin.model.User;
import com.taliesin.taliesin.repository.UserRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Implémentation personnalisée de {@link UserDetailsService} utilisée par Spring Security
 * pour charger les détails d’un utilisateur à partir de l’adresse email.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Charge un utilisateur par son adresse email.
     *
     * @param email l'adresse email fournie lors de l'authentification
     * @return un objet {@link UserDetails} contenant les informations de sécurité
     * @throws UsernameNotFoundException si aucun utilisateur n'est trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    /**
     * Méthode d'initialisation appelée automatiquement après l’injection des dépendances.
     * (Utilisée ici à des fins de debug ou de vérification de démarrage.)
     */
    @PostConstruct
    public void init() {
        // Log initialisation — peut être remplacé par un logger si nécessaire
        System.out.println("✅ CustomUserDetailsService initialisé");
    }
}
