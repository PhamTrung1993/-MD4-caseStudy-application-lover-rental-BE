package com.codegym.controller.register;

import com.codegym.model.User;
import com.codegym.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping
    public String showRegisterForm() {
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/register?success";
    }
}
