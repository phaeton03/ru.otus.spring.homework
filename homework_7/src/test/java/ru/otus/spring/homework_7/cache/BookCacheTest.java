package ru.otus.spring.homework_7.cache;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework_7.dao.BookDaoJDBC;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@ComponentScan({
        "ru.otus.spring.homework_7.cache",
        "ru.otus.spring.homework_7.dao",
        "ru.otus.spring.homework_7.mapper",
        "ru.otus.spring.homework_7.cache"
})
@DisplayName("Проверка корректности работы кеширования книг")
class BookCacheTest {
    @Autowired
    BookCache bookCache;
    @Autowired
    BookDaoJDBC bookDaoJDBC;
    private static final long BOOK_ID = 1l;

    @Test
    @DisplayName("Should return correct book from cache")
    void shouldReturnCorrectBookFromCache() {
        assertThat(bookCache.getBooks()).isNullOrEmpty();
        bookDaoJDBC.getById(BOOK_ID);
        assertThatCode(() -> bookCache.getFromCache(BOOK_ID)).doesNotThrowAnyException();
        System.out.println(bookCache.getBooks());
    }

    @Test
    @DisplayName("Should return correct list of books from cache")
    void shouldReturnCorrectListOfBooksFromCache() {
        assertThat(bookCache.getBooks()).isNullOrEmpty();
        val expectedBooks = bookDaoJDBC.getAll();
        assertThat(bookCache.getBooks().values()).containsAll(expectedBooks);
        System.out.println(bookCache.getBooks());
    }

    @Test
    void deleteById() {
        assertThat(bookCache.getBooks()).isNullOrEmpty();
        bookDaoJDBC.getById(BOOK_ID);
        assertThatCode(() -> bookCache.getFromCache(BOOK_ID)).doesNotThrowAnyException();
        bookDaoJDBC.deleteById(BOOK_ID);
        assertThatCode(() -> bookCache.getFromCache(BOOK_ID).isEmpty());
    }
}