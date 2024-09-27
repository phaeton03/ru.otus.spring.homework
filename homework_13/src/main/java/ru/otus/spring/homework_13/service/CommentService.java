package ru.otus.spring.homework_13.service;

import org.springframework.transaction.annotation.Transactional;

public interface CommentService {
    @Transactional
    void publishComment(String message, String bookId);

    String getAllCommentsByBook(String bookId);
}