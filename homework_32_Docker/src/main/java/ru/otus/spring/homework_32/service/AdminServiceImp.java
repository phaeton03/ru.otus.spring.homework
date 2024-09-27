package ru.otus.spring.homework_32.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_32.domain.Book;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImp implements AdminService {
    private final BookService bookService;
    private final CommentService commentService;

    @Override
    public void deleteBook(Long bookId) {
        bookService.deleteBook(bookId);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentService.deleteComment(commentId);
    }

    @Override
    public void editComment(Long commentId, String message) {
        commentService.editComment(commentId, message);
    }

    @Override
    public List<Book> getBooks() {
        return bookService.findAllBooks();
    }
}