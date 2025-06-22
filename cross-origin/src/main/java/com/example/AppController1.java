package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

// @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")

@RestController
@RequestMapping("/api")
public class AppController1 {

    @GetMapping("/admin")
    public String admin() {
        return "Admin endpoint: ";
    }

}