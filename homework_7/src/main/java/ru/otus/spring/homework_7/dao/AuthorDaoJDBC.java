package ru.otus.spring.homework_7.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.mapper.AuthorMapper;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AuthorDaoJDBC implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorMapper authorMapper;

    @Override
    public Boolean findAuthorById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            Long authorId = jdbc.queryForObject("select a.id from authors a where a.id = :id", params, Long.class);
            return authorId != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void save(Author author) {
        jdbc.update("insert into authors(id, name) values (:id, :name)",
                Map.of("id", author.getId(),
                        "name", author.getName()));
    }

    @Override
    public Author getAuthor(String authorName) {
        authorName = authorName.trim();
        Map<String, Object> params = Collections.singletonMap("authorName", authorName);
        return jdbc.queryForObject("select a.id, a.name from authors a where a.name = :authorName", params, authorMapper);
    }
}