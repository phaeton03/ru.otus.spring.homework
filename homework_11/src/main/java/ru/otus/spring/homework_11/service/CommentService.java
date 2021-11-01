package ru.otus.spring.homework_11.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_11.repository.BookRepository;
import ru.otus.spring.homework_11.repository.CommentRepository;
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
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void publishComment(String message, Long bookId) {
        Book book = bookService.findBook(bookId);
        Comment comment = new Comment(message);
        commentRepository.save(comment);
        book.getComments().add(comment);
        bookRepository.save(book);
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