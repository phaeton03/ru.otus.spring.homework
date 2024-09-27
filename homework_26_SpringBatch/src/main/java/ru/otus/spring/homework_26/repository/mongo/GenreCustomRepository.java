package ru.otus.spring.homework_26.repository.mongo;

import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Genre;

import java.util.List;

public interface GenreCustomRepository {
    List<Genre> findAllGenres();
}