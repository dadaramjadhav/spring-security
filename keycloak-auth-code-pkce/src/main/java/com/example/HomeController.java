package com.example;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // home.html
    }

    @GetMapping("/secured")
    public String secured(Model model, @AuthenticationPrincipal OAuth2User principal) {
        model.addAttribute("name", principal.getAttribute("preferred_username"));
        model.addAttribute("email", principal.getAttribute("email"));
        return "secured"; // secured.html
    }
}
