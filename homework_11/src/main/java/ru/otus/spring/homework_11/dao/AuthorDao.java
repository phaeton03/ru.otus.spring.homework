package ru.otus.spring.homework_11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework_11.domain.Author;

import java.util.Optional;

public interface AuthorDao extends JpaRepository<Author, Long>, CrudRepository<Author, Long> {
    Optional<Author> findAuthorByName(String authorName);
}