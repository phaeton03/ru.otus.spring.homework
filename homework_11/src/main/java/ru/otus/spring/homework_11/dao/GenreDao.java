package ru.otus.spring.homework_11.dao;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework_11.domain.Genre;

import java.util.Optional;

public interface GenreDao extends CrudRepository<Genre, Long> {
    Optional<Genre> findGenreByName(String genreName);
}