package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers("/").permitAll()
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .requestMatchers("/user/**").hasRole("USER")
                                                // .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                                                // .requestMatchers("/user").hasAuthority("ROLE_USER")
                                                .anyRequest().authenticated())
                                .oauth2Login(withDefaults())
                                .logout(logout -> logout
                                                .logoutSuccessHandler(oidcLogoutSuccessHandler()));
                return http.build();
        }

        // role
        @Bean
        public OidcUserService oidcUserService() {
                return new OidcUserService() {
                        @Override
                        public OidcUser loadUser(OidcUserRequest userRequest) {
                                OidcUser oidcUser = super.loadUser(userRequest);
                                List<GrantedAuthority> mappedAuthorities = new ArrayList<>();

                                Map<String, Object> resourceAccess = (Map<String, Object>) oidcUser.getClaims()
                                                .get("resource_access");
                                if (resourceAccess != null && resourceAccess.containsKey("myapp")) {
                                        Map<String, Object> client = (Map<String, Object>) resourceAccess
                                                        .get("myapp");
                                        List<String> roles = (List<String>) client.get("roles");

                                        mappedAuthorities.addAll(roles.stream()
                                                        .map(role -> new SimpleGrantedAuthority(
                                                                        "ROLE_" + role.toUpperCase()))
                                                        .toList());
                                }

                                return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(),
                                                oidcUser.getUserInfo());
                        }
                };
        }

        // logout working
        private LogoutSuccessHandler oidcLogoutSuccessHandler() {
                return (request, response, authentication) -> {
                        String issuer = "http://localhost:8081/realms/myrealm"; // Replace with your realm
                        String clientId = "myapp"; // Replace with your client ID
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
