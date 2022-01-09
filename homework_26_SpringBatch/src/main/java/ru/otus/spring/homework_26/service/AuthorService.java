package ru.otus.spring.homework_26.service;

public interface AuthorService {
    void rename(String authorId, String newAuthorName);
    String getAuthor(String authorId);
    void writeBiography(String authorId, String history);
}