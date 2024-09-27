package ru.otus.spring.homework_30.service;

import ru.otus.spring.homework_30.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    List<Book> findBooksByAuthor(String authorName);

    List<Book> findBooksByGenre(String genreName);

    void deleteBook(Long bookId);

    void saveBook(Book book);

    Book findBook(Long bookId);
}