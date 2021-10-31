package ru.otus.spring.homework_9.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_9.repository.AuthorDao;
import ru.otus.spring.homework_9.repository.BookDao;
import ru.otus.spring.homework_9.repository.GenreDao;
import ru.otus.spring.homework_9.domain.Author;
import ru.otus.spring.homework_9.domain.Book;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_9.domain.Genre;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    AuthorDao authorDao;
    GenreDao genreDao;
    BookDao bookDao;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    @Transactional(readOnly = true)
    public String findAllBooks() {
        return OFFSET +
                bookDao.
                        getAll().
                        stream().
                        map(Objects::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
    public String findBooksByAuthor(String authorName) {
        Author author = authorDao.getAuthor(authorName);
        return OFFSET +
                author.getBooks().
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
    public String findBooksByGenre(String genreName) {
        Genre genre = genreDao.getGenre(genreName);
        return OFFSET +
                genre.getBooks().
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
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