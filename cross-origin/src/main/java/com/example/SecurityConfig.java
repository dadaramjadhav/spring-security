package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .cors(Customizer.withDefaults()) // ✅ Enable CORS
                                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for APIs
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/**").permitAll() // adjust to your needs
                                                .anyRequest().authenticated());

                return http.build();
        }

}
