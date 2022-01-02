package ru.otus.spring.homework_26.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework_26.domain.mongo.Book;
import ru.otus.spring.homework_26.domain.mongo.embedded.Comment;
import ru.otus.spring.homework_26.domain.mongo.embedded.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByGenre_Name(String genreName);
    List<Book> findByAuthor_Name(String authorName);

    Book findByCommentsIsContaining(Comment comment);
}