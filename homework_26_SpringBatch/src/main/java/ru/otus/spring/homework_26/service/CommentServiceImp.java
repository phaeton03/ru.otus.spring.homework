package ru.otus.spring.homework_26.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_26.repository.mongo.BookRepository;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Comment;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {
    private static final String OFFSET = "\n\n----------------------------\n\n";

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Override
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