package ru.otus.spring.homework_7.service;

//import org.springframework.shell.standard.ShellMethod;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_7.dao.AuthorDao;
import ru.otus.spring.homework_7.dao.BookDao;
import ru.otus.spring.homework_7.dao.GenreDao;
import ru.otus.spring.homework_7.domain.Book;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    AuthorDao authorDao;
    GenreDao genreDao;
    BookDao bookDao;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    public String findAllBooks() {
        return OFFSET +
                bookDao.
                        getAll().
                        stream().
                        map(Objects::toString).
                        collect(Collectors.joining("\n"));
    }

    public String findBooksByAuthor(String authorName) {
        return OFFSET +
                bookDao.
                        getByAuthor(authorDao.getAuthor(authorName)).
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    public String findBooksByGenre(String genreName) {
        return OFFSET +
                bookDao.
                        getByGenre(genreDao.getGenre(genreName)).
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    public String getBook(Long bookId) {
        return OFFSET + bookDao.getById(bookId).orElseThrow().toString();
    }

    public void deleteBook(Long bookId) { bookDao.deleteById(bookId); }

    public void saveBook(Book book) { bookDao.save(book); }
}