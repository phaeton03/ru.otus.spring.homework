package ru.otus.spring.homework_16.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework_16.domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}