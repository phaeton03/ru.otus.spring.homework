package ru.otus.spring.homework_16.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework_16.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    @EntityGraph(value = "book-graph")
    List<Book> findAll();

    @Override
    @EntityGraph(value = "book-graph")
    void deleteById(Long id);
}