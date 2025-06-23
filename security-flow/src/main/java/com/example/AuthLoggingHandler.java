package com.example;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class AuthLoggingHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        System.out.println("✅ LOGIN SUCCESS");
        System.out.println("Username: " + authentication.getName());
        System.out.println("Authorities: " + authentication.getAuthorities());
        response.sendRedirect("/secured");
    }
}
