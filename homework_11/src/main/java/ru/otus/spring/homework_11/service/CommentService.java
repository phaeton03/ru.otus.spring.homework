package ru.otus.spring.homework_11.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_11.dao.BookDao;
import ru.otus.spring.homework_11.dao.CommentDao;
import ru.otus.spring.homework_11.domain.Book;
import ru.otus.spring.homework_11.domain.Comment;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String OFFSET = "\n\n----------------------------\n\n";

    private final BookService bookService;
    private final BookDao bookDao;
    private final CommentDao commentDao;

    @Transactional
    public void publishComment(String message, Long bookId) {
        Book book = bookService.findBook(bookId);
        Comment comment = new Comment(message);
        commentDao.save(comment);
        book.getComments().add(comment);
        bookDao.save(book);
    }

    @Transactional(readOnly = true)
    public String getAllCommentsByBook(Long bookId) {
        Book book = bookService.findBook(bookId);
        return OFFSET + book.getComments().
                stream().
                map(Objects::toString).
                collect(Collectors.joining("\n"));
    }
}