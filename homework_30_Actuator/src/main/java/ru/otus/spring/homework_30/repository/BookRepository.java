package ru.otus.spring.homework_30.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.homework_30.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    @EntityGraph(value = "book-graph")
    List<Book> findAll();

    @Override
    @EntityGraph(value = "book-graph")
    void deleteById(Long id);
}