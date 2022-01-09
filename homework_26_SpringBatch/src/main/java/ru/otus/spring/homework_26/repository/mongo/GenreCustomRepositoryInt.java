package ru.otus.spring.homework_26.repository.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Genre;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreCustomRepositoryInt implements GenreCustomRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Genre> findAllGenres() {
        return mongoTemplate.findDistinct("genre", Book.class, Genre.class);
    }
}