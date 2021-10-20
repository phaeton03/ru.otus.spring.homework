package ru.otus.spring.homework_7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.otus.spring.homework_7.dao.AuthorDao;
import ru.otus.spring.homework_7.dao.BookDao;
import ru.otus.spring.homework_7.dao.GenreDao;
import ru.otus.spring.homework_7.domain.Author;

import java.util.Collections;
import java.util.Objects;

// url h2 консоли: http://localhost:8080/h2-console
// url базы: jdbc:h2:mem:testdb

@SpringBootApplication
@EnableJpaAuditing
public class Homework7Application {
    public static void main(String[] args) {
        SpringApplication.run(Homework7Application.class);
    }
}