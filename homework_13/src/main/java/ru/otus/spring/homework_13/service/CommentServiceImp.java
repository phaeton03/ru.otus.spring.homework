package ru.otus.spring.homework_13.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_13.repository.BookRepository;
import ru.otus.spring.homework_13.domain.Book;
import ru.otus.spring.homework_13.domain.embedded.Comment;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {
    private static final String OFFSET = "\n\n----------------------------\n\n";

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void publishComment(String message, String bookId) {
        Book book = bookService.findBook(bookId);
        Comment comment = new Comment(message);
        book.getComments().add(comment);
        bookRepository.save(book);
    }

    @Override
    public String getAllCommentsByBook(String bookId) {
        Book book = bookService.findBook(bookId);
        return OFFSET + book.getComments().
                stream().
                map(Objects::toString).
                collect(Collectors.joining("\n"));
    }
}