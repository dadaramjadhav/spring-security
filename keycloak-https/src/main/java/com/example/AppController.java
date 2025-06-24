package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class AppController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user() {
        return "User endpoint";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Admin endpoint";
    }

    @GetMapping("/token")
    @ResponseBody
    public String token(@AuthenticationPrincipal OidcUser oidcUser) {
        return "ID Token:\n" + oidcUser.getIdToken().getTokenValue();
    }

}