package ru.otus.spring.homework_26.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_26.domain.h2.BookJPA;

import java.util.List;
import java.util.Optional;

public interface BookJPARepository extends JpaRepository<BookJPA, Long> {
    @Override
    @EntityGraph(value = "book-graph")
    List<BookJPA> findAll();

    @Override
    @EntityGraph(value = "book-graph")
    void deleteById(Long id);

    @EntityGraph(value = "book-graph")
    Optional<BookJPA> findByName(String name);
}