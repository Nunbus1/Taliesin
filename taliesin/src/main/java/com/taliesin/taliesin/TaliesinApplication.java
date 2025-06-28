package com.taliesin.taliesin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d’entrée principal de l’application Spring Boot Taliesin.
 * Lance le contexte Spring et démarre le serveur embarqué.
 */
@SpringBootApplication
public class TaliesinApplication {

    /**
     * Méthode principale exécutée au démarrage de l’application.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(TaliesinApplication.class, args);
    }
}
