package ru.otus.spring.homework_13.service;

import ru.otus.spring.homework_13.domain.Book;

public interface BookService {
    String findAllBooks();

    String findBooksByAuthor(String authorName);

    String findBooksByGenre(String genreName);

    String getBook(String bookId);

    void deleteBook(String bookId);

    void saveBook(Book book);

    Book findBook(String bookId);
}