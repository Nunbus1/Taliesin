package com.taliesin.taliesin.controller;

import com.taliesin.taliesin.model.User;
import com.taliesin.taliesin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public User getUser(Authentication auth) {
        return userRepository.findByEmail(auth.getName()).orElseThrow();
    }
}

