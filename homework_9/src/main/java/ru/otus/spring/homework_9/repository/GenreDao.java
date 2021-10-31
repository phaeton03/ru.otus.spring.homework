package ru.otus.spring.homework_9.repository;

import ru.otus.spring.homework_9.domain.Genre;

public interface GenreDao {
    Genre getGenre(String genreName);
}