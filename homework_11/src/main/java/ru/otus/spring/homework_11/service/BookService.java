package ru.otus.spring.homework_11.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_11.Exception.AuthorNotFoundException;
import ru.otus.spring.homework_11.Exception.BookNotFoundException;
import ru.otus.spring.homework_11.Exception.GenreNotFoundException;
import ru.otus.spring.homework_11.repository.AuthorRepository;
import ru.otus.spring.homework_11.repository.BookRepository;
import ru.otus.spring.homework_11.repository.GenreRepository;
import ru.otus.spring.homework_11.domain.Author;
import ru.otus.spring.homework_11.domain.Book;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_11.domain.Genre;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    AuthorRepository authorRepository;
    GenreRepository genreRepository;
    BookRepository bookRepository;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    @Transactional(readOnly = true)
    public String findAllBooks() {
        return OFFSET +
                bookRepository.
                        findAll().
                        stream().
                        map(Objects::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
    public String findBooksByAuthor(String authorName) {
        Author author = authorRepository.
                findAuthorByName(authorName).
                orElseThrow(() -> new AuthorNotFoundException(String.format("Author not found exception %s", authorName)));
        return OFFSET +
                author.getBooks().
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
    public String findBooksByGenre(String genreName) {
        Genre genre = genreRepository.
                findGenreByName(genreName).
                orElseThrow(() -> new GenreNotFoundException(String.format("Genre not found, genreName: %s", genreName)));
        return OFFSET +
                genre.getBooks().
                        stream().
                        map(Book::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
    public String getBook(Long bookId) {
        return OFFSET +
                bookRepository.
                        findById(bookId).
                        orElseThrow(() -> new BookNotFoundException(String.format("Book not found exception %s", bookId))).toString();
    }

    @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException(String.format("Book not found, bookId = %d", bookId)));
    }
}