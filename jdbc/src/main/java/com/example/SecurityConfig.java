package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/login").permitAll()
                                                .requestMatchers("/home", "/home/**").permitAll()
                                                .requestMatchers("/user", "/user/**").hasAuthority("ROLE_USER")
                                                .requestMatchers("/admin", "/admin/**").hasAuthority("ROLE_ADMIN"))
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/home", true));
                // .httpBasic(withDefaults());
                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService(DataSource dataSource) {
                JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
                return manager;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // @Bean
        // public WebServerFactoryCustomizer<TomcatServletWebServerFactory>
        // servletContainer() {
        // return server ->
        // server.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
        // }

        // private Connector httpToHttpsRedirectConnector() {
        // Connector connector = new
        // Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        // connector.setScheme("http");
        // connector.setPort(8080);
        // connector.setSecure(false);
        // connector.setRedirectPort(8443);
        // return connector;
        // }

}
