package com.bookstore.controller;

import com.bookstore.entity.User;
import com.bookstore.services.UserServices;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
        @Autowired
        private UserServices userservice;
        @GetMapping("/login")
        public String Login() {
            return "user/login";
        }

        @GetMapping("/register")
        public String register(Model model) {
            model.addAttribute("user", new User());
            return "user/register";
        }
        @PostMapping("/register")
        public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
            if (bindingResult.hasErrors()) {
                List<FieldError> errors = bindingResult.getFieldErrors();
                for (FieldError error : errors) {
                    model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
                }
                return "user/register";
            }
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userservice.save(user);
            return "redirect:/Aegin";

        }

}
