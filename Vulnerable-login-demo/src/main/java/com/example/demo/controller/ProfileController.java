package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String home() {
        return "profile"; // Loads home.html
    }

    @PostMapping("/submit")
    public String submitData(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            Model model
    ) {
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        return "profile"; // Redisplay on the same page
    }
}
