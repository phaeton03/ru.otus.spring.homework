package ru.otus.spring.homework_13.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Author {
    @Id
    private String id;

    @NonNull
    private String name;

    private String biography;
}