package ru.otus.spring.homework_11.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.homework_11.domain.Author;
import ru.otus.spring.homework_11.domain.Book;
import ru.otus.spring.homework_11.domain.Genre;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long>, CrudRepository<Book, Long> {
    @Override
    @EntityGraph(value = "book-graph")
    List<Book> findAll();
}