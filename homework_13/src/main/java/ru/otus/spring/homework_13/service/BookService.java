package ru.otus.spring.homework_13.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_13.exception.AuthorNotFoundException;
import ru.otus.spring.homework_13.exception.BookNotFoundException;
import ru.otus.spring.homework_13.exception.GenreNotFoundException;
import ru.otus.spring.homework_13.repository.BookRepository;
import ru.otus.spring.homework_13.domain.Book;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {
    BookRepository bookRepository;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    public String findAllBooks() {
        return OFFSET +
                bookRepository.
                        findAll().
                        stream().
                        map(Objects::toString).
                        collect(Collectors.joining("\n"));
    }

    public String findBooksByAuthor(String authorName) {
        List<Book> authors = bookRepository.findByAuthor_Name(authorName);

        if (authors.isEmpty()) {
            throw new AuthorNotFoundException(String.format("Author not found exception %s", authorName));
        }

        return OFFSET + authors.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    public String findBooksByGenre(String genreName) {
        List<Book> books = bookRepository.findByGenre_Name(genreName);

        if (books.isEmpty()) {
            throw new GenreNotFoundException(String.format("Genre not found, genreName: %s", genreName));
        }

        return OFFSET + books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    public String getBook(String bookId) {
        return OFFSET +
                bookRepository.
                        findById(bookId).
                        orElseThrow(() -> new BookNotFoundException(String.format("Book not found exception %s", bookId))).toString();
    }

    public void deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book findBook(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException(String.format("Book not found, bookId = %d", bookId)));
    }
}