package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// applicaible at controller level only
// This is a simple example of how to configure CORS at the controller level in Spring Boot
// @CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/user")
    public String user() {
        return "User endpoint: ";
    }

}