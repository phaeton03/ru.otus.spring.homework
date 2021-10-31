package ru.otus.spring.homework_9.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_9.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Component
@AllArgsConstructor
public class GenreDaoJDBCRepository implements GenreDao {
    @PersistenceContext
    private final EntityManager em;
    @Override
    public Genre getGenre(String genreName) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :genreName", Genre.class);
        query.setParameter("genreName", genreName);
        return query.getSingleResult();
    }
}