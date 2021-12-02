package ru.otus.spring.homework_13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework_13.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByGenre_Name(String genreName);
    List<Book> findByAuthor_Name(String authorName);
}