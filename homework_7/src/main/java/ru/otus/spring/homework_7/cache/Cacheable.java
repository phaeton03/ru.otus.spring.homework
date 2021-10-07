package ru.otus.spring.homework_7.cache;

import java.util.Optional;

public interface Cacheable<T> {
    void saveToCache(T element);

    Optional<T> getFromCache(T element);

    Optional<T> getFromCache(Long id);

    void deleteById(Long id);
}