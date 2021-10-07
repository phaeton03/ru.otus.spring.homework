package ru.otus.spring.homework_7.mapper;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_7.cache.AuthorCache;
import ru.otus.spring.homework_7.cache.BookCache;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;

@Component
@AllArgsConstructor
public class AuthorMapper implements RowMapper<Author> {
    BookMapper bookMapper;
    BookCache bookCache;
    AuthorCache authorCache;

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        System.out.println("test");
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        Author author = authorCache.getFromCache(id).
                orElse(new Author(id, name, new HashSet<>()));

        val bookSizeInitial = author.getBooks().size();
        while (rs.next()) {
            Book book = bookCache.getFromCache(rs.getLong("id")).
                    orElse(bookMapper.mapRow(rs, rowNum));
            author.getBooks().add(book);
        }
        val bookSizeFinal = author.getBooks().size();
        val diffBookSize = bookSizeFinal - bookSizeInitial;

        if (diffBookSize > 0 || authorCache.getFromCache(id).isEmpty()) {
            authorCache.saveToCache(author);
        }
        return author;
    }
}