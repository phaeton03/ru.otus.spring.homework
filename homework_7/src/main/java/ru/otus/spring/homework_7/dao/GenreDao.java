package ru.otus.spring.homework_7.dao;

import ru.otus.spring.homework_7.domain.Genre;

public interface GenreDao {
    Genre getGenre(String genreName);
}