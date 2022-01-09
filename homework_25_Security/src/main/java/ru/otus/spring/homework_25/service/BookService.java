package ru.otus.spring.homework_25.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_25.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    List<Book> findBooksByAuthor(String authorName);

    List<Book> findBooksByGenre(String genreName);

    void deleteBook(Long bookId);

    void saveBook(Book book);

    Book findBook(Long bookId);
}