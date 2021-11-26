package ru.otus.spring.homework_13.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.homework_13.domain.embedded.Comment;
import ru.otus.spring.homework_13.domain.embedded.Genre;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Document
@Getter
@Setter
@ToString
public class Book {
    @Id
    private String id;

    private String name;

    @DBRef
    private Author author;

    private Genre genre;

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