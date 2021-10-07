package ru.otus.spring.homework_7.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_7.domain.Book;
import ru.otus.spring.homework_7.domain.Genre;

import java.util.Map;
import java.util.Optional;

@Component
@Getter
@AllArgsConstructor
public class BookCache implements Cacheable<Book> {
    private final Map<Long, Book> books;

    @Override
    public void saveToCache(Book book) {
        books.put(book.getId(), book);
    }

    @Override
    public Optional<Book> getFromCache(Book book) {
        return Optional.ofNullable(books.get(book.getId()));
    }

    @Override
    public Optional<Book> getFromCache(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public void deleteById(Long id) {
        books.remove(id);
    }
}