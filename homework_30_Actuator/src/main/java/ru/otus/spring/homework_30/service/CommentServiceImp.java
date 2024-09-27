package ru.otus.spring.homework_30.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_30.exception.CommentNotFoundException;
import ru.otus.spring.homework_30.repository.BookRepository;
import ru.otus.spring.homework_30.repository.CommentRepository;
import ru.otus.spring.homework_30.domain.Book;
import ru.otus.spring.homework_30.domain.Comment;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImp implements CommentService {
    private static final String OFFSET = "\n\n----------------------------\n\n";

    private final BookServiceImp bookService;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void publishComment(String message, Long bookId) {
        Book book = bookService.findBook(bookId);
        Comment comment = new Comment(message);
        commentRepository.save(comment);
        book.getComments().add(comment);
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsByBook(Long bookId) {
        Book book = bookService.findBook(bookId);
        return book.getComments();
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public void editComment(Long commentId, String message) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(String.format("Comment with id = %d not found", commentId)));
        comment.setComment(message);
    }
}