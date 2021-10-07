package ru.otus.spring.homework_7.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_7.domain.Genre;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
@Getter
public class GenreCache implements Cacheable<Genre> {
    private final Map<Long, Genre> genres;

    @Override
    public void saveToCache(Genre genre) {
        genres.put(genre.getId(), genre);
    }

    @Override
    public Optional<Genre> getFromCache(Genre genre) {
        return Optional.ofNullable(genres.get(genre.getId()));
    }

    @Override
    public Optional<Genre> getFromCache(Long id) {
        return Optional.ofNullable(genres.get(id));
    }

    @Override
    public void deleteById(Long id) {
        genres.remove(id);
    }
}