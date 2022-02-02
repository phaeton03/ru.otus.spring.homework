package ru.otus.spring.homework_32.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_32.domain.Comment;


public interface CommentRepository extends JpaRepository<Comment, Long> {
}