package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {
        @Autowired
        private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/public").permitAll()
                                                .anyRequest().authenticated())
                                .oauth2Login(oauth -> oauth
                                                .userInfoEndpoint(userInfo -> userInfo.userService(oauth2UserService))
                                                .defaultSuccessUrl("/secured", true))
                                .logout(logout -> logout
                                                .logoutSuccessUrl("/")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID"));

                return http.build();
        }

        // @Bean
        // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http
        // .authorizeHttpRequests(auth -> auth
        // .requestMatchers("/", "/public").permitAll()
        // // .requestMatchers("/admin/**").hasRole("ADMIN")
        // // .requestMatchers("/user/**").hasRole("USER")
        // .anyRequest().authenticated())
        // .oauth2Login(oauth -> oauth
        // .defaultSuccessUrl("/secured", true))
        // .logout(logout -> logout
        // .logoutSuccessUrl("/")
        // .invalidateHttpSession(true)
        // .deleteCookies("JSESSIONID"));

        // return http.build();
        // }
}
