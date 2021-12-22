package ru.otus.spring.homework_25.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_25.domain.Comment;

import java.util.List;

public interface CommentService {
    @Transactional
    void publishComment(String message, Long bookId);

    @Transactional(readOnly = true)
    List<Comment> getAllCommentsByBook(Long bookId);

    void deleteComment(Long commentId);

    @Transactional
    void editComment(Long commentId, String message);
}