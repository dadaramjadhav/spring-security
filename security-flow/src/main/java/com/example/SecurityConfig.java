package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/public", "/login").permitAll()
                                                .requestMatchers("/admin").hasRole("ADMIN")
                                                .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .permitAll()
                                                .successHandler(customSuccessHandler()))
                                .logout(logout -> logout.logoutSuccessUrl("/public"));

                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService() {
                return new InMemoryUserDetailsManager(
                                User.withUsername("test")
                                                .password("test")
                                                .roles("USER")
                                                .build());
        }

        @SuppressWarnings("deprecation") // for demo only
        @Bean
        public static NoOpPasswordEncoder passwordEncoder() {
                return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        }

        @Bean
        public AuthenticationSuccessHandler customSuccessHandler() {
                return new AuthLoggingHandler();
        }
}