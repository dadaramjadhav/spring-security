package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome endpoint: ";
    }

    @GetMapping("/user")
    public String user() {
        return "User endpoint: ";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin endpoint: ";
    }
}