package ru.otus.spring.homework_13.shell;

import lombok.AllArgsConstructor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.homework_13.domain.Book;
import ru.otus.spring.homework_13.service.AuthorService;
import ru.otus.spring.homework_13.service.BookService;
import ru.otus.spring.homework_13.service.CommentService;

@AllArgsConstructor
@ShellComponent
public class ShellCommands {

    BookService bookService;
    CommentService commentService;
    AuthorService authorService;

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
    public String getBook(String bookId) {
        return bookService.getBook(bookId);
    }

    @ShellMethod(value = "delete this book", key = "delete-this-book")
    public void deleteBook(String bookId) {
        bookService.deleteBook(bookId);
    }

    @ShellMethod(value = "save this book", key = "save-this-book")
    public void saveBook(Book book) {
        bookService.saveBook(book);
    }

    @ShellMethod(value = "add this comment", key = "add-this-comment")
    public void addBookComment(String msg, String bookId) {
        commentService.publishComment(msg, bookId);
    }

    @ShellMethod(value = "get books comments", key = "show-book-comments")
    public String getBookComments(String bookId) {
        return commentService.getAllCommentsByBook(bookId);
    }

    @ShellMethod(value = "rename Author", key = "rename-author")
    public void renameAuthor(String authorId, String newAuthorName) {
       authorService.rename(authorId, newAuthorName);
    }

    @ShellMethod(value = "get books comments", key = "write-biography")
    public void writeAuthorsBiography(String authorId, String biography) {
        authorService.writeBiography(authorId, biography);
    }

    @ShellMethod(value = "get Author", key = "get-author")
    public String getAuthor(String authorId) {
        return authorService.getAuthor(authorId);
    }
}