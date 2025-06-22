package com.example;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MyRestTemplate implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(MyRestTemplate.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        // option 1: Using RestTemplateBuilder to create a RestTemplate with basic
        // authentication
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8080")
                .basicAuthentication("user", "123")
                .build();

        String url = "/user";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("Response from server: " + response);

        // option 2: Using RestTemplate with basic authentication
        RestTemplate restTemplate2 = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String basicAuth = Base64.getEncoder().encodeToString("admin:123".getBytes());
        headers.set("Authorization", "Basic " + basicAuth);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String responce2 = restTemplate2.exchange("http://localhost:8080/admin", HttpMethod.GET, entity, String.class)
                .getBody();
        System.out.println("Response from server: " + responce2);
    }
}
