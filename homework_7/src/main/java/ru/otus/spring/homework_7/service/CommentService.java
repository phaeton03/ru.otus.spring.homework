package ru.otus.spring.homework_7.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_7.dao.BookDao;
import ru.otus.spring.homework_7.dao.CommentDao;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Comment;

import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public String getAllCommentsByBook(Long bookId) {
        Book book = bookDao.getById(bookId).orElseThrow();
        return OFFSET + book.getComments().
                stream().
                map(c -> c.getComment()).
                collect(Collectors.joining("\n"));
    }
}