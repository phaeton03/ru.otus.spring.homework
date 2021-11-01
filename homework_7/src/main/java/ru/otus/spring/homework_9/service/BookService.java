package ru.otus.spring.homework_9.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_9.dao.AuthorDao;
import ru.otus.spring.homework_9.dao.BookDao;
import ru.otus.spring.homework_9.dao.GenreDao;
import ru.otus.spring.homework_9.domain.Book;

import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    AuthorDao authorDao;
    GenreDao genreDao;
    BookDao bookDao;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    @Transactional
    public String findAllBooks() {
        return OFFSET +
                bookDao.
                        getAll().
                        stream().
                        map(Objects::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional
    public String findBooksByAuthor(String authorName) {
        return OFFSET +
                bookDao.
                        getByAuthor(authorDao.getAuthor(authorName)).
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional
    public String findBooksByGenre(String genreName) {
        return OFFSET +
                bookDao.
                        getByGenre(genreDao.getGenre(genreName)).
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional
    public String getBook(Long bookId) {
        return OFFSET + bookDao.getById(bookId).orElseThrow().toString();
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookDao.deleteById(bookId);
    }

    @Transactional
    public void saveBook(Book book) {
        bookDao.save(book);
    }
}