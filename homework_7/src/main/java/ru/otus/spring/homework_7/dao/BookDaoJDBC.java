package ru.otus.spring.homework_7.dao;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.spring.homework_7.cache.AuthorCache;
import ru.otus.spring.homework_7.cache.BookCache;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Genre;
import ru.otus.spring.homework_7.mapper.BookMapper;

import java.security.spec.NamedParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookDaoJDBC implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private final BookMapper bookMapper;
    private final BookCache bookCache;
    private final AuthorCache authorCache;
    private final AuthorDaoJDBC authorDao;

    @Override
    public void save(Book book) {
        if (!authorDao.findAuthorById(book.getAuthor().getId())) {
            authorDao.save(book.getAuthor());
        }
        jdbc.update("insert into books (id, `name`, author_id, genre_id) values(:id, :name, :authorId, :genreId)",
                Map.of("id", book.getId(),
                        "name", book.getName(),
                        "authorId", book.getAuthor().getId(),
                        "genreId", book.getGenre().getId()));
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        jdbc.update("delete from books where id = :id", params);
        Long authorId = bookCache.getFromCache(id).map(b -> b.getAuthor().getId()).get();

        authorCache.deleteById(authorId);
        bookCache.deleteById(id);
    }

    @Override
    public Optional<Book> getById(Long id) {
        if (bookCache.getFromCache(id).isPresent()) {
            return bookCache.getFromCache(id);
        }
        Map<String, Object> params = Collections.singletonMap("id", id);
        return Optional.ofNullable(
                jdbc.queryForObject("select b.id, b.name, b.author_id, b.genre_id, " +
                                "a.id as authorId, a.name as authorName, g.id as genreId, g.name as genreName " +
                                "from books b " +
                                "left join authors a on b.author_id = a.id " +
                                "left join genre g on b.genre_id = g.id " +
                                "where b.id = :id", params,
                        bookMapper)
        );
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select b.id, b.name, b.author_id, b.genre_id, " +
                        "a.id as authorId, a.name as authorName, " +
                        "g.id as genreId, g.name as genreName " +
                        "from books b " +
                        "left join authors a on b.author_id = a.id " +
                        "left join genre g on b.genre_id = g.id",
                bookMapper);
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        Map<String, Object> params = Collections.singletonMap("genreId", genre.getId());
        return jdbc.query("select b.id, b.name, b.author_id, b.genre_id, " +
                "a.id as authorId, a.name as authorName, " +
                "g.id as genreId, g.name as genreName " +
                "from books b " +
                "left join authors a on b.author_id = a.id " +
                "left join genre g on b.genre_id = g.id " +
                "where genre_id = :genreId", params, bookMapper);
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        Long authorId = author.getId();
        Map<String, Object> params = Collections.singletonMap("authorId", authorId);
        return jdbc.query("select b.id, b.name, b.author_id, b.genre_id, " +
                "a.id as authorId, a.name as authorName, " +
                "g.id as genreId, g.name as genreName " +
                "from books b " +
                "left join authors a on b.author_id = a.id " +
                "left join genre g on b.genre_id = g.id " +
                "where author_id = :authorId", params, bookMapper);
    }
}