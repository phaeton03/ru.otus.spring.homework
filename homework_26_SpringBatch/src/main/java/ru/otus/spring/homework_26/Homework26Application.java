package ru.otus.spring.homework_26;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongock
@EnableMongoAuditing
@EnableJpaAuditing
public class Homework26Application {
    public static void main(String[] args) {
        SpringApplication.run(Homework26Application.class);
    }
}