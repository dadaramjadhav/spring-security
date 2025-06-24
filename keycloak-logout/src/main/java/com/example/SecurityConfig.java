package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers("/").permitAll()
                                                .anyRequest().authenticated())
                                .oauth2Login(withDefaults())
                                .logout(logout -> logout
                                                .logoutSuccessHandler(oidcLogoutSuccessHandler()));
                return http.build();
        }

        // logout working
        private LogoutSuccessHandler oidcLogoutSuccessHandler() {
                return (request, response, authentication) -> {
                        String issuer = "http://localhost:8081/realms/demo"; // Replace with your realm
                        String clientId = "spring-pkce"; // Replace with your client ID
                        String redirectUri = "http://localhost:8080/"; // Where user goes after logout

                        if (authentication != null && authentication.getPrincipal() instanceof OidcUser oidcUser) {
                                String idToken = oidcUser.getIdToken().getTokenValue();
                                String logoutUrl = issuer + "/protocol/openid-connect/logout" +
                                                "?id_token_hint=" + idToken +
                                                "&post_logout_redirect_uri=" + redirectUri;

                                response.sendRedirect(logoutUrl);
                        } else {
                                response.sendRedirect(redirectUri); // fallback
                        }
                };
        }
}
