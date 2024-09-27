package ru.otus.spring.homework_9.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_9.domain.Book;
import ru.otus.spring.homework_9.domain.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookDaoJDBCRepository implements BookDao {
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
        Book book = em.find(Book.class, id);
        em.remove(book);
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
    public void addBookComment(Comment comment, Book book) {
        book.getComments().add(comment);
    }
}