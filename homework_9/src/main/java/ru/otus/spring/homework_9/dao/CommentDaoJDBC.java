package ru.otus.spring.homework_9.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.homework_9.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@AllArgsConstructor
public class CommentDaoJDBC implements CommentDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Comment> getAllComments() {
        return em.createQuery("select c from Comment c", Comment.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        em.remove(em.find(Comment.class, id));
    }

    @Override
    public void addComment(Comment comment) {
        if (comment.getId() == null) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }
}