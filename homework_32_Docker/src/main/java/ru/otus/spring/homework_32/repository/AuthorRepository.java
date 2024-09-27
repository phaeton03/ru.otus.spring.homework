package ru.otus.spring.homework_32.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_32.domain.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByName(String authorName);
}