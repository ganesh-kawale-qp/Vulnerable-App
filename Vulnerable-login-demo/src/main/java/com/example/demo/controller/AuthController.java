package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // ✅ You were missing this annotation
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // Show login page
    @GetMapping("/")
    public String home(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userRepository.login(username, password); // custom method assumed
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } else {
            return "redirect:/?error=true";
        }
    }

    // Show register page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Handle registration
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists.");
            return "register";
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password); // 🔒 Consider hashing in real apps
        userRepository.save(newUser);

        return "redirect:/";
    }


}
