package ru.otus.spring.homework_25.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_25.domain.Comment;

import java.util.List;

public interface CommentService {
    void publishComment(String message, Long bookId);

    List<Comment> getAllCommentsByBook(Long bookId);

    void deleteComment(Long commentId);

    void editComment(Long commentId, String message);
}