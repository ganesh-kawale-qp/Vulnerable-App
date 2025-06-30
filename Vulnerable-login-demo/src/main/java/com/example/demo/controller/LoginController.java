package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login"; // Loads login.html
    }

    @PostMapping("/doLogin")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpServletRequest request) {
        // Check the database for registered user
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (user != null) {
            // Successful login — store user in session
            request.getSession().setAttribute("user", user);
            return "redirect:/dashboard"; // or return "home";
        } else {
            // Invalid login — stay on login page
            request.setAttribute("error", "Invalid credentials");
            return "login";
        }
    }
}
