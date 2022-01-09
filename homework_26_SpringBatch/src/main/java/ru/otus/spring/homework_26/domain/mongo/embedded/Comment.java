package ru.otus.spring.homework_26.domain.mongo.embedded;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Comment {
    @NonNull
    private String comment;

    private LocalDate dateCreated;

    public Comment(@NonNull String comment) {
        this.comment = comment;
        this.dateCreated = LocalDate.now();
    }
}