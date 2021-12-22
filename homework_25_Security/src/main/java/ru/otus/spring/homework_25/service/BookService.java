package ru.otus.spring.homework_25.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_25.domain.Book;

import java.util.List;

public interface BookService {
    @Transactional(readOnly = true)
    List<Book> findAllBooks();

    @Transactional(readOnly = true)
    List<Book> findBooksByAuthor(String authorName);

    @Transactional(readOnly = true)
    List<Book> findBooksByGenre(String genreName);

    void deleteBook(Long bookId);

    void saveBook(Book book);

    @Transactional(readOnly = true)
    Book findBook(Long bookId);
}