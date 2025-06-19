package com.taliesin.taliesin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }
    
    @PostMapping("/register")
    public String handleRegister(@RequestParam String username, @RequestParam String password) {
        //TODO: process POST request
        
        return "login.html";
    }
    
}
