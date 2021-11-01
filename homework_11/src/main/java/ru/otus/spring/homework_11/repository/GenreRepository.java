package ru.otus.spring.homework_11.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework_11.domain.Genre;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findGenreByName(String genreName);
}