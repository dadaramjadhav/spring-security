package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class AppController {

    @GetMapping("/user")
    public String user() {
        return "User endpoint: ";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin endpoint: ";
    }

    @GetMapping("/debug")
    public ResponseEntity<?> debug(OAuth2AuthenticationToken auth) {
        return ResponseEntity.ok(auth.getAuthorities());
    }
}