package com.taliesin.taliesin.service;

import com.taliesin.taliesin.model.User;
import com.taliesin.taliesin.repository.UserRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    @PostConstruct
    public void init() {
        System.out.println("✅ MyUserDetailsService initialisé");
    }

}
