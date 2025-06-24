package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KeycloakUI {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(KeycloakUI.class, args);
        System.out.println("Keycloak UI Application Started");
    }
}