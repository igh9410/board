package com.springboot.board.controller;

import com.springboot.board.entity.User;
import com.springboot.board.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private UserService userService;

    public AccountController(UserService theUserService) {
        userService = theUserService;
    }

    @GetMapping("/login")
    public String login() {
        return "accounts/login";
    }

    @GetMapping("/register")
    public String register() {
        return "accounts/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);

        return "redirect:/posts/list";
    }



}
