package ru.otus.spring.homework_7.cache;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework_7.dao.AuthorDaoJDBC;
import ru.otus.spring.homework_7.dao.BookDaoJDBC;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan({
        "ru.otus.spring.homework_7.cache",
        "ru.otus.spring.homework_7.dao",
        "ru.otus.spring.homework_7.mapper",
        "ru.otus.spring.homework_7.cache"
})
@DisplayName("Проверка корректности работы кеша авторов")
class AuthorCacheTest {
    @Autowired
    AuthorCache authorCache;
    @Autowired
    BookDaoJDBC bookDaoJDBC;
    @Autowired
    AuthorDaoJDBC authorDaoJDBC;
    private static final String AUTHOR_NAME = "testПушкин";
    private static final long AUTHOR_ID = 1l;

    @Test
    @DisplayName("Should return correct author from cache")
    void shouldReturnCorrectAuthorFromCache() {
        assertThat(authorCache.getFromCache(AUTHOR_ID).isEmpty());
        Author author = authorDaoJDBC.getAuthor(AUTHOR_NAME);
        assertThat(author).usingRecursiveComparison().isEqualTo(authorCache.getFromCache(AUTHOR_ID).get());
    }

    @Test
    @DisplayName("Should delete author from cache")
    void deleteById() {
        assertThat(authorCache.getFromCache(AUTHOR_ID).isEmpty());
        Author author = authorDaoJDBC.getAuthor(AUTHOR_NAME);
        Book book = bookDaoJDBC.getByAuthor(author).get(0);
        assertThat(authorCache.getFromCache(AUTHOR_ID).isPresent());
        bookDaoJDBC.deleteById(book.getId());
        assertThatCode(() -> authorCache.getFromCache(AUTHOR_ID).isEmpty());
    }
}