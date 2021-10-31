package ru.otus.spring.homework_9.repository;

import ru.otus.spring.homework_9.domain.Author;

public interface AuthorDao {
    Boolean findAuthorById(Long id);
    void save(Author author);
    Author getAuthor(String authorName);
}