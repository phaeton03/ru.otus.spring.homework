package ru.otus.spring.homework_7.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_7.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.Map;

@Repository
@AllArgsConstructor
public class AuthorDaoJDBC implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Boolean findAuthorById(Long id) {
        return em.find(Author.class, id) != null;
    }

    @Override
    public void save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
        } else {
            em.merge(author);
        }
    }

    @Override
    public Author getAuthor(String authorName) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :authorName", Author.class);
        query.setParameter("authorName", authorName);
        return query.getSingleResult();
    }
}