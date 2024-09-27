package ru.otus.spring.homework_13.domain.embedded;

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
public class Genre {
    @NonNull
    private String name;
}