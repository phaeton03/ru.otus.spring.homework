package ru.otus.spring.homework_7.mapper;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_7.cache.Cacheable;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class BookMapper implements RowMapper<Book> {
    Cacheable<Author> authorCache;
    Cacheable<Book> bookCache;
    Cacheable<Genre> genreCache;

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException, DataAccessException {
        Long bookId = rs.getLong("id");
        String bookName = rs.getString("name");
        Long authorId = rs.getLong("author_id");
        Long genreId = rs.getLong("genre_id");
        String authorName = rs.getString("authorName");
        String genreName = rs.getString("genreName");
        Author author = authorCache.getFromCache(authorId).
                orElse(new Author(authorId, authorName, new HashSet<>()));
        Genre genre = genreCache.getFromCache(genreId).
                orElse(new Genre(genreId, genreName, new HashSet<>()));
        Book book = bookCache.getFromCache(bookId).
                orElse(new Book(bookId, bookName, author, genre));

        author.getBooks().add(book);
        genre.getBooks().add(book);

        authorCache.saveToCache(author);
        genreCache.saveToCache(genre);
        bookCache.saveToCache(book);

        return book;
    }
}