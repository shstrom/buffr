package main.java.com.example.buffr.controller;

import main.java.com.example.buffr.entity.User;
import main.java.com.example.buffr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}