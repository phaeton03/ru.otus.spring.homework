package ru.otus.spring.homework_9;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.homework_9.repository.*;
import ru.otus.spring.homework_9.domain.Author;
import ru.otus.spring.homework_9.domain.Book;
import ru.otus.spring.homework_9.domain.Comment;
import ru.otus.spring.homework_9.domain.Genre;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@ComponentScan({"ru.otus.spring.homework_9.repository"})
@DisplayName("Репозиторий для работы с книгами")
@Transactional(propagation = Propagation.REQUIRED)
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
    private BookDaoJDBCRepository bookDaoJDBCRepository;
    @Autowired
    private GenreDaoJDBCRepository genreDaoJDBCRepository;
    @Autowired
    private AuthorDaoJDBCRepository authorDaoJDBCRepository;


    @DisplayName("Should return correct information about all books")
    @Test
    void shouldReturnCorrectBookListWithAllinfo() {
        val books = bookDaoJDBCRepository.getAll();
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
        Genre genre = genreDaoJDBCRepository.getGenre(EXISTING_GENRE_NAME);
        val books = genre.getBooks();
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
        Author author = authorDaoJDBCRepository.getAuthor(AUTHOR_NAME);
        val books = author.getBooks();
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
        Author author = authorDaoJDBCRepository.getAuthor(AUTHOR_NAME);
        Set<Book> authorBooks = new HashSet<>(author.getBooks());
        Genre genre = genreDaoJDBCRepository.getGenre(EXISTING_GENRE_NAME);
        Set<Book> genreBooks = new HashSet<>(genre.getBooks());
        val existingAuthor = new Author(EXISTING_AUTHOR_ID, AUTHOR_NAME, authorBooks);
        val existingGenre = new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME, genreBooks);
        val existingBook = new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME, existingAuthor, existingGenre, new ArrayList<Comment>());

        val expectedBook = bookDaoJDBCRepository.getById(existingBook.getId()).get();
        assertThat(expectedBook).usingRecursiveComparison().isEqualTo(existingBook);
        System.out.println("existing book: " + existingBook + "\n\nexpected book: " + expectedBook );
    }

    @DisplayName("Delete this book")
    @Test
    void shouldDeleteBook() {
        assertThatCode(() -> bookDaoJDBCRepository.getById(EXISTING_BOOK_ID)).doesNotThrowAnyException();
        bookDaoJDBCRepository.deleteById(EXISTING_BOOK_ID);
        assertThat(bookDaoJDBCRepository.getById(EXISTING_BOOK_ID).isEmpty());
    }

    @DisplayName("Save this book")
    @Test
    void shouldSaveBook() {
        val newAuthor = new Author(NEW_AUTHOR_ID, NEW_AUTHOR_NAME, new HashSet<Book>());
        val existingGenre = genreDaoJDBCRepository.getGenre(EXISTING_GENRE_NAME);
        Book newBook = new Book(NEW_BOOK_ID, NEW_BOOK_NAME, newAuthor, existingGenre, new ArrayList<Comment>());

        System.out.println(bookDaoJDBCRepository.getById(newBook.getId()));
        assertThat(bookDaoJDBCRepository.getById(newBook.getId()).isEmpty());
        bookDaoJDBCRepository.save(newBook);
        assertThat( bookDaoJDBCRepository.getById(newBook.getId()).isPresent());
    }
}