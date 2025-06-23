package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "User endpoint: ";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Admin endpoint: ";
    }
}