package ru.otus.spring.homework_16.exception;

public class GenreNotFoundException extends HomeworkRootException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}