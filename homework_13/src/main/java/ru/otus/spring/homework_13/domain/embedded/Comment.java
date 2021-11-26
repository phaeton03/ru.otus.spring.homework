package ru.otus.spring.homework_13.domain.embedded;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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