package ru.otus.spring.homework_26.domain.mongo.embedded;

import lombok.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Genre {
    @NonNull
    private String name;
}