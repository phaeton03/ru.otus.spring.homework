package ru.otus.spring.homework_7;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.homework_7.dao.*;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Genre;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@ComponentScan({"ru.otus.spring.homework_7.cache",
        "ru.otus.spring.homework_7.dao", "ru.otus.spring.homework_7.mapper"})
@DisplayName("Репозиторий для работы с книгами")
class BookRepositoryJDBCTest {
    private static final long NEW_BOOK_ID = 90l;
    private static final String NEW_BOOK_NAME = "TEST";
    private static final String NEW_AUTHOR_NAME = "AUTHORTEST";
    private static final long NEW_AUTHOR_ID = 90L;
    private static final int EXPECTED_BOOK_SIZE = 10;
    private static final int EXPECTED_BOOK_SIZE_BY_GENRE = 4;
    private static final int EXPECTED_BOOK_SIZE_BY_AUTHOR = 4;
    private static final long EXISTING_BOOK_ID = 4l;
    private static final long EXISTING_AUTHOR_ID = 1l;
    private static final long EXISTING_GENRE_ID = 8l;
    private static final String EXISTING_BOOK_NAME = "testРуслан и Людмила";
    private static final String EXISTING_GENRE_NAME = "testpoetry";
    private static final String AUTHOR_NAME = "testПушкин";

    @Autowired
    private BookDaoJDBC bookDaoJDBC;
    @Autowired
    private GenreDaoJDBC genreDaoJDBC;
    @Autowired
    private AuthorDaoJDBC authorDaoJDBC;


    @DisplayName("Should return correct information about all books")
    @Test
    void shouldReturnCorrectBookListWithAllinfo() {
        val books = bookDaoJDBC.getAll();
        assertThat(books).isNotNull().hasSize(EXPECTED_BOOK_SIZE).
                allMatch(s -> !s.getName().equals("")).
                allMatch(s -> s.getAuthor() != null).
                allMatch(s -> s.getAuthor().getBooks() != null && s.getAuthor().getBooks().size() > 0).
                allMatch(s -> s.getGenre() != null).
                allMatch(s -> s.getGenre().getBooks() != null && s.getGenre().getBooks().size() > 0);
        books.forEach(System.out::println);
    }

    @DisplayName("Should return correct information about all books with concrete genre")
    @Test
    void shouldReturnCorrectBookListWithAllinfoByGenre() {
        Genre genre = genreDaoJDBC.getGenre(EXISTING_GENRE_NAME);
        val books = bookDaoJDBC.getByGenre(genre);
        assertThat(books).isNotNull().hasSize(EXPECTED_BOOK_SIZE_BY_GENRE).
                allMatch(s -> !s.getName().equals("")).
                allMatch(s -> s.getAuthor() != null).
                allMatch(s -> s.getAuthor().getBooks() != null && s.getAuthor().getBooks().size() > 0).
                allMatch(s -> s.getGenre() != null).
                allMatch(s -> s.getGenre().getBooks() != null && s.getGenre().getBooks().size() > 0);
        books.forEach(System.out::println);
    }

    @DisplayName("Should return correct information about all books with concrete author")
    @Test
    void shouldReturnCorrectBookListWithAllinfoByAuthor() {
        Author author = authorDaoJDBC.getAuthor(AUTHOR_NAME);
        val books = bookDaoJDBC.getByAuthor(author);
        assertThat(books).isNotNull().hasSize(EXPECTED_BOOK_SIZE_BY_AUTHOR).
                allMatch(s -> !s.getName().equals("")).
                allMatch(s -> s.getAuthor() != null).
                allMatch(s -> s.getAuthor().getBooks() != null && s.getAuthor().getBooks().size() > 0).
                allMatch(s -> s.getGenre() != null).
                allMatch(s -> s.getGenre().getBooks() != null && s.getGenre().getBooks().size() > 0);
        books.forEach(System.out::println);
    }

    @DisplayName("Should return correct information about the book")
    @Test
    void shouldReturnBook() {
        Author author = authorDaoJDBC.getAuthor(AUTHOR_NAME);
        Set<Book> authorBooks = new HashSet<>(bookDaoJDBC.getByAuthor(author));
        Genre genre = genreDaoJDBC.getGenre(EXISTING_GENRE_NAME);
        Set<Book> genreBooks = new HashSet<>(bookDaoJDBC.getByGenre(genre));
        val existingAuthor = new Author(EXISTING_AUTHOR_ID, AUTHOR_NAME, authorBooks);
        val existingGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME, genreBooks);
        val existingBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, existingAuthor, existingGenre);

        val expectedBook = bookDaoJDBC.getById(existingBook.getId()).get();
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(existingBook);
        System.out.println("existing book: " + existingBook + "\n\nexpected book: " + expectedBook );
    }

    @DisplayName("Delete this book")
    @Test
    void shouldDeleteBook() {
        assertThatCode(() -> bookDaoJDBC.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        bookDaoJDBC.deleteById(EXISTING_BOOK_ID);
        assertThatCode(() -> bookDaoJDBC.getById(EXISTING_BOOK_ID)).isInstanceOf(EmptyResultDataAccessException.class);
    }

    @DisplayName("Save this book")
    @Test
    void shouldSaveBook() {
        val newAuthor = new Author(NEW_AUTHOR_ID, NEW_AUTHOR_NAME, new HashSet<>());
        val existingGenre = genreDaoJDBC.getGenre(EXISTING_GENRE_NAME);
        Book newBook = new Book(NEW_BOOK_ID, NEW_BOOK_NAME, newAuthor, existingGenre);

        assertThatCode(() -> bookDaoJDBC.getById(newBook.getId())).isInstanceOf(EmptyResultDataAccessException.class);
        bookDaoJDBC.save(newBook);
        assertThatCode(() -> bookDaoJDBC.getById(newBook.getId())).doesNotThrowAnyException();
    }
}