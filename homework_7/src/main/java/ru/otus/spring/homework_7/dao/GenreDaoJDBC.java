package ru.otus.spring.homework_7.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.domain.Genre;
import ru.otus.spring.homework_7.mapper.GenreMapper;

import java.util.Collections;
import java.util.Map;

@Repository
@AllArgsConstructor
public class GenreDaoJDBC implements GenreDao {
    NamedParameterJdbcOperations jdbc;
    GenreMapper genreMapper;
    @Override
    public Genre getGenre(String genreName) {
        Map<String, Object> params = Collections.singletonMap("genreName", genreName);
        return jdbc.queryForObject("select * from genre where name = :genreName", params, genreMapper);
    }
}