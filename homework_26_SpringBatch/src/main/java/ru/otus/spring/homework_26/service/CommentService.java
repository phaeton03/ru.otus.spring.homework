package ru.otus.spring.homework_26.service;

import org.springframework.transaction.annotation.Transactional;

public interface CommentService {
    void publishComment(String message, String bookId);

    String getAllCommentsByBook(String bookId);
}