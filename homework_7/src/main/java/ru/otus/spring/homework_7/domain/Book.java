package ru.otus.spring.homework_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    private Long id;
    private String name;
    private Author author;
    private Genre genre;
}