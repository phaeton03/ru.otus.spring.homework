package ru.otus.spring.homework_7.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@Repository
@AllArgsConstructor
public class GenreDaoJDBC implements GenreDao {
    @PersistenceContext
    private final EntityManager em;
    @Override
    public Genre getGenre(String genreName) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :genreName", Genre.class);
        query.setParameter("genreName", genreName);
        return query.getSingleResult();
    }
}