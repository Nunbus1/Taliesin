package com.taliesin.taliesin.controller; 

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Contrôleur principal gérant la redirection initiale selon l'état d'authentification.
 */
@Controller
public class IndexController {

    /**
     * Redirige l'utilisateur vers la page d'accueil s'il est connecté, sinon vers la page de login.
     *
     * @param authentication l'objet Spring Security représentant l'utilisateur connecté (s'il y en a un)
     * @return redirection vers la page appropriée
     */
    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/index.html";
        } else {
            return "redirect:/login.html";
        }
    }
}
