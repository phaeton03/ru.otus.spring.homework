package ru.otus.spring.homework_30.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_30.exception.AuthorNotFoundException;
import ru.otus.spring.homework_30.exception.BookNotFoundException;
import ru.otus.spring.homework_30.exception.GenreNotFoundException;
import ru.otus.spring.homework_30.repository.AuthorRepository;
import ru.otus.spring.homework_30.repository.BookRepository;
import ru.otus.spring.homework_30.repository.GenreRepository;
import ru.otus.spring.homework_30.domain.Author;
import ru.otus.spring.homework_30.domain.Book;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_30.domain.Genre;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImp implements BookService {
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(String authorName) {
        Author author = authorRepository.
                findAuthorByName(authorName).
                orElseThrow(() -> new AuthorNotFoundException(String.format("Author not found exception %s", authorName)));
        return  author.getBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByGenre(String genreName) {
        Genre genre = genreRepository.
                findGenreByName(genreName).
                orElseThrow(() -> new GenreNotFoundException(String.format("Genre not found, genreName: %s", genreName)));
        return genre.getBooks();
    }

    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException(String.format("Book not found, bookId = %d", bookId)));
    }
}