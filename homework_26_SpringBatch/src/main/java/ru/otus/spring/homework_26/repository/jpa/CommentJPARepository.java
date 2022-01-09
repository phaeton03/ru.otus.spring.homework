package ru.otus.spring.homework_26.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_26.domain.h2.CommentJPA;


public interface CommentJPARepository extends JpaRepository<CommentJPA, Long> {
}