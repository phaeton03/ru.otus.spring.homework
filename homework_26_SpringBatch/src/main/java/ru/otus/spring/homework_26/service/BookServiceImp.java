package ru.otus.spring.homework_26.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_26.exception.AuthorNotFoundException;
import ru.otus.spring.homework_26.exception.BookNotFoundException;
import ru.otus.spring.homework_26.exception.GenreNotFoundException;
import ru.otus.spring.homework_26.repository.mongo.BookRepository;
import ru.otus.spring.homework_26.domain.mongo.Book;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;

    private static final String OFFSET = "\n\n----------------------------\n\n";

    @Override
    public String findAllBooks() {
        return OFFSET +
                bookRepository.
                        findAll().
                        stream().
                        map(Objects::toString).
                        collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
    @Override
    public String findBooksByAuthor(String authorName) {
        List<Book> authors = bookRepository.findByAuthor_Name(authorName);

        if (authors.isEmpty()) {
            throw new AuthorNotFoundException(String.format("Author not found exception %s", authorName));
        }

        return OFFSET + authors.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    @Transactional(readOnly = true)
    @Override
    public String findBooksByGenre(String genreName) {
        List<Book> books = bookRepository.findByGenre_Name(genreName);

        if (books.isEmpty()) {
            throw new GenreNotFoundException(String.format("Genre not found, genreName: %s", genreName));
        }

        return OFFSET + books.stream().map(Book::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String getBook(String bookId) {
        return OFFSET +
                bookRepository.
                        findById(bookId).
                        orElseThrow(() -> new BookNotFoundException(String.format("Book not found exception %s", bookId))).toString();
    }

    @Override
    public void deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findBook(String bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new BookNotFoundException(String.format("Book not found, bookId = %s", bookId)));
    }
}