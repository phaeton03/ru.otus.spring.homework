package ru.otus.spring.homework_26.service;

import ru.otus.spring.homework_26.domain.mongo.Book;

public interface BookService {
    String findAllBooks();

    String findBooksByAuthor(String authorName);

    String findBooksByGenre(String genreName);

    String getBook(String bookId);

    void deleteBook(String bookId);

    void saveBook(Book book);

    Book findBook(String bookId);
}