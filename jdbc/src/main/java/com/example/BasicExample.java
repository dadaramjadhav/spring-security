package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BasicExample {
    public static void main(String[] args) {
        SpringApplication.run(BasicExample.class, args);
        System.out.println("           " + new BCryptPasswordEncoder().encode("123"));
    }
}