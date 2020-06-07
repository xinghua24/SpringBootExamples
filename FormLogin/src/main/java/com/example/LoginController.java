package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class LoginController {
    @GetMapping("/auth/login")
    public String login(Principal principal) {
        return "login";
    }
}
