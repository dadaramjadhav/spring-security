package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
        model.addAttribute("authority", principal.getAuthorities().toString());
        return "secured"; // secured.html
    }

    @GetMapping("/login-info")
    public Map<String, Object> loginInfo(@AuthenticationPrincipal OAuth2User principal,
            OAuth2AuthenticationToken authToken) {
        Map<String, Object> info = new HashMap<>();
        info.put("name", principal.getAttribute("name"));
        info.put("email", principal.getAttribute("email"));
        info.put("authorities", principal.getAuthorities());
        info.put("client", authToken.getAuthorizedClientRegistrationId()); // github or keycloak
        return info;
    }

}
