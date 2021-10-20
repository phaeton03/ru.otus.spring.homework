package ru.otus.spring.homework_7.dao;

import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Comment;
import ru.otus.spring.homework_7.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void save(Book book);

    void deleteById(Long id);

    Optional<Book> getById(Long id);

    List<Book> getAll();

    List<Book> getByGenre(Genre genre);

    List<Book> getByAuthor(Author author);

    void addBookComment(Comment comment, Book book);
}