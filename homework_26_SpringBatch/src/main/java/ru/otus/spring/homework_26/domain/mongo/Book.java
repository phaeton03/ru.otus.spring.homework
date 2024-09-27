package ru.otus.spring.homework_26.domain.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.homework_26.domain.mongo.embedded.Comment;
import ru.otus.spring.homework_26.domain.mongo.embedded.Genre;

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