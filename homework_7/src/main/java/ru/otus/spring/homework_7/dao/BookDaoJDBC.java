package ru.otus.spring.homework_7.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Comment;
import ru.otus.spring.homework_7.domain.Genre;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookDaoJDBC implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> graph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList();
    }

    @Override
    public List<Book> getByGenre(Genre genre) {
        EntityGraph<?> graph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.genre = :genre", Book.class);
        query.setParameter("genre", genre);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList();
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        EntityGraph<?> graph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b where b.author = :author", Book.class);
        query.setParameter("author", author);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList();
    }

    @Override
    public void addBookComment(Comment comment, Book book) {
        book.getComments().add(comment);
    }
}