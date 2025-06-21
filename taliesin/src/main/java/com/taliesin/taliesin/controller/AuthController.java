package com.taliesin.taliesin.controller;

import com.taliesin.taliesin.model.User;
import com.taliesin.taliesin.repository.UserRepository;

import java.io.File;

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
    
    @PostMapping("/register")
    public String handleRegister(@RequestParam String username, @RequestParam String password, @RequestParam String first, @RequestParam String last, @RequestParam(required = false) MultipartFile picture) throws Exception  {
        String path = null;
        if (picture != null && !picture.isEmpty()) {
            // Sauvegarder l'image localement (ou cloud)
            String fileName = "uploads/" + picture.getOriginalFilename();
            File f = new File("src/main/resources/static/" + fileName);
            f.getParentFile().mkdirs();
            picture.transferTo(f);
            path = "/" + fileName;
        }

        User user = new User();
        user.setLast(last);
        user.setFirst(first);
        user.setEmail(username);
        user.setPassword(encoder.encode(password));
        user.setpicture(path);

        userRepository.save(user);

        return "login.html";
    }
    
}
