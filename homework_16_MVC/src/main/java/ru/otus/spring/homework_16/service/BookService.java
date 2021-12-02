package ru.otus.spring.homework_16.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_16.exception.AuthorNotFoundException;
import ru.otus.spring.homework_16.exception.BookNotFoundException;
import ru.otus.spring.homework_16.exception.GenreNotFoundException;
import ru.otus.spring.homework_16.repository.AuthorRepository;
import ru.otus.spring.homework_16.repository.BookRepository;
import ru.otus.spring.homework_16.repository.GenreRepository;
import ru.otus.spring.homework_16.domain.Author;
import ru.otus.spring.homework_16.domain.Book;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_16.domain.Genre;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    AuthorRepository authorRepository;
    GenreRepository genreRepository;
    BookRepository bookRepository;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(String authorName) {
        Author author = authorRepository.
                findAuthorByName(authorName).
                orElseThrow(() -> new AuthorNotFoundException(String.format("Author not found exception %s", authorName)));
        return  author.getBooks();
    }

    @Transactional(readOnly = true)
    public List<Book> findBooksByGenre(String genreName) {
        Genre genre = genreRepository.
                findGenreByName(genreName).
                orElseThrow(() -> new GenreNotFoundException(String.format("Genre not found, genreName: %s", genreName)));
        return genre.getBooks();
    }

  //  @Transactional
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

   // @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException(String.format("Book not found, bookId = %d", bookId)));
    }
}