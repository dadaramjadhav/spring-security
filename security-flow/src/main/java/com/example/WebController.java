package com.example;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/public")
    public String publicPage() {
        return "This is a public page. <a href='/login'>Login</a>";
    }

    @GetMapping("/secured")
    public String secured() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Secured Page - Hello, " + auth.getName() + "<br/>Roles: " + auth.getAuthorities();
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userPage() {
        return "Hello USER";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "Hello ADMIN";
    }
}