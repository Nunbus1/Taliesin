package com.taliesin.taliesin.user;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * Contrôleur Spring MVC gérant l'enregistrement des utilisateurs.
 */
@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    private final Path uploadDir = Paths.get("taliesin/src/main/data/profilepicture");

    /**
     * Traite la requête POST d'enregistrement d'un nouvel utilisateur.
     * Gère l'upload d'une photo de profil optionnelle.
     *
     * @param username Email de l'utilisateur
     * @param password Mot de passe (non chiffré à l'entrée)
     * @param first Prénom
     * @param last Nom
     * @param picture Fichier image de profil (optionnel)
     * @return nom du fichier HTML à afficher après enregistrement
     * @throws Exception en cas d'erreur lors de la sauvegarde du fichier
     */
    @PostMapping("/register")
    public String handleRegister(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String first,
            @RequestParam String last,
            @RequestParam(required = false) MultipartFile picture
    ) throws Exception {

        User user = new User();

        if (picture != null && !picture.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + picture.getOriginalFilename();
            Path filePath = uploadDir.resolve(filename);
            Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            user.setPicture("/profilepicture/" + filename); // chemin relatif
        }

        user.setLast(last);
        user.setFirst(first);
        user.setEmail(username);
        user.setPassword(encoder.encode(password));

        userRepository.save(user);

        return "login.html";
    }
}
