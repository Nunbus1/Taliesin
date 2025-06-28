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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired PasswordEncoder encoder;

    private final Path uploadDir = Paths.get("taliesin/src/main/data/profilepicture");
    
    @PostMapping("/register")
    public String handleRegister(@RequestParam String username, @RequestParam String password, @RequestParam String first, @RequestParam String last, @RequestParam(required = false) MultipartFile picture) throws Exception  {
        User user = new User();
        System.out.println("Chemin absolu uploadDir = " + uploadDir.toAbsolutePath());
        if (picture != null && !picture.isEmpty()) {
            // Sauvegarder l'image localement 
            String filename = UUID.randomUUID() + "_" + picture.getOriginalFilename();
            Path filePath = uploadDir.resolve(filename);
            Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Stocke le chemin relatif en base 
            user.setPicture("/profilepicture/" + filename);
        }

        user.setLast(last);
        user.setFirst(first);
        user.setEmail(username);
        user.setPassword(encoder.encode(password));
       

        userRepository.save(user);

        return "login.html";
    }
    
}
