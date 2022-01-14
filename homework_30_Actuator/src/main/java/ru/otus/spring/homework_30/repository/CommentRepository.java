package ru.otus.spring.homework_30.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_30.domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}