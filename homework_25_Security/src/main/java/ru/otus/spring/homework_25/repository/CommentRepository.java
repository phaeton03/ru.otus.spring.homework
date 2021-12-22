package ru.otus.spring.homework_25.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_25.domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}