package com.taliesin.taliesin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Configuration Spring MVC personnalisée.
 * Permet de servir des fichiers statiques (images de profil) depuis un dossier local.
 */
@Configuration 
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure un gestionnaire de ressources pour servir les images de profil.
     *
     * @param registry le registre de gestion des ressources
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/profilepicture/**")
            .addResourceLocations("file:taliesin/src/main/data/profilepicture/");
    }
}
