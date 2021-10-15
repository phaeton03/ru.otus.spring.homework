package ru.otus.spring.homework_7.domain;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "books")
@ToString(exclude = "books")
public class Genre {
    private Long id;
    private String name;
    private Set<Book> books;

}