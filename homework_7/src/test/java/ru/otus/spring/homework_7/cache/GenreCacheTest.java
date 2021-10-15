package ru.otus.spring.homework_7.cache;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.spring.homework_7.dao.GenreDaoJDBC;
import ru.otus.spring.homework_7.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ComponentScan({
        "ru.otus.spring.homework_7.cache",
        "ru.otus.spring.homework_7.dao",
        "ru.otus.spring.homework_7.mapper",
        "ru.otus.spring.homework_7.cache"
})
@DisplayName("Проверка корректности работы кеша жанров")
class GenreCacheTest {
    @Autowired
    GenreCache genreCache;
    @Autowired
    GenreDaoJDBC genreDaoJDBC;
    private final static long GENRE_ID = 10l;
    private final static String GENRE_NAME = "testfairy-tale";

    @Test
    @DisplayName("Should return correct genre from cache")
    void shouldReturnCorrectGenreFromCache() {
        assertThat(genreCache.getFromCache(GENRE_ID).isEmpty());
        val genre = genreDaoJDBC.getGenre(GENRE_NAME);
        assertThat(genre).usingRecursiveComparison().isEqualTo(genreCache.getFromCache(GENRE_ID).get());
    }

}