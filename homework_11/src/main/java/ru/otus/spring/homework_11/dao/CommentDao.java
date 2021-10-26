package ru.otus.spring.homework_11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework_11.domain.Comment;


public interface CommentDao extends JpaRepository<Comment, Long>, CrudRepository<Comment, Long> {
}