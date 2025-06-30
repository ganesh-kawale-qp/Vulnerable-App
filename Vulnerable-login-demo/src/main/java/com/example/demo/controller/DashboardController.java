package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        User user = new User();
        user.setUsername("Pandey"); // Static username for now
        model.addAttribute("user", user);
        return "dashboard"; // Loads dashboard.html
    }
}
