package ru.otus.spring.homework_7.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.homework_7.dao.AuthorDao;
import ru.otus.spring.homework_7.dao.BookDao;
import ru.otus.spring.homework_7.dao.GenreDao;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@ShellComponent
public class ShellCommands {

    BookService bookService;

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
    public void deleteBook(Book book) {
        bookService.saveBook(book);
    }
}