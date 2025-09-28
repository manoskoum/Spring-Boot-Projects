package com.Auth.Auth.controller;

import com.Auth.Auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String loginPage() { return "login"; }     // templates/login.html

    @GetMapping("/register")
    public String registerPage() { return "register"; } // templates/register.html

    @GetMapping("/home")
    public String homePage() { return "home"; }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        try {
            service.register(email, username, password);
            return "redirect:/login?registered"; // μήνυμα στη login
        } catch (RuntimeException ex) {
            model.addAttribute("error", ex.getMessage());
            return "register";
        }
    }
}
