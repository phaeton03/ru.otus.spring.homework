package ru.otus.spring.homework_13.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.homework_13.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}