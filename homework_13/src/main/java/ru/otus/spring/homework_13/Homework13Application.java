package ru.otus.spring.homework_13;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongock
@EnableMongoAuditing
public class Homework13Application {
    public static void main(String[] args) {
        SpringApplication.run(Homework13Application.class);
    }
}