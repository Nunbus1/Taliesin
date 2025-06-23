package com.taliesin.taliesin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.taliesin.taliesin.model.User;
import com.taliesin.taliesin.repository.UserRepository;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/index.html";
        } else {
            return "redirect:/login.html";
        }
    }
}
