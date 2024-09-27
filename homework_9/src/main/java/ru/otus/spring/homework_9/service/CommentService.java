package ru.otus.spring.homework_9.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_9.repository.BookDao;
import ru.otus.spring.homework_9.repository.CommentDao;
import ru.otus.spring.homework_9.domain.Book;
import ru.otus.spring.homework_9.domain.Comment;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String OFFSET = "\n\n----------------------------\n\n";

    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Transactional
    public void publishComment(String message, Long bookId) {
        Comment comment = new Comment(message);
        commentDao.addComment(comment);
        Book book = bookDao.getById(bookId).orElseThrow();
        bookDao.addBookComment(comment, book);
    }

    @Transactional(readOnly = true)
    public String getAllCommentsByBook(Long bookId) {
        Book book = bookDao.getById(bookId).orElseThrow();
        return OFFSET + book.getComments().
                stream().
                map(Objects::toString).
                collect(Collectors.joining("\n"));
    }
}