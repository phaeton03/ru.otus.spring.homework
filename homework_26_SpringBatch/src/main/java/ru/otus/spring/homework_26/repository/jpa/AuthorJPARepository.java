package ru.otus.spring.homework_26.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_26.domain.h2.AuthorJPA;

import java.util.Optional;

public interface AuthorJPARepository extends JpaRepository<AuthorJPA, Long> {
    Optional<AuthorJPA> findAuthorByName(String authorName);
}