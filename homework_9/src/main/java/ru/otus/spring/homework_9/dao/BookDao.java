package ru.otus.spring.homework_9.dao;

import ru.otus.spring.homework_9.domain.Book;
import ru.otus.spring.homework_9.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void save(Book book);

    void deleteById(Long id);

    Optional<Book> getById(Long id);

    List<Book> getAll();

    void addBookComment(Comment comment, Book book);
}