package ru.otus.spring.homework_7.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_7.domain.Author;
import ru.otus.spring.homework_7.domain.Genre;

import java.util.Map;
import java.util.Optional;


@AllArgsConstructor
@Component
public class AuthorCache implements Cacheable<Author> {
    private final Map<Long, Author> authors;

    @Override
    public void saveToCache(Author author) {
        authors.put(author.getId(), author);
    }

    @Override
    public Optional<Author> getFromCache(Author author) {
        return Optional.ofNullable(authors.get(author.getId()));
    }

    @Override
    public Optional<Author> getFromCache(Long id) {
        return Optional.ofNullable(authors.get(id));
    }

    @Override
    public void deleteById(Long id) {
        authors.remove(id);
    }
}