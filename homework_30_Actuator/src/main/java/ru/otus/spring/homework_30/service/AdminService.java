package ru.otus.spring.homework_30.service;

import ru.otus.spring.homework_30.domain.Book;

import java.util.List;

public interface AdminService {
    void deleteBook(Long bookId);

    void deleteComment(Long commentId);

    void editComment(Long commentId, String message);

    List<Book> getBooks();
}