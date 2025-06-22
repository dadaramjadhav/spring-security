package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {
    @GetMapping("/home")
    public String home() {
        return "home endpoint: ";
    }

    @GetMapping("/user")
    public String user(Authentication auth) {
        return "User endpoint: " + auth.getName() + ", roles = " +
                auth.getAuthorities();
    }

    @GetMapping("/admin")
    public String admin(Authentication auth) {
        return "Admin endpoint: " + auth.getName() + ", roles = " +
                auth.getAuthorities();
    }

}