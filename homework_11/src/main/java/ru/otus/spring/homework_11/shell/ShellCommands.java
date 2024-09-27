package ru.otus.spring.homework_11.shell;

import lombok.AllArgsConstructor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework_11.domain.Book;
import ru.otus.spring.homework_11.service.BookService;
import ru.otus.spring.homework_11.service.CommentService;

@AllArgsConstructor
@ShellComponent
public class ShellCommands {

    BookService bookService;
    CommentService commentService;

    @ShellMethod(value = "Show all books", key = "show-all-books")
    public String findAllBooks() {
        return bookService.findAllBooks();
    }

    @ShellMethod(value = "Show all books by Author", key = "show-all-books-by-author")
    public String findBooksByAuthor(String authorName) {
        return bookService.findBooksByAuthor(authorName);
    }

    @ShellMethod(value = "Show all books by Genre", key = "show-all-books-by-genre")
    public String findBooksByGenre(String genreName) {
        return bookService.findBooksByGenre(genreName);
    }

    @ShellMethod(value = "Show this book", key = "show-this-book")
    public String getBook(Long bookId) {
        return bookService.getBook(bookId);
    }

    @ShellMethod(value = "delete this book", key = "delete-this-book")
    public void deleteBook(Long bookId) {
         bookService.deleteBook(bookId);
    }

    @ShellMethod(value = "save this book", key = "save-this-book")
    public void saveBook(Book book) {
        bookService.saveBook(book);
    }

    @ShellMethod(value = "add this comment", key = "add-this-comment")
    public void addBookComment(String msg, Long bookId) {
        commentService.publishComment(msg, bookId);
    }

    @ShellMethod(value = "get books comments", key = "show-book-comments")
    public String getBookComments(Long bookId) {
        return commentService.getAllCommentsByBook(bookId);
    }
}