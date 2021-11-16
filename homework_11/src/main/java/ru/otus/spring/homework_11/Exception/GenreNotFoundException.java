package ru.otus.spring.homework_11.Exception;

public class GenreNotFoundException extends HomeworkRootException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}