package ru.otus.spring.homework_25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// url h2 консоли: http://localhost:8080/h2-console
// url базы: jdbc:h2:mem:testdb

@SpringBootApplication
@EnableJpaAuditing
@EnableWebSecurity
public class Homework25Application {
    public static void main(String[] args) {
        SpringApplication.run(Homework25Application.class);
    }
}