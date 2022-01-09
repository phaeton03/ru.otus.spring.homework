package ru.otus.spring.homework_26.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework_26.domain.mongo.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}