package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
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

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal OAuth2User principal) {
        return "secured............" + principal.getAttribute("email") + " endpoint: ";
    }
}