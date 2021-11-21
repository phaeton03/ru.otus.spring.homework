package ru.otus.spring.homework_13.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.homework_13.domain.embedded.Author;
import ru.otus.spring.homework_13.domain.embedded.Genre;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class Book {
    @Id
    private String id;

    private String name;

    private Author author;

    private Genre genre;

    @DBRef
    private List<Comment> comments = new ArrayList<>();

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, Author author, Genre genre, List<Comment> comments) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }

    public Book(String id, String name, Author author, Genre genre, List<Comment> comments) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
    }
}