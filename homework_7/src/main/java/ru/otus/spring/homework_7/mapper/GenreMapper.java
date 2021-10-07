package ru.otus.spring.homework_7.mapper;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.spring.homework_7.cache.BookCache;
import ru.otus.spring.homework_7.cache.GenreCache;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class GenreMapper implements RowMapper<Genre> {
    BookMapper bookMapper;
    GenreCache genreCache;
    BookCache bookCache;

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Genre genre = genreCache.getFromCache(id).
                orElse(new Genre(id, name, new HashSet<>()));

        val bookSizeInitial = genre.getBooks().size();

        while (rs.next()) {
            Book book = bookCache.getFromCache(rs.getLong("id")).
                    orElse(bookMapper.mapRow(rs, rowNum));
            genre.getBooks().add(book);
        }
        val bookSizeFinal = genre.getBooks().size();
        val diffBookSize = bookSizeFinal - bookSizeInitial;

        if (diffBookSize > 0 || genreCache.getFromCache(id).isEmpty()) {
            genreCache.saveToCache(genre);
        }
        return genre;
    }
}