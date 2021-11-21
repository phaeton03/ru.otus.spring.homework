package ru.otus.spring.homework_13.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Comment {
    @Id
    private String id;

    @NonNull
    private String comment;

    @CreatedDate
    private LocalDate dateCreated;
}