package ru.otus.spring.homework_26.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_26.domain.h2.GenreJPA;

import java.util.Optional;

public interface GenreJPARepository extends JpaRepository<GenreJPA, Long> {
    Optional<GenreJPA> findGenreByName(String genreName);
}