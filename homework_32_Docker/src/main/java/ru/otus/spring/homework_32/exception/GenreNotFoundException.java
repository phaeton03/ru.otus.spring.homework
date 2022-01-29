package ru.otus.spring.homework_32.exception;

import ru.otus.spring.homework_32.exception.root.HomeworkRootException;

public class GenreNotFoundException extends HomeworkRootException {
    public GenreNotFoundException(String message) {
        super(message);
    }
}