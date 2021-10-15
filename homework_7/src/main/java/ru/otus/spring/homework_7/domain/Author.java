package ru.otus.spring.homework_7.domain;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Data
@EqualsAndHashCode(exclude = "books")
@ToString(exclude = "books")
public class Author {
    private Long id;
    private String name;
    private Set<Book> books;
}