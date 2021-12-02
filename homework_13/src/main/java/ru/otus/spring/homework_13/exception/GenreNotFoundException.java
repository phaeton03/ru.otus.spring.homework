package ru.otus.spring.homework_13.exception;

public class GenreNotFoundException extends HomeworkRootException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}