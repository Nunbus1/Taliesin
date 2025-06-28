package com.taliesin.taliesin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Retourne les informations de l'utilisateur actuellement authentifié.
     *
     * @param auth contexte d'authentification Spring Security
     * @return l'utilisateur correspondant à l'email d'authentification
     * @throws java.util.NoSuchElementException si aucun utilisateur n'est trouvé
     */
    @GetMapping("/user")
    public User getUser(Authentication auth) {
        return userRepository.findByEmail(auth.getName()).orElseThrow();
    }
}
